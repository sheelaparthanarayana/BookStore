/*
|--------------------------------------------------------------------------
| Cart Servlet for handling all addItem requests from web pages
|--------------------------------------------------------------------------
|
|The Cart Servlet basically dealing with shopping cart manipulations
|such as adding item to cart, update an item in cart and delete an item in cart
|
*/
package com.ecom.ecar.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecom.ecar.entity.Item;
import com.ecom.ecar.util.EcartConstant;

@SuppressWarnings("serial")
@WebServlet(name = "CartServlet", urlPatterns = {"/addItem"})
public class CartServlet extends HttpServlet {

	//all constant goes here
	private static final String BOOK_QUANTITY = "bookQuantity";
	private static final String BOOK_PRICE = "bookPrice";
	private static final String BOOK_TITLE = "bookTitle";

	/*doPost(request,response)
	* HttpServletRequest,HttpServletResponse -> void
	* requires a request and response from web page
	* make changes to the shopping cart in session and redirect user to where he called this method
	*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter(EcartConstant.ACTION);

        if (action.equals(EcartConstant.DELETE)) {
            doDelete(request);
        } else if (action.equals(EcartConstant.UPDATE)) {
            doUpdate(request);
        } else if(action.equals(EcartConstant.ADD)){
            doAdd(request);
        }
        response.sendRedirect(request.getHeader("referer"));
    }

    /*Cart should not have any get request*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    /*doUpdate(request)
     * HttpServletRequest -> boolean
     * requires a request from web page
     * update a particular item in shopping cart and update the shopping cart in session. e.g. alternate the quantity of an item
     */
    @SuppressWarnings("unchecked")
	private boolean doUpdate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String bookid = request.getParameter(EcartConstant.BOOKID);
        int quantity = Integer.parseInt(request.getParameter(BOOK_QUANTITY));
		Map<String, Item> itemMap = (LinkedHashMap<String, Item>) session.getAttribute(EcartConstant.MY_CART);
        if (itemMap != null) {
            Item item = itemMap.get(bookid);
            if (item != null) {
                item.setQuantity(quantity);
                itemMap.put(bookid, item);
                return true;
            }
        }
        session.setAttribute(EcartConstant.MY_CART, itemMap);
        return false;
    }

    /*doAdd(request)
    * HttpServletRequest -> void
    * add an item to shopping cart and update the shopping cart in session
    */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void doAdd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String bookid = request.getParameter(EcartConstant.BOOKID);
        String bookTitle = request.getParameter(BOOK_TITLE);
        int bookPrice = Integer.parseInt(request.getParameter(BOOK_PRICE));
        String bookQuantity = request.getParameter(BOOK_QUANTITY);

        int quantity = Integer.parseInt(bookQuantity);
        if (session.getAttribute(EcartConstant.MY_CART) == null) {
            Item item = new Item();
            item.setId(bookid);
            item.setPrice(bookPrice);
            item.setQuantity(quantity);
            item.setTitle(bookTitle);
            Map<String, Item> itemMap = new LinkedHashMap<String, Item>();

            itemMap.put(bookid, item);
            session.setAttribute(EcartConstant.MY_CART, itemMap);

        } else {
            Map itemMap = (LinkedHashMap) session.getAttribute(EcartConstant.MY_CART);
            if (itemMap.containsKey(bookid)) {
                Item item = (Item) itemMap.get(bookid);
                item.setQuantity(item.getQuantity() + quantity);
                if(item.getQuantity()>10) item.setQuantity(10);
                itemMap.put(bookid, item);
            } else {
                Item item = new Item();
                item.setId(bookid);
                item.setPrice(bookPrice);
                item.setQuantity(quantity);
                item.setTitle(bookTitle);
                itemMap.put(bookid, item);
                session.setAttribute(EcartConstant.MY_CART, itemMap);

            }

        }
    }

    /*doDelete(request)
     * HttpServletRequest -> boolean
     * delete an item in shopping cart, no matter how many exist in shopping cart
     */
    @SuppressWarnings({ "unchecked"})
	private boolean doDelete(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String bookid = request.getParameter(EcartConstant.BOOKID);
		Map<String, Item> itemMap = (LinkedHashMap<String, Item>) session.getAttribute(EcartConstant.MY_CART);
		if (itemMap != null) {
		    Item item = itemMap.get(bookid);
		    if (item != null) {
		        itemMap.remove(bookid);
		        return true;
		    }
		}
		session.setAttribute(EcartConstant.MY_CART, itemMap);
        return false;
    }
}
