/*
|--------------------------------------------------------------------------
| Order Servlet serves for confirming the order of specific order
|--------------------------------------------------------------------------
|
|It collects information need to confirm an order and contact with service
|
*/
package com.ecom.ecar.servlet;

import com.ecom.ecar.exception.EcartException;
import com.ecom.ecar.exception.ErrorCode;
import com.ecom.ecar.request.OrderConfirmRequest;
import com.ecom.ecar.util.EcartConstant;
import com.ecom.ecar.util.EcartUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = {"/success"})
public class OrderServlet extends HttpServlet {

    //logger for logging status of servlet which is likely to throw exception
    private static Logger logger = LoggerFactory.getLogger(HomeServlet.class);

    //all constant goes here
    private static final String MY_CART = "myCart";
    private static final String CATEGORY = "category";
    private static final String TOTAL_PRICE = "total_price";

    /*doPost(request, response)
     * HttpServletRequest, HttpServletResponse -> void
     * get the order created by user and authorize is or deny it, redirect user to different page
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String purchaseID = (String) session.getAttribute(EcartConstant.CREATE_ORDER_RESPONSE);
            Response orderResponse = createConfirmOrder(purchaseID);
            if (orderResponse.getStatus() == 200) {
                cleanSession(session);
                response.sendRedirect(EcartUtil.buildPath(request.getContextPath(), EcartConstant.SUCCESS_JSP));
            } else if (orderResponse.getStatus() == 403) {
                cleanSession(session);
                response.sendRedirect(EcartUtil.buildPath(request.getContextPath(), EcartConstant.DENIED_JSP));
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

    //do nothing
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /*cleanSession(session)
     * HttpSession -> void
     * clean the given session, the cart information, category and total price
     */
    private void cleanSession(HttpSession session) {
        session.removeAttribute(MY_CART);
        session.removeAttribute(CATEGORY);
        session.setAttribute(TOTAL_PRICE, 0);
    }

    /*createConfirmOrder(purchaseId)
     * String -> Response
     * given purchase id and create a confirm order message and contact to the service
     * return the confirm order response from service
     * Exception: throw EcartException of can not confirm order
     */
    private Response createConfirmOrder(String purchaseId) throws EcartException {
        try {
            OrderConfirmRequest orderConfirmRequest = new OrderConfirmRequest();
            orderConfirmRequest.setPurchase_id(purchaseId);
            orderConfirmRequest.setStatus(EcartConstant.STATUS);
            String order = EcartUtil.getStringFromJson(orderConfirmRequest);
            return EcartUtil.doPost(EcartUtil.constructURI(EcartConstant.ORDER_SERVICE, EcartConstant.CONFIRMORDER), order);
        } catch (EcartException e) {
            e.printStackTrace();
            throw new EcartException(ErrorCode.CAN_NOT_CONFIRM_ORDER, e);
        }
    }
}
