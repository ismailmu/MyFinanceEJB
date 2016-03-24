package org.imu.myfinance.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imu.myfinance.service.remote.HelloServiceRemote;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		super();
	}

	@EJB
	private HelloServiceRemote ejb;

	private void doWork(HttpServletResponse res) {
		PrintWriter out = null;
		try {
			out = res.getWriter();
			Context ctx = EjbClientUtils.getInitialContext();
			String jndiname = getLookupName();
			ejb = (HelloServiceRemote) ctx.lookup(jndiname);
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Hello " + ejb.sayHello("EJB 3.x,Is Simple ?") + "</h1>");
			out.println("</body>");
			out.println("</html>");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doWork(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doWork(response);
	}

	private static String getLookupName() {
		/*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for
		 * us will be an empty string
		 */
		String appName = "MyFinance";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "MyFinanceService";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = "HelloServiceImpl";

		// Fully qualified remote interface name
		final String interfaceName = HelloServiceRemote.class.getName();

		// Create a look up string name
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		// name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" +
		// interfaceName;

		return name;
	}
}
