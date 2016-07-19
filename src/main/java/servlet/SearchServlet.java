package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bl.ReviewBlImpl;
import vo.UserVO;

/**
 * Servlet implementation class SearchServlet
 * 
 * search usernames by keywords
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String type = request.getParameter("type");

		if (type.equals("searchUser")) {
			searchUser(request, response);
		} else if (type.equals("searchTask")) {
			searchTask(request, response);
		}
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
	 * search users by keyword
	 * 
	 * @param request
	 *            request from jsp page, include keyword
	 * @param response
	 *            response to jsp page, include a set of userVO
	 * @throws IOException
	 */
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ReviewBlImpl review = new ReviewBlImpl();
		List<UserVO> users = review.searchUserByKeyword(request.getParameter("keyword"));

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", users.size() == 0 ? "empty" : "success");
		jsonObject.put("users", users);

		JSONArray jsonArray = new JSONArray();
		jsonArray.put(jsonObject);

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
	}

	/**
	 * search task by keyword
	 * 
	 * @param request
	 * @param response
	 */
	private void searchTask(HttpServletRequest request, HttpServletResponse response) {

	}
}
