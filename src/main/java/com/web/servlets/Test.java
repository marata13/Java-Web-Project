package com.web.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testing")
public class Test extends HttpServlet {
    private String message;

    public void init() {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getParameter("username"));
    }

    public void destroy() {
    }
}
