/*
|--------------------------------------------------------------------------
| Sign up servlet
|--------------------------------------------------------------------------
|
|Check out servlet is intercepting check out page and payment page
|redirect user from other page to checkout page and compose purchase order by post to check out servlet
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
import com.ecom.ecar.exception.EcartException;
import com.ecom.ecar.util.EcartConstant;
import com.ecom.ecar.util.EcartUtil;

@SuppressWarnings("serial")
@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {

    //logger for logging status of servlet which is likely to throw exception
    private static Logger logger = LoggerFactory.getLogger(SignupServlet.class);

    //all constant goes here
    private static final String ZIP2 = "zip2";
    private static final String PROVINCE2 = "province2";
    private static final String CITY2 = "city2";
    private static final String ADDRESS2 = "address2";
    private static final String ZIP1 = "zip1";
    private static final java.lang.String PROVINCE1 = "province1";
    private static final java.lang.String CITY1 = "city1";
    private static final java.lang.String ADDRESS1 = "address1";
    private static final java.lang.String PHONENO = "phoneno";
    private static final java.lang.String PASSWORD = "password";
    private static final java.lang.String EMAIL = "email";
    private static final String LNAME = "lname";
    private static final String FNAME = "fname";
    private static final String YOU_HAVE_REGISTERED_KINDLY_LOGIN_NOW = "you have registered, kindly login now";
    private static final String USER_ALREADY_EXIST = "User Already Exist";

    /*doPost(request,response)
     * HttpServletRequest, HttpServletResponse -> void
     * gather all user information from sign up form and try to sign the user up in database
     * redirect user to the login page based on the returned status
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = constructAccount(request);
        try {
            String jsonInString = EcartUtil.getStringFromJson(acc);
            Response httpResponse = EcartUtil.doPost(EcartUtil.constructURI(EcartConstant.ORDER_SERVICE, EcartConstant.SIGNUP), jsonInString);
            if (httpResponse.getStatus() == 409) {
                session.setAttribute(EcartConstant.ERROR_MESSAGE, USER_ALREADY_EXIST);
                response.sendRedirect(EcartUtil.buildPath(request.getContextPath(), EcartConstant.LOGIN_JSP));
            } else if (httpResponse.getStatus() == 200) {
                request.setAttribute(EcartConstant.ACCOUNT_ID, acc.getAccountId());
                session.setAttribute(EcartConstant.ERROR_MESSAGE, YOU_HAVE_REGISTERED_KINDLY_LOGIN_NOW);
                response.sendRedirect(EcartUtil.buildPath(request.getContextPath(), EcartConstant.LOGIN_JSP));
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /*constructAccount(request)
     * HttpServletRequest -> Account
     * input a request and extract user information to create an object of account
     */
    private Account constructAccount(HttpServletRequest request) {
        Account acc = new Account();
        acc.setFirst_name(request.getParameter(FNAME));
        acc.setLast_name(request.getParameter(LNAME));
        acc.setEmail(request.getParameter(EMAIL));
        acc.setPassword(request.getParameter(PASSWORD));
        acc.setPhone(request.getParameter(PHONENO));
        String billing = addString(request.getParameter(ADDRESS1), request.getParameter(CITY1), request.getParameter(PROVINCE1), request.getParameter(ZIP1));
        String shipping = addString(request.getParameter(ADDRESS2), request.getParameter(CITY2), request.getParameter(PROVINCE2), request.getParameter(ZIP2));
        acc.setBilling_info(billing);
        acc.setShipping_info(shipping);
        return acc;
    }

    /*addString(cab, cib, prb, zipb)
     * String, String, String, String -> String
     * concatenate the string given into a new string return the new string
     */
    private String addString(String cab, String cib, String prb, String zipb) {
        StringBuilder builder = new StringBuilder();
        return builder.append(cab).append(" ").append(cib).append(" ").append(prb).append(" ").append(zipb).toString();
    }
}
