package filter;

import bl.FriendBlImpl;
import vo.UserInfoVO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by song on 16-7-27.
 *
 * 加载好友信息
 */
@WebFilter(filterName = "FriendsFilter", urlPatterns = {"/My CRC.jsp"})
public class FriendsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        String userName = (String) session.getAttribute("username");

        if (userName != null) {
            FriendBlImpl friendBl = new FriendBlImpl();
            List<UserInfoVO> userList = friendBl.getFriends(userName);
            session.setAttribute("friends_" + userName, userList);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
