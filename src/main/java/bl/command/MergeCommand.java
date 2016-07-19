package bl.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bl.MergeBlImpl;
import bl.SplitBlImpl;
import blservice.Command;
import blservice.MergeBlService;
import blservice.SplitBlService;
import vo.ReportVO;

/**
 * merge some reports into one report
 * 
 * @author song
 *
 */
public class MergeCommand implements Command {

	private List<ReportVO> reportList;
	private String taskName;

	private MergeBlService merge;

	private SplitBlService split;

	public MergeCommand(List<ReportVO> reportList, String taskName) {
		this.reportList = reportList;
		this.taskName = taskName;

		merge = new MergeBlImpl();
		split = new SplitBlImpl();
	}

	@Override
	public void execute() {
		merge.saveMergeReport(reportList, taskName);
	}

	@Override
	public void undo() {
		ArrayList<ReportVO> temp;
		try {
			temp = split.choose(reportList.get(0));
			split.split(temp, reportList.get(0));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
