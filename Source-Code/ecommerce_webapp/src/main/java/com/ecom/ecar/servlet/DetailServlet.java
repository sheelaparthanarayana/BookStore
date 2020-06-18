/*
|--------------------------------------------------------------------------
| Detail Servlet serves for the detail product page
|--------------------------------------------------------------------------
|
|This servlet is used for filling the detail pages for each products by book information
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
@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends HttpServlet {

    //logger for logging status of servlet which is likely to throw exception
    private static Logger logger = LoggerFactory.getLogger(DetailServlet.class);

    //do Post should do nothing
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    /*doGet(request, response)
    * HttpServletRequest, HttpServletResponse -> void
    * get book details by calling getBook(bookid) method to get the particular book information from service
    * and send information to page, redirect to detail page*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookid = request.getParameter(EcartConstant.BOOKID);
        try {
            List<Book> book = getBook(bookid);
            request.getSession().setAttribute(EcartConstant.BOOK, book);
        } catch (EcartException e) {
            e.printStackTrace();
            logger.error("Error Occured : " + e.getErrorCode().getDescription());
            request.getSession().setAttribute(EcartConstant.ERROR_MESSAGE, e.getErrorCode().getDescription());
            request.getRequestDispatcher(EcartConstant.WELCOME_JSP).forward(request, response);
        } catch (Exception e) {
            request.getSession().setAttribute(EcartConstant.ERROR_MESSAGE, e.getMessage());
            request.getRequestDispatcher(EcartConstant.WELCOME_JSP).forward(request, response);
        }
        request.getRequestDispatcher(EcartConstant.DETAIL_JSP).forward(request, response);
    }

    /*getBook(bookid)
    * String -> list<Book>
    * contact to service to get information of a book by book id and return the book
    */
    private List<Book> getBook(String bookid) throws EcartException {
        Response response = EcartUtil.doGet(EcartUtil.constructURI(EcartConstant.PRODUCT_SERVICE, new StringBuilder().append(EcartConstant.PRODUCT).append(EcartConstant.SLASH).append(bookid).toString()));
        if (response.getStatus() == 200) {
            String bookClass = response.readEntity(String.class);
            ProductCatalogResponse responseBook = (ProductCatalogResponse) EcartUtil.extractJsonObject(bookClass, ProductCatalogResponse.class);
            return responseBook.getBooks();
        } else {
            throw new EcartException(ErrorCode.CAN_NOT_GET_BOOK);
        }
    }

}
