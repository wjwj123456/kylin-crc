package filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bl.InviteBlImpl;
import vo.TaskVO;

/**
 * Servlet Filter implementation class CRCFilter
 */
public class CRCFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CRCFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String userName = (String) session.getAttribute("username");

		InviteBlImpl invite = new InviteBlImpl();

		// 用户收到的邀请
		List<TaskVO> invitationList = invite.getInvitationInfo(userName);
		session.setAttribute("invitationList", invitationList);

		// 正在进行中的任务
		List<TaskVO> runningTask = invite.getAllDoingTask(userName);
		session.setAttribute("runningTask", runningTask);

		// 历史任务
		List<TaskVO> historyTask = invite.getAllCompleteTask(userName);
		session.setAttribute("historyTask", historyTask);

		for (TaskVO task : runningTask) {
			// 接受邀请的评审者
			session.setAttribute("agree_" + task.getTaskName(), invite.getAgreeUser(task.getTaskName()));
			// 未接受邀请
			session.setAttribute("disagree_" + task.getTaskName(), invite.getDisagreeUser(task.getTaskName()));
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
