package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bl.InviteBlImpl;
import bl.UserInfoBlImpl;
import blservice.UserInfoBlService;
import tools.Encode;

/**
 * Servlet implementation class TaskServlet
 * 
 * load info of a task
 */
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskServlet() {
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

		if (type.equals("agree")) {
			handleAgree(request, response);
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

	private void handleAgree(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String taskName = Encode.transfer(request.getParameter("taskName"));
		UserInfoBlService userInfoBl = new UserInfoBlImpl();
		InviteBlImpl invite = new InviteBlImpl();
		List<String> users = invite.getAgreeUser(taskName);
		List<String> pictures = new LinkedList<>();
		for(String s : users){
			pictures.add(userInfoBl.get(s).getPicture());
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("users", users);
		jsonObject.put("picture", pictures);

		JSONArray array = new JSONArray();
		array.put(jsonObject);

		PrintWriter out = response.getWriter();
		out.print(array);
	}
}
