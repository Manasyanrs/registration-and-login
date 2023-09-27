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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm_password");
        int errors = 0;
        String fullName_error = "";
        String email_error = "";
        String password_error = "";
        String confirm_password_error = "";
        String password_match = "";

        if (fullName == null || fullName.trim().equals("") || fullName.split(" ").length != 2) {
            fullName_error += "<br>Incorrect data ('name' 'surname')";
            errors++;
        } else if (fullName.split(" ")[0].length() < 2 || fullName.split(" ")[1].length() < 2) {
            fullName_error += "Name and surname should be at least 2 characters.";
            errors++;

        }

        if (email == null || email.trim().equals("") || !EmailUtil.patternMatches(email)) {
            email_error += "<br>*Email format is incorrect";
            errors++;
        }


        if (password == null || password.trim().equals("") || password.length() < 8) {
            password_error += "<br>*Password length must be >= 8";
            errors++;

        }
        if (confirmPassword == null || confirmPassword.trim().equals("") || confirmPassword.length() < 8) {
            confirm_password_error += "<br>*Password length must be >= 8";
            errors++;


        }
        if (!password.equals(confirmPassword)) {
            password_match += "<br>*Passwords don't match";
            errors++;

        }
        if (errors == 0) {
            UserModel user = userManager.getByEmail(email);
            if (user == null) {
                UserModel userModel = UserModel.builder()
                        .fullName(fullName)
                        .email(email)
                        .password(password)
                        .confirmPassword(confirmPassword)
                        .build();
                userManager.save(userModel);
                HttpSession session = req.getSession();
                session.setAttribute("user", userModel);
            }

            resp.sendRedirect("/");
        } else {
            req.setAttribute("fullName_error", fullName_error);
            req.setAttribute("email_error", email_error);
            req.setAttribute("password_error", password_error);
            req.setAttribute("confirm_password_error", confirm_password_error);
            req.setAttribute("password_match", password_match);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

    }
}

