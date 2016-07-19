package bl.command;

import java.util.ArrayList;
import java.util.List;

import bl.ReportBlImpl;
import blservice.Command;
import blservice.ReportBlService;
import vo.ReportVO;

/**
 * add a report commond
 * 
 * @author song
 */
public class ReportCommand implements Command {

	private ReportVO report;
	private ReportBlService reportBl;

	public ReportCommand(ReportVO report) {
		this.report = report;

		reportBl = new ReportBlImpl();
	}

	@Override
	public int execute() {
		List<ReportVO> reportList = new ArrayList<ReportVO>();

		reportList.add(report);

		return reportBl.createReport(reportList);
	}

	@Override
	public void undo() {
		reportBl.deleteReport(report);
	}
}
