package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.ReviewBlImpl;
import tools.Encode;

/**
 * Servlet implementation class InviteServlet
 * 
 * invite one or several reviewers
 */
public class InviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InviteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String taskName = Encode.transfer(request.getParameter("taskName"));
		String users = Encode.transfer(request.getParameter("users"));
		int userNumber = Integer.parseInt(request.getParameter("userNumber"));

		ReviewBlImpl review = new ReviewBlImpl();
		int result = review.saveInvitation(getUsers(userNumber, users), taskName);

		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * parse usernames from data in json into String[]
	 * 
	 * @return usernames invited
	 */
	private String[] getUsers(int userNumber, String users) {
		StringTokenizer tokenizer = new StringTokenizer(users);
		String[] result = new String[userNumber];

		for (int i = 0; i < userNumber; i++) {
			result[i] = tokenizer.nextToken();
		}

		return result;
	}
}
