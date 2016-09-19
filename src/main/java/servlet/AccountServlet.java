package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bl.UserInfoBlImpl;
import vo.Language;
import vo.Sex;
import vo.UserInfoVO;

/**
 * Servlet implementation class AccountServlet
 * 
 * manage user information
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		if (type.equals("update")) {
			handleUpdate(request, response);
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
	 * update user information
	 */
	private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		JSONObject jsonObject = new JSONObject(request.getParameter("data"));

		String userName = (String) request.getSession().getAttribute("username");
		JSONArray array = jsonObject.getJSONArray("language");

		Language[] language = new Language[array.length()];
		for (int i = 0; i < array.length(); i++) {
			language[i] = Language.valueOf(array.getString(i));
		}

		String province = jsonObject.has("province") ? jsonObject.getString("province") : "";
		String city = jsonObject.has("city") ? jsonObject.getString("city") : "";

		UserInfoVO userInfo = new UserInfoVO(userName, jsonObject.getString("name"),
				jsonObject.getEnum(Sex.class, "sex"), jsonObject.getString("job"), province,
				city, jsonObject.getString("description"),
				jsonObject.getString("picture"), language);

		new UserInfoBlImpl().update(userInfo);
	}
}
