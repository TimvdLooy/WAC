package nl.hu.v1wac.firstapp.servlets;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/practicum_5_les_2.do")
public class practicum_5_les_2 extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Double getal_1 = Double.parseDouble(req.getParameter("getal_1"));
		Double getal_2 = Double.parseDouble(req.getParameter("getal_2"));
		String button_sum = req.getParameter("+");
		String button_minus = req.getParameter("-");
		String button_divide = req.getParameter("/");
		String button_multiplication = req.getParameter("*");

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		if(button_sum != null){
			Double returnValue = getal_1 + getal_2;
			out.println("<p>" + returnValue +"</p>");
		}
		else if(button_minus != null){
			Double returnValue = getal_1 - getal_2;
			out.println("<p>" + returnValue + "</p>");
		}
		else if(button_divide != null){
			Double returnValue = getal_1 / getal_2;
			out.println("<p>" + returnValue + "</p>");
		}
		else if(button_multiplication != null){
			Double returnValue = getal_1 * getal_2;
			out.println("<p>" + returnValue + "</p>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
