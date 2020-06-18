/*
|--------------------------------------------------------------------------
| login Servlet serves log in page
|--------------------------------------------------------------------------
|
|construct an userinfo and send it to service for checking and proceed the user to next action
|whether to next page or login page
|
*/
package com.ecom.ecar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecom.ecar.entity.Account;
import com.ecom.ecar.entity.UserInfo;
import com.ecom.ecar.exception.EcartException;
import com.ecom.ecar.exception.ErrorCode;
import com.ecom.ecar.util.EcartConstant;
import com.ecom.ecar.util.EcartUtil;

@SuppressWarnings("serial")
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    //logger for logging status of servlet which is likely to throw exception
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    //all constant goes here
    private static final String INVALID_CREDENTIALS = "User email or password is wrong, otherwise please sign up";


    /* doPost(request, response)
     * HttpServletRequest, HttpServletResponse -> void
     * create a userInfo and contact with service to check whether user is valid or not
     * redirect user to login page or proceed to next page
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserInfo userDetails = getUserDetails(request);
        HttpSession session = request.getSession();
        try {
            String stringFromJson = EcartUtil.getStringFromJson(userDetails);
            Response httpResponse = EcartUtil.doPost(EcartUtil.constructURI(EcartConstant.ORDER_SERVICE, EcartConstant.CHECKACCOUNT), stringFromJson);
            if (httpResponse.getStatus() == 401) {
                request.getSession().setAttribute(EcartConstant.LOGIN_MESSAGE, INVALID_CREDENTIALS);
                response.sendRedirect(EcartUtil.buildPath(request.getContextPath(), EcartConstant.LOGIN_JSP));
            } else if (httpResponse.getStatus() == 200) {
                String entity = httpResponse.readEntity(String.class);
                Account accountInfo = (Account) EcartUtil.extractJsonObject(entity, Account.class);
                session.setAttribute(EcartConstant.ACCOUNT_ID, accountInfo.getAccountId());
                session.setAttribute(EcartConstant.SHIPPING_INFO, accountInfo.getShipping_info());
                session.setAttribute(EcartConstant.BILLING_INFO, accountInfo.getBilling_info());
                checkIfValid(session);
                response.sendRedirect(EcartUtil.buildPath(request.getContextPath(), EcartConstant.ORDER_JSP));
            }
        } catch (EcartException e) {
            e.printStackTrace();
            logger.error("Error Occured : " + e.getErrorCode().getDescription());
            request.getSession().setAttribute(EcartConstant.ERROR_MESSAGE, e.getErrorCode().getDescription());
            request.getRequestDispatcher(EcartConstant.WELCOME_JSP).forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute(EcartConstant.ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(EcartConstant.WELCOME_JSP).forward(request, response);
        }
    }


    /*checkIfValid(session)
    * HttpSession -> void
    * check if cart or total price is valid or not if not throw an EcartException
    * Exception: EcartException of can not proceed checkout
    */
    private void checkIfValid(HttpSession session) throws EcartException {
        if (session.getAttribute(EcartConstant.TOTAL_PRICE) == null || session.getAttribute(EcartConstant.MY_CART) == null) {
            throw new EcartException(ErrorCode.CAN_NOT_PROCEED);
        }
    }


    /*doGet(request, response)
    * HttpServletRequest, HttpServletResponse -> void
    * redirect user to login page*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(EcartConstant.LOGIN_JSP);
    }

    /*getUserDetails(request)
    * HttpServletRequest -> UserInfo
    * get user info from request and return an UserInfo
    */
    private UserInfo getUserDetails(HttpServletRequest request) {
        UserInfo userDetails = new UserInfo();
        userDetails.setUsername(request.getParameter(EcartConstant.EMAIL));
        userDetails.setPassword(request.getParameter(EcartConstant.PWD));
        return userDetails;
    }
}
