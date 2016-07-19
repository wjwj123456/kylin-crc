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
import blservice.ReviewBlService;
import vo.TaskVO;

/**
 * Servlet Filter implementation class myTasksFilter
 */
public class myTasksFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public myTasksFilter() {
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
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		// 正在进行中的任务(发起者)
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String userName = (String) session.getAttribute("username");

		InviteBlImpl invite = new InviteBlImpl();

		ReviewBlService reviewBl = new ReviewBlImpl();

		List<TaskVO> runningTask = reviewBl.getDoingTaskList(userName);
		session.setAttribute("runningTask", runningTask);

		// 历史任务(发起者)
		List<TaskVO> historyTask = reviewBl.getEndTaskList(userName);
		historyTask.addAll(reviewBl.getJoinedEndTasksByUserName(userName));
		session.setAttribute("historyTask", historyTask);

		// 正在进行中的任务(参与者)
		List<TaskVO> participantTask = reviewBl.getJoinedDoingTasksByUserName(userName);
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
		// TODO Auto-generated method stub
	}

}