package filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bl.AssessmentBlImpl;
import bl.InviteBlImpl;
import bl.MergeBlImpl;
import bl.ReportBlImpl;
import bl.ReviewBlImpl;
import blservice.AssessmentBlService;
import blservice.MergeBlService;
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
		String taskName = req.getParameter("taskName");

		ReviewBlImpl review = new ReviewBlImpl();
		TaskVO task = review.getTaskVOByTaskName(taskName);
		session.setAttribute("taskVO", task);

		InviteBlImpl invite = new InviteBlImpl();
		// 接受邀请的评审者
		MergeBlService mergeBl = new MergeBlImpl();
		ReportBlService reportBl = new ReportBlImpl();
		AssessmentBlService assessmentBl = new AssessmentBlImpl();
		
		session.setAttribute("agree_" + taskName, invite.getAgreeUser(taskName));
		// 未接受邀请
		session.setAttribute("disagree_" + taskName, invite.getDisagreeUser(taskName));
		//待合并项目
		session.setAttribute("toMerge_" + taskName, mergeBl.mergeReport(taskName));
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
