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
import org.json.JSONException;
import org.json.JSONObject;

import bl.MergeBlImpl;
import bl.SplitBlImpl;
import tools.Encode;
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
		} else if (type.equals("split")) {
			handleSplit(request, response);
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

		ReportVO report = new ReportVO(Encode.transfer(jsonObject.getString("taskName")),
				jsonObject.getString("userName"), jsonObject.getString("fileName"), jsonObject.getInt("page"),
				jsonObject.getInt("location"), jsonObject.getString("description"), jsonObject.getInt("state"),
				jsonObject.getInt("origin"));

		List<ReportVO> result = new ArrayList<>();
		SplitBlImpl split = new SplitBlImpl();
		try {
			result = split.choose(report);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(packageData(result));
	}

	/**
	 * 处理拆分操作，发送拆分后该任务的所有评审记录
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleSplit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 原始条目
		JSONObject jsonObject = new JSONObject(request.getParameter("origin"));
		ReportVO report = getData(jsonObject);

		// 拆分条目
		JSONArray jsonArray = new JSONArray(request.getParameter("data"));
		ArrayList<ReportVO> reportList = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			reportList.add(getData(jsonArray.getJSONObject(i)));
		}

		SplitBlImpl split = new SplitBlImpl();
		// try {
		// split.split(reportList, report);
		// } catch (ClassNotFoundException | SQLException e) {
		// e.printStackTrace();
		// }

		MergeBlImpl merge = new MergeBlImpl();
		List<ReportVO> result = merge.mergeReport(report.getTaskName());

		System.out.println(result);
		System.out.println(report.getTaskName());
		JSONObject object = new JSONObject();
		object.put("data", result);

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(object);
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

	/**
	 * 转换数据，将json格式的数据转换为ReportVO
	 * 
	 * @param jsonObject
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	private ReportVO getData(JSONObject jsonObject) throws JSONException, IOException {
		ReportVO report = new ReportVO(Encode.transfer(jsonObject.getString("taskName")),
				jsonObject.getString("userName"), jsonObject.getString("fileName"), jsonObject.getInt("page"),
				jsonObject.getInt("location"), jsonObject.getString("description"), jsonObject.getInt("state"),
				jsonObject.getInt("origin"));

		return report;
	}
}
