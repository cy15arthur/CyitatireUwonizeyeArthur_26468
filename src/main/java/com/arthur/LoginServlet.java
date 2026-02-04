package com.arthur;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
     
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        if (password != null && password.length() < 8) {
            
            out.println("<h3>Hello " + username + ", your password is weak. Try a strong one.</h3>");
        } else {
       
            out.println("<h3>Welcome " + username + "</h3>");
        }

        out.println("<br><a href='login.html'>Back to Login</a>");
    }
}