package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bl.SplitBlImpl;
import vo.ReportVO;

/**
 * Servlet implementation class SplitServlet
 */
public class SplitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SplitServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		if (type.equals("getData")) {
			handleGetData(request, response);
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
	 * 获取指定条目的组成部分
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleGetData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject(request.getParameter("data"));

		ReportVO report = new ReportVO(jsonObject.getString("taskName"),
				(String) request.getSession().getAttribute("username"), jsonObject.getString("fileName"),
				jsonObject.getInt("page"), jsonObject.getInt("location"), jsonObject.getString("description"),
				jsonObject.getInt("state"), jsonObject.getInt("origin"));

		System.out.println(jsonObject.getString("description"));
		List<ReportVO> result = new ArrayList<>();
		SplitBlImpl split = new SplitBlImpl();
		try {
			result = split.choose(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);

		PrintWriter out = response.getWriter();
		out.print(packageData(result));
	}

	/**
	 * 打包数据，返回JSON对象
	 * 
	 * @param reportList
	 */
	private JSONArray packageData(List<ReportVO> reportList) {
		JSONArray result = new JSONArray();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", reportList);

		result.put(jsonObject);

		return result;
	}
}
