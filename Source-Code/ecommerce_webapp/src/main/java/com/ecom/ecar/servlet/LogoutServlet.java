/*
|--------------------------------------------------------------------------
| logout Servlet serves for the log out process
|--------------------------------------------------------------------------
|
|clean the current session and invalidate the session
|
*/
package com.ecom.ecar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    //call logout method
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logOut(request, response);
    }

    //call logout method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logOut(request, response);
    }

    /*logOut(HttpServletRequest, HttpServletResponse)
     * request, response -> void
     * get session and invalidate the session
     * redirect user to where it comes
     */
    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(false).invalidate();
        response.sendRedirect(request.getHeader("referer"));
    }
}
