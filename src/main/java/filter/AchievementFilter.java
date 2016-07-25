package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import bl.AchievementBlImpl;
import blservice.AchievementBlService;

/**
 * Created by song on 16-7-25.
 */
@WebFilter(filterName = "AchievementFilter", urlPatterns = { "/My CRC.jsp" })
public class AchievementFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;

		String userName = (String) request.getSession().getAttribute("username");

		if (userName != null) {
			AchievementBlService achievementBl = new AchievementBlImpl();
			request.getSession().setAttribute("achievement_" + userName, achievementBl.getAchievement(userName));
		}

		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}
}
