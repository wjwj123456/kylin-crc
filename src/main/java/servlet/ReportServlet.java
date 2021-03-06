
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.LockBlImpl;
import blservice.LockBlService;
import org.json.JSONException;
import org.json.JSONObject;

import bl.ReportBlImpl;
import bl.command.CommandManager;
import bl.command.DeleteCommand;
import bl.command.ReportCommand;
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

		switch (type) {
			case "store":
				handleStore(request, response);
				break;
			case "delete":
				handleDelete(request, response);
				break;
			case "commit":
				handleCommit(request, response);
				break;
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
	 * 鍚戞暟鎹簱涓啓鍏ヨ褰�
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JSONException
	 */
	private void handleStore(HttpServletRequest request, HttpServletResponse response)
			throws JSONException, IOException {
		// List<ReportVO> reportList = new ArrayList<ReportVO>();
		JSONObject jsonObject = new JSONObject(request.getParameter("data"));

		ReportVO report = new ReportVO(jsonObject.getString("taskName"),
				(String) request.getSession().getAttribute("username"), jsonObject.getString("fileName"),
				jsonObject.getInt("page"), jsonObject.getInt("location"), jsonObject.getString("description"),
				jsonObject.getInt("state"), jsonObject.getInt("origin"));

		// ReportBlImpl report = new ReportBlImpl();
		// report.createReport(reportList);
		int result = CommandManager.executeCommand(new ReportCommand(report));

		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * 浠庢暟鎹簱涓垹闄よ褰�
	 * 
	 * @param request
	 * @param reponse
	 * @throws IOException
	 */
	private void handleDelete(HttpServletRequest request, HttpServletResponse reponse) throws IOException {
		JSONObject jsonObject = new JSONObject(request.getParameter("data"));

		// ReportBlImpl report = new ReportBlImpl();
		ReportVO report = new ReportVO(jsonObject.getString("taskName"),
				(String) request.getSession().getAttribute("username"), jsonObject.getString("fileName"),
				jsonObject.getInt("page"), jsonObject.getInt("location"), jsonObject.getString("description"),
				jsonObject.getInt("state"), jsonObject.getInt("origin"));

		int result = CommandManager.executeCommand(new DeleteCommand(report));

		PrintWriter out = reponse.getWriter();
		out.print(result);
	}

	/**
	 * 鎻愪氦鎶ュ憡
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void handleCommit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String taskName = request.getParameter("taskName");
		String userName = (String) request.getSession().getAttribute("username");
		double time = Double.parseDouble(request.getParameter("time"));
		ReportBlImpl report = new ReportBlImpl();
		int result = report.setCompleteTime(taskName, userName, time);

		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
