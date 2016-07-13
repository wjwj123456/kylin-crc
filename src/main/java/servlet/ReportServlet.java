
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

import bl.ReportBlImpl;
import tools.Encode;
import vo.ReportVO;

/**
 * Servlet implementation class ReportServlet
 * 
 * commit a report at the end of reviewing
 */
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportServlet() {
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

		if (type.equals("store")) {
			handleStore(request, response);
		} else if (type.equals("delete")) {
			handleDelete(request, response);
		}
		String taskName = Encode.transfer(request.getParameter("taskName"));
		String userName = (String) request.getSession().getAttribute("username");
		String data = Encode.transfer(request.getParameter("data"));

		JSONArray jsonArray = new JSONArray();

		JSONObject jsonObject = new JSONObject();
		// jsonObject.put("result", createReport(taskName, userName, data));
		// jsonObject.put("reportList", new
		// ReportBlImpl().getAllReportsByTaskName(taskName));

		jsonArray.put(jsonObject);

		PrintWriter out = response.getWriter();
		out.print(jsonArray);
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
	 * 向数据库中写入记录
	 * 
	 * @param request
	 * @param reponse
	 */
	private void handleStore(HttpServletRequest request, HttpServletResponse reponse) {
		{"taskName":"crc3","fileName":"1","page":0,"location":"1","describe":"1描述","state":0,"origin":0}

		JSONObject jsonObject = new JSONObject(request.getParameter("data"));
		
	}

	/**
	 * 从数据库中删除记录
	 * 
	 * @param request
	 * @param reponse
	 */
	private void handleDelete(HttpServletRequest request, HttpServletResponse reponse) {

	}

	/**
	 * 参与者提交报告，生成原始报告
	 * 
	 * @param taskName
	 *            任务名
	 * @param userName
	 *            用户民
	 * @param data
	 *            报告数据，json格式
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws NumberFormatException
	 */
	private int createReport(String taskName, String userName, String data)
			throws NumberFormatException, JSONException, IOException {
		List<ReportVO> reportList = new ArrayList<ReportVO>();
		JSONArray jsonArray = new JSONArray(data);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			reportList.add(new ReportVO(taskName, userName, Encode.transfer(jsonObject.getString("fileName")),
					jsonObject.getInt("page"), Integer.parseInt(jsonObject.getString("location")),
					Encode.transfer(jsonObject.getString("description")), jsonObject.getInt("state"),
					jsonObject.getInt("origin")));
		}

		ReportBlImpl report = new ReportBlImpl();

		return report.createReport(reportList);
	}
}
