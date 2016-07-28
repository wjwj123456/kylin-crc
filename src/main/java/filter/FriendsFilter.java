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

        String userName = (String) session.getAttribute("username");
        String friendName = request.getParameter("friend");

        // 是否是朋友
        FriendBlImpl friendBl = new FriendBlImpl();
        boolean isFriend = friendBl.isFriend(userName, friendName);
        session.setAttribute("isFriend_" + userName + "_" + friendName, isFriend);
        // 用户信息
        UserInfoVO userInfo = new UserInfoBlImpl().get(friendName);
        session.setAttribute("userInfo_" + friendName, userInfo);
        // 成就
        AchievementVO achievement = new AchievementBlImpl().getAchievement(friendName);
        session.setAttribute("achievement_" + friendName, achievement);
        // 完成的任务（创建者）
        List<TaskVO> doingTaskList = new ReviewBlImpl().getDoingTaskList(friendName);
        session.setAttribute("doingTaskList_" + friendName, doingTaskList);
        // 已完成的任务（创建者）
        List<TaskVO> endingTaskList = new ReviewBlImpl().getEndTaskList(friendName);
        session.setAttribute("endingTaskList_" + friendName, endingTaskList);

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
