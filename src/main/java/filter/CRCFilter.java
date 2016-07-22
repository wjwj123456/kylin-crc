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
import bl.UserInfoBlImpl;
import blservice.ReviewBlService;
import blservice.UserInfoBlService;
import vo.TaskVO;
import vo.UserInfoVO;

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
		UserInfoBlService userInfo = new UserInfoBlImpl();

		if (userName != null) {
			UserInfoVO userInfoVO = userInfo.get(userName);
			session.setAttribute("userInfo", userInfoVO);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
