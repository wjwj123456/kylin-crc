package bl.command;

import java.util.ArrayList;
import java.util.List;

import bl.ReportBlImpl;
import blservice.Command;
import blservice.ReportBlService;
import vo.ReportVO;

/**
 * delete a report command
 * 
 * @author song
 */
public class DeleteCommand implements Command {

	private ReportVO report;

	private ReportBlService reportBl;

	public DeleteCommand(ReportVO report) {
		this.report = report;

		reportBl = new ReportBlImpl();
	}

	@Override
	public void execute() {
		reportBl.deleteReport(report);
	}

	@Override
	public void undo() {
		List<ReportVO> reportList = new ArrayList<>();
		reportList.add(report);

		reportBl.createReport(reportList);
	}
}
