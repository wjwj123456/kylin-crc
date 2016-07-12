package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.ReviewBlImpl;
import tools.Encode;
import vo.TaskVO;
import vo.Type;

/**
 * Servlet implementation class CreateTaskServlet
 * 
 * create new tasks
 */
public class CreateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateTaskServlet() {
		super();
	}

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = (String) request.getSession().getAttribute("username");
		String taskName = Encode.transfer(request.getParameter("taskName"));
		String type = Encode.transfer(request.getParameter("type"));
		String describe = Encode.transfer(request.getParameter("describe"));
		String date = request.getParameter("deadline");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date deadline = null;
		try {
			deadline = dateFormat.parse(date + ":00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ReviewBlImpl review = new ReviewBlImpl();
		review.saveReviewInfo(new TaskVO(userName, taskName, Type.getType(type), "", describe, deadline, 0));

		PrintWriter out = response.getWriter();
		out.print("success");
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
