package filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
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
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
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

		if (!req.getServletPath().equals("/index.jsp")) {
			if (req.getSession().getAttribute("username") == null) {
				req.getRequestDispatcher("index.jsp?unsigned=true").forward(request, response);
			}else {
				InviteBlImpl invite = new InviteBlImpl();
				// 用户收到的邀请
				List<TaskVO> invitationList = invite.getInvitationInfo((String)req.getSession().getAttribute("username") );
				int messageNum = invitationList.size();
				req.getSession().setAttribute("messageNum", messageNum);
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
