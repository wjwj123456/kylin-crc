package filter;

import bl.AchievementBlImpl;
import blservice.AchievementBlService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by song on 16-7-25.
 *
 * 加载成就
 */
@WebFilter(filterName = "AchievementFilter", urlPatterns = {"/My CRC.jsp"})
public class AchievementFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        String userName = (String) request.getSession().getAttribute("username");

        if (userName != null) {
            AchievementBlService achievementBl = new AchievementBlImpl();
            request.setAttribute("achievement", achievementBl.getAchievement(userName));
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
