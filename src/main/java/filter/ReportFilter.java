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

import bl.AssessmentBlImpl;
import bl.MergeBlImpl;
import bl.ReportBlImpl;
import bl.ReviewBlImpl;
import blservice.AssessmentBlService;
import blservice.MergeBlService;
import blservice.ReportBlService;
import tools.Encode;
import vo.TaskVO;

public class ReportFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        req.setCharacterEncoding("UTF-8");
        String taskName = Encode.transfer(req.getParameter("taskName"));
        ReviewBlImpl review = new ReviewBlImpl();
        TaskVO task = review.getTaskVOByTaskName(taskName);
        session.setAttribute("taskVO", task);
        ReportBlImpl reportBl = new ReportBlImpl();
        AssessmentBlService assessmentBl = new AssessmentBlImpl();
        MergeBlService mergeBl = new MergeBlImpl();
        int[][] assessmenAndFault = new int[3][];
        assessmenAndFault[0] = assessmentBl.getHistoryFaultValues(taskName);
        assessmenAndFault[1] = assessmentBl.getHistoryAssessmentValues_Mt(taskName);
        assessmenAndFault[2] = assessmentBl.getHistoryAssessmentValues_Mh(taskName);
        //历史项目合并数据
        session.setAttribute("taskHis_" + taskName, assessmenAndFault);
        //历史评审人数据
        session.setAttribute("userHis_" + taskName, assessmentBl.getAllAssessments(taskName));
        //待合并项目
        session.setAttribute("toMerge_" + taskName, reportBl.getMergeReport(taskName));
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
