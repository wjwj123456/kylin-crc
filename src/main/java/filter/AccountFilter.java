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

import bl.UserInfoBlImpl;
import blservice.UserInfoBlService;
import vo.UserInfoVO;

/**
 * Servlet Filter implementation class AccountFilter
 * 
 * load info of user
 */
public class AccountFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AccountFilter() {
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

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
