package com.arthur;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fetch")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String userQuery = request.getParameter("query");
        if (userQuery != null && !userQuery.trim().isEmpty()) {
            response.sendRedirect("https://www.google.com/search?q=" + userQuery);
        } else {
            response.sendRedirect("https://www.google.com");
        }
    }
}