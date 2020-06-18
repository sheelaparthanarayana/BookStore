/*
|--------------------------------------------------------------------------
| Check out servlet for routing to checkout pages and creating order for specific session cart
|--------------------------------------------------------------------------
|
|Check out servlet is intercepting check out page and payment page
|redirect user from other page to checkout page and compose purchase order by post to check out servlet
|
*/
package com.ecom.ecar.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.ecom.ecar.response.PurchaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecom.ecar.entity.Item;
import com.ecom.ecar.exception.EcartException;
import com.ecom.ecar.exception.ErrorCode;
import com.ecom.ecar.request.PurchaseOrder;
import com.ecom.ecar.util.EcartConstant;
import com.ecom.ecar.util.EcartUtil;

@SuppressWarnings("serial")
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/checkout", "/payment"})
public class CheckOutServlet extends HttpServlet {

    //logger for logging status of servlet which is likely to throw exception
    private static Logger logger = LoggerFactory.getLogger(CheckOutServlet.class);

    //all constant goes here
    private static final String PROCESSED = "PROCESSED";
    private static final String ADDRESS_1 = "address1";
    private static final String ADDRESS_2 = "address2";


    /*doPost(request,response)
     * HttpServletRequest, HttpServletResponse -> void
     * post action was called from checkout and it creates an order with info available in cart
     * then contact with services to create an entry in database to store a record and redirect user to next payment page
     */
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //update session
        session.setAttribute(EcartConstant.SHIPPING_INFO, request.getParameter(ADDRESS_1));
        session.setAttribute(EcartConstant.BILLING_INFO, request.getParameter(ADDRESS_2));
        session.setAttribute(EcartConstant.STATUS, PROCESSED);
        Map itemMap = (LinkedHashMap<String, Item>) session.getAttribute(EcartConstant.MY_CART);
        List<String> bookids = getBookids(itemMap);
        PurchaseOrder cpo = constructPurchaseOrder(session, bookids);
        try {
            Response httpResponse = EcartUtil.doPost(EcartUtil.constructURI(EcartConstant.ORDER_SERVICE, EcartConstant.CREATE_ORDER), EcartUtil.getStringFromJson(cpo));
            if (httpResponse.getStatus() == 200) {
                PurchaseResponse purchaseResponse = (PurchaseResponse) EcartUtil.extractJsonObject(httpResponse.readEntity(String.class), PurchaseResponse.class);
                session.setAttribute(EcartConstant.CREATE_ORDER_RESPONSE, purchaseResponse.getPurchase_id());
                response.sendRedirect(EcartUtil.buildPath(request.getContextPath(), EcartConstant.PAYMENT_JSP));
            } else {
                throw new EcartException(ErrorCode.CAN_NOT_CREATE_ORDER);
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

    /*doPost(request,response)
     * HttpServletRequest, HttpServletResponse -> void
     * redirect user to checkout page where show the detail of cart
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + EcartConstant.CHECKOUT_JSP);
    }


    /*constructPurchaseOrder( session, bookids)
     * HttpSession,List<String> -> PurchaseOrder
     * creates a purchase order with user account id, shipping address, list of products and the total prices
     * returns the constructed purchase order
     */
    private PurchaseOrder constructPurchaseOrder(HttpSession session, List<String> bookids) {
        PurchaseOrder PO = new PurchaseOrder();
        PO.setAccountId((String) session.getAttribute(EcartConstant.ACCOUNT_ID));
        PO.setShipping_info((String) session.getAttribute(EcartConstant.SHIPPING_INFO));
        PO.setStatus(PROCESSED);
        PO.setBookIds(bookids);
        PO.setTotal_price((Integer) session.getAttribute(EcartConstant.TOTAL_PRICE));
        return PO;
    }


    /*getBookids(itemMaps)
     * Map<String,Item> -> List<String>
     * get all bookids from Map structure then return the list of bookids
     * */
    private List<String> getBookids(Map<String, Item> itemMaps) {
        List<String> bookIds = new ArrayList<String>();
        if (itemMaps != null && !itemMaps.isEmpty()) {
            for (Map.Entry<String, Item> item : itemMaps.entrySet()) {
                for (int i = 0; i < item.getValue().getQuantity(); i++) {
                    bookIds.add(item.getValue().getId());
                }
            }
        }
        return bookIds;
    }


}
