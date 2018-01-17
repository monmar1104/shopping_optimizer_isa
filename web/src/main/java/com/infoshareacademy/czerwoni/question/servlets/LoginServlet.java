package com.infoshareacademy.czerwoni.question.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.login(request.getParameter("login"), request.getParameter("password"));
        } catch (ServletException se) {
            request.setAttribute("errorMessage", se.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
//        if (request.getHeader("Referer").contains("login.jsp")) {
//            response.sendRedirect("/index.jsp");
//            return;
//        }
//        response.sendRedirect(request.getHeader("Referer"));
        HttpSession session = request.getSession();
        session.setAttribute("login",request.getParameter("login"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-panel.jsp");
        requestDispatcher.forward(request, response);
    }
}

