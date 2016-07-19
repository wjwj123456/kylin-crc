package bl.command;

import java.sql.SQLException;
import java.util.ArrayList;

import bl.MergeBlImpl;
import bl.SplitBlImpl;
import blservice.Command;
import blservice.MergeBlService;
import blservice.SplitBlService;
import vo.ReportVO;

/**
 * split one report which was merged by several reports into some reports
 * 
 * @author song
 */
public class SplitCommand implements Command {

	private ArrayList<ReportVO> reportList;
	private ReportVO report;

	private SplitBlService splitBl;
	private MergeBlService mergeBl;

	public SplitCommand(ArrayList<ReportVO> vos, ReportVO vo) {
		this.reportList = vos;
		this.report = vo;

		splitBl = new SplitBlImpl();
		mergeBl = new MergeBlImpl();
	}

	@Override
	public void execute() {
		try {
			splitBl.split(reportList, report);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		reportList.add(0, report);
		mergeBl.saveMergeReport(reportList, report.getTaskName());
	}
}
