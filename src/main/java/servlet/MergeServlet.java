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
import tools.Encode;
import vo.ReportVO;

/**
 * Servlet implementation class MergeServlet
 */
public class MergeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MergeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		if (type.equals("saveMerge")) {
			handleSaveMerge(request, response);
		} else if (type.equals("deleteMerge")) {
			handleDeleteMerge(request, response);
		} else if (type.equals("commitMerge")) {
			handleCommitMerge(request, response);
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
	 * 保存合并条目
	 * 
	 * @param request
	 * @param reponse
	 * @throws IOException
	 */
	private void handleSaveMerge(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String taskName = Encode.transfer(request.getParameter("taskName"));
		// String userName = (String)
		// request.getSession().getAttribute("username");

		MergeBlImpl merge = new MergeBlImpl();
		System.out.println(taskName);
		int result = merge.saveMergeReport(getData(request.getParameter("data")), taskName);

		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * 删除合并条目
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JSONException
	 */
	private void handleDeleteMerge(HttpServletRequest request, HttpServletResponse response)
			throws JSONException, IOException {
		MergeBlImpl merge = new MergeBlImpl();
		merge.deleteMergeRecord(getData(request.getParameter("data")).get(0));
	}

	/**
	 * 提交合并后的报告
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleCommitMerge(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName = (String) request.getSession().getAttribute("username");

		MergeBlImpl merge = new MergeBlImpl();
		int result = merge.saveHistory(userName, Encode.transfer(request.getParameter("taskName")));

		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * 解析json数据，获得报告列表
	 * 
	 * @param json
	 * @throws IOException
	 * @throws JSONException
	 */
	private List<ReportVO> getData(String json) throws JSONException, IOException {
		List<ReportVO> reportList = new ArrayList<ReportVO>();

		JSONArray jsonArray = new JSONArray(json);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			reportList.add(new ReportVO(Encode.transfer(jsonObject.getString("taskName")),
					jsonObject.getString("userName"), jsonObject.getString("fileName"), jsonObject.getInt("page"),
					jsonObject.getInt("location"), jsonObject.getString("description"), jsonObject.getInt("state"),
					jsonObject.getInt("origin")));
		}

		return reportList;
	}
}
