package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String useremail = request.getParameter("email");
        String pass = request.getParameter("password");

        if (Validate.checkUser(useremail, pass)) {
            String firstName = Validate.fetchFirstName(useremail);
            String lastName = Validate.fetchLastName(useremail);

            request.setAttribute("firstName", firstName);
            request.setAttribute("userEmail", useremail);
            request.setAttribute("lastName", lastName);

            RequestDispatcher rs = request.getRequestDispatcher("main.jsp");
            rs.forward(request, response);

            //response.sendRedirect(request.getContextPath() + "/main.jsp");
        } else {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            rs.include(request, response);
        }
    }
}
