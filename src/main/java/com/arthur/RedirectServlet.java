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
        
        // 1. Grab the search term from the URL (e.g., ?query=apples)
        String userQuery = request.getParameter("query");

        // 2. The Redirect Magic
        // We take Google's search URL and append the user's word to the end
        if (userQuery != null && !userQuery.trim().isEmpty()) {
            response.sendRedirect("https://www.google.com/search?q=" + userQuery);
        } else {
            // If they typed nothing, just go to Google home
            response.sendRedirect("https://www.google.com");
        }
    }
}