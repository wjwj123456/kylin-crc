package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.InviteBlImpl;
import bl.ReviewBlImpl;
import tools.Encode;

/**
 * Servlet implementation class RefuseServlet
 * 
 * refuse an invitation from others
 */
public class RefuseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RefuseServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = Encode.transfer(request.getParameter("type"));
		String userName = (String) request.getSession().getAttribute("username");
		String taskName = Encode.transfer(request.getParameter("taskName"));

		int result = 0;
		if (type.equals("accept")) {
			ReviewBlImpl review = new ReviewBlImpl();
			result = review.saveAcceptReviewer(userName, taskName);
		} else {
			InviteBlImpl invite = new InviteBlImpl();
			result = invite.deleteInvitationInfo(userName, taskName);
		}

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
}
