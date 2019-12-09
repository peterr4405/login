package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/check")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

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
                req.setAttribute("user", user);
                
                String Query2 = "SELECT * FROM LOGIN.ASSETS where log_id =(select id  from login where username = ?)";
                psm = con.prepareStatement(Query2);
                psm.setString(1, user);
                rs = psm.executeQuery();
                if(rs.next()){
                req.setAttribute("stock", rs.getInt("stock"));
                req.setAttribute("fund", rs.getInt("fund"));
                req.setAttribute("fixed", rs.getInt("fixed"));
                req.setAttribute("activity", rs.getInt("activity"));
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/welcome.jsp");
                rd.forward(req, resp);
                //resp.sendRedirect("/login/welcome.jsp");
            } else {
                out.println("login fail");
            }

        } catch (Exception e) {
        }

    }

}
