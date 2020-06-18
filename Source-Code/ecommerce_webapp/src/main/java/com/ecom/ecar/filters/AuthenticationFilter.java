/*
|--------------------------------------------------------------------------
| AuthenticationFilter for all crucial pages access right verification
|--------------------------------------------------------------------------
|
| Authentication filter is used for check the authentication of current user
| if it has access to current page
|
*/
package com.ecom.ecar.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecom.ecar.util.EcartConstant;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/order.jsp", "/confirm", "/payment.jsp"})
public class AuthenticationFilter implements Filter {

    //logger for logging status of servlet which is likely to throw exception
    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    //all constant goes here
    private static final String UNAUTHORIZED_ACCESS = "unauthorized access";

    public void destroy() {
    }

    /*doFilter(request, response, chain)
     * ServletRequest, ServletResponse, FilterChain -> void
     * intercept the user's routine to critical page where needs authentication
     * and redirect user to login page is needed
     * */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("accountId") != null) {
            if (session.getAttribute(EcartConstant.MY_CART) == null || session.getAttribute(EcartConstant.TOTAL_PRICE) == null) {
                response.sendRedirect(request.getHeader("referer"));
            }
            chain.doFilter(req, resp);
        } else {
            logger.error("Authentication log: ", UNAUTHORIZED_ACCESS);
            response.sendRedirect(request.getContextPath() + EcartConstant.LOGIN_JSP);
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

}
