package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/check")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String url = "jdbc:derby://localhost:1527/login";
        String _user = "login";
        String _pass = "login";
        
        try (Connection con = DriverManager.getConnection(url, _user, _pass);
                Statement stmt = con.createStatement();) {
            String user = req.getParameter("username");
            String pass = req.getParameter("password");
            String Query = "select * from login where username = ? and password = ?";
            PreparedStatement psm = con.prepareStatement(Query);
            psm.setString(1, user);
            psm.setString(2, pass);
            ResultSet rs = psm.executeQuery();
            if (rs.next()) {
                resp.sendRedirect("/login/welcome.jsp");
            } else {
                out.println("login fail");
            }

        } catch (Exception e) {
        }
    }

}
