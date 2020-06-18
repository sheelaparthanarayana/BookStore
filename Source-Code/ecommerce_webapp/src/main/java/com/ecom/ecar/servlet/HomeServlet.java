/*
|--------------------------------------------------------------------------
| Home Servlet is handling all calls for accessing home page
|--------------------------------------------------------------------------
|
|Every http get requests made to home page will eventually get to homeServlet
|response to display the main page to user in web page
|
*/
package com.ecom.ecar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecom.ecar.entity.Book;
import com.ecom.ecar.exception.EcartException;
import com.ecom.ecar.exception.ErrorCode;
import com.ecom.ecar.response.ProductCatalogResponse;
import com.ecom.ecar.util.EcartConstant;
import com.ecom.ecar.util.EcartUtil;

@SuppressWarnings("serial")
@WebServlet(name = "HomeServlet", urlPatterns = {"/index", ""})
public class HomeServlet extends HttpServlet {

    //logger for logging status of servlet which is likely to throw exception
    private static Logger logger = LoggerFactory.getLogger(HomeServlet.class);


    //doPost should do nothing
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    /*doGet(request, response)
     * HttpServletRequest, HttpServletResponse -> void
     * encounter user get call to homepage. get all categories and books from service and display information to user
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String category = request.getParameter(EcartConstant.CATEGORY);
        try {
            List<String> categories = getCategory();
            session.setAttribute(EcartConstant.CATEGORIES, categories);
            category = (category != null) ? category : categories.get(0);
            List<Book> books = getBooks(category);
            session.setAttribute(EcartConstant.BOOKS, books);
            session.setAttribute(EcartConstant.CATEGORY, category);
            request.getRequestDispatcher(EcartConstant.INDEX_JSP).include(request, response);
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

    /*getCategory()
     * None -> List<String>
     * get a list of categories from services return the list of categories
     * Exception: throw an EcartException of can not get categories from service
     */
    private List<String> getCategory() throws EcartException {
        Response response = EcartUtil.doGet(EcartUtil.constructURI(EcartConstant.PRODUCT_SERVICE, EcartConstant.ALL_CATEGORIES));
        if (response.getStatus() == 200) {
            List<String> categories;
            String entity = response.readEntity(String.class);
            ProductCatalogResponse productCatalog = (ProductCatalogResponse) EcartUtil.extractJsonObject(entity,
                    ProductCatalogResponse.class);
            categories = productCatalog.getCategories();
            return categories;
        } else {
            throw new EcartException(ErrorCode.CAN_NOT_GET_CATEGORY);
        }

    }

    /*getBooks(category)
     * String -> List<Book>
     * given a specific category and return the list of books belongs to the category
     * * Exception: throw an EcartException of can not get books from service
     */
    private List<Book> getBooks(String category) throws EcartException {
        StringBuilder builder = new StringBuilder();
        String categoryUri = builder.append(EcartConstant.CATEGORY).append(EcartConstant.SLASH).append(category).toString();
        Response response = EcartUtil.doGet(EcartUtil.constructURI(EcartConstant.PRODUCT_SERVICE, categoryUri));
        if (response.getStatus() == 200) {
            String entity = response.readEntity(String.class);
            ProductCatalogResponse productCatalog = (ProductCatalogResponse) EcartUtil.extractJsonObject(entity,
                    ProductCatalogResponse.class);
            return productCatalog.getBooks();
        } else {
            throw new EcartException(ErrorCode.CAN_NOT_GET_BOOKS);
        }
    }
}
