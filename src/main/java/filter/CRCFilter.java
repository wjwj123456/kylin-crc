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
		String userName = (String) req.getSession().getAttribute("username");

		InviteBlImpl invite = new InviteBlImpl();
		List<TaskVO> invitationList = invite.getInvitationInfo(userName);

		req.getSession().setAttribute("invitationList", invitationList);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
