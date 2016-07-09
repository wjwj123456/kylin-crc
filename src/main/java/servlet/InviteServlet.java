package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.ReviewBlImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getQueryString());
		String taskName = "";
		ReviewBlImpl review = new ReviewBlImpl();
		review.saveInvitation(getUsers(), taskName);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * parse usernames from data in json into String[]
	 * 
	 * @return usernames invited
	 */
	private String[] getUsers() {
		return null;
	}
}
