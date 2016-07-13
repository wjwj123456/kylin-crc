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
import bl.ReviewBlImpl;
import vo.TaskVO;

/**
 * Servlet Filter implementation class CRCFilter
 */
public class CRCFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CRCFilter() {
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

		String userName = (String) session.getAttribute("username");

		InviteBlImpl invite = new InviteBlImpl();

		// 用户收到的邀请
		List<TaskVO> invitationList = invite.getInvitationInfo(userName);
		session.setAttribute("invitationList", invitationList);

		// 正在进行中的任务(发起者)
		List<TaskVO> runningTask = invite.getAllDoingTask(userName);
		session.setAttribute("runningTask", runningTask);

		// 历史任务(发起者)
		List<TaskVO> historyTask = invite.getAllCompleteTask(userName);
		session.setAttribute("historyTask", historyTask);

		// 正在进行中的任务(参与者)
		List<TaskVO> participantTask = new ReviewBlImpl().getJoinedDoingTasksByUserName(userName);
		session.setAttribute("participantTask", participantTask);

		// 历史任务(参与者)
		List<TaskVO> historyTask_participant = new ReviewBlImpl().getJoinedEndTasksByUserName(userName);
		session.setAttribute("historyTask_participant", historyTask_participant);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
