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
import bl.ReportBlImpl;
import blservice.AssessmentBlService;
import blservice.ReportBlService;
import tools.Encode;

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
		ReportBlService reportBl = new ReportBlImpl();
		AssessmentBlService assessmentBl = new AssessmentBlImpl();
		int[][] assessmenAndFault = new int[2][];
		assessmenAndFault[0]= assessmentBl.getHistoryFaultValues(taskName);
		assessmenAndFault[1]= assessmentBl.getHistoryAssessmentValues(taskName);
		//历史项目合并数据
		session.setAttribute("taskHis_"+taskName, assessmenAndFault);
		//历史评审人数据
		session.setAttribute("userHis_"+taskName,assessmentBl.getAllAssessments(taskName) );
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
