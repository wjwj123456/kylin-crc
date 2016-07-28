package filter;

import bl.*;
import vo.AchievementVO;
import vo.TaskVO;
import vo.UserInfoVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by song on 16-7-27.
 * <p>
 * 加载好友信息
 */
@WebFilter(filterName = "FriendsFilter", urlPatterns = {"/friend.jsp"})
public class FriendsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // friend --> userInfoVO, achievementVO, taskVO;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        String userName = request.getParameter("friend");

        UserInfoVO userInfo = new UserInfoBlImpl().get(userName);
        session.setAttribute("userInfo_" + userName, userInfo);
        // 成就
        AchievementVO achievement = new AchievementBlImpl().getAchievement(userName);
        session.setAttribute("achievement_" + userName, achievement);
        // 完成的任务（创建者）
        List<TaskVO> doingTaskList = new ReviewBlImpl().getDoingTaskList(userName);
        session.setAttribute("doingTaskList_" + userName, doingTaskList);
        // 已完成的任务（创建者）
        List<TaskVO> endingTaskList = new ReviewBlImpl().getEndTaskList(userName);
        session.setAttribute("endingTaskList_" + userName, endingTaskList);
        
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
