package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bl.AssessmentBlImpl;
import bl.InviteBlImpl;
import bl.ReportBlImpl;
import bl.ReviewBlImpl;
import blservice.AssessmentBlService;
import blservice.ReportBlService;
import tools.Encode;
import vo.TaskVO;

/**
 * Servlet Filter implementation class TaskFilter
 */
public class TaskFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public TaskFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		req.setCharacterEncoding("UTF-8");
		String taskName = Encode.transfer(req.getParameter("taskName"));

		ReviewBlImpl review = new ReviewBlImpl();
		TaskVO task = review.getTaskVOByTaskName(taskName);
		session.setAttribute("taskVO", task);

		InviteBlImpl invite = new InviteBlImpl();
		// 接受邀请的评审者
		ReportBlService reportBl = new ReportBlImpl();
		AssessmentBlService assessmentBl = new AssessmentBlImpl();
		int[][] assessmenAndFault = new int[2][];
		assessmenAndFault[0]= assessmentBl.getHistoryFaultValues(taskName);
		assessmenAndFault[1]= assessmentBl.getHistoryAssessmentValues(taskName);
		session.setAttribute("agree_" + taskName, invite.getAgreeUser(taskName));
		// 未接受邀请
		session.setAttribute("disagree_" + taskName, invite.getDisagreeUser(taskName));
		//待合并项目
		session.setAttribute("toMerge_" + taskName, reportBl.getAllReportsByTaskName(taskName));
		//历史项目合并数据
		session.setAttribute("taskHis_"+taskName, assessmenAndFault);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
