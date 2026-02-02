package com.arthur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// This maps the URL "/login" to this class
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // We use doPost because the HTML form uses method="POST"
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Get the data from the HTML form using the "name" attributes
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        
        // 2. Set the content type so the browser knows we are sending HTML
        response.setContentType("text/html");

        // 3. Perform the logic for Assignment 1
        if (password != null && password.length() < 8) {
            // Password is too short - redirect back to the login form with an error flag
            String redirectUrl = "login.html?error=weak";
            if (username != null && !username.isBlank()) {
                redirectUrl += "&user=" + URLEncoder.encode(username, StandardCharsets.UTF_8);
            }
            response.sendRedirect(redirectUrl);
            return;
        } else {
            PrintWriter out = response.getWriter();
            // Password is 8 characters or more - render styled success page
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">\n<head>\n<meta charset=\"utf-8\">\n<meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n<title>Welcome</title>\n<style>");
            out.println(":root{--bg:#f8fafc;--card:#ffffff;--success:#059669;--muted:#6b7280}");
            out.println("html,body{height:100%;margin:0;font-family:system-ui,-apple-system,\"Segoe UI\",Roboto,\"Helvetica Neue\",Arial;background:linear-gradient(135deg,#ecfeff,#f0f9ff)}");
            out.println("body{display:flex;align-items:center;justify-content:center;padding:24px}");
            out.println(".success-card{width:100%;max-width:520px;background:var(--card);padding:28px;border-radius:12px;box-shadow:0 8px 30px rgba(2,6,23,0.08);text-align:center}");
            out.println(".success-card h1{margin:0 0 8px;font-size:22px;color:#064e3b}");
            out.println(".success-card p{color:var(--muted);margin:0 0 18px}");
            out.println(".btn{display:inline-block;padding:10px 16px;border-radius:8px;background:var(--success);color:#fff;text-decoration:none;font-weight:600}");
            out.println("</style>\n</head>\n<body>\n<div class=\"success-card\">\n<h1>Welcome, " + escapeHtml(username) + "</h1>\n<p>You've successfully logged in.</p>\n<a class=\"btn\" href=\"index.jsp\">Go to Home</a>\n</div>\n</body>\n</html>");
        }
    }

    // Simple HTML-escape helper to avoid injecting raw user input
    private static String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
  }