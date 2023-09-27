package com.example.servletapp.servlet;

import com.example.servletapp.manager.UserManager;
import com.example.servletapp.model.UserModel;
import com.example.servletapp.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String email_error = "";
        String password_error = "";

        if (email == null || email.trim().equals("") || !EmailUtil.patternMatches(email)) {
            email_error += "<br>*Email format is incorrect";

        }
        if (password == null || password.trim().equals("") || password.length() < 8) {
            password_error += "<br>*Password length must be >= 8";
        }

        if (email_error.equals("") && password_error.equals("")) {
            UserModel user = userManager.getByEmailAndPassword(email, password);
            if (user != null) {
                UserModel byEmail = userManager.getByEmail(email);
                HttpSession session = req.getSession();
                session.setAttribute("user", byEmail);
            }

            resp.sendRedirect("/");
        } else {
            req.setAttribute("mail_error", email_error);
            req.setAttribute("password_error", password_error);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
