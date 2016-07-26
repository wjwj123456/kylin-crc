package bl.command;

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
	private String operator;

	private MergeBlService merge;

	private SplitBlService split;

	public MergeCommand(List<ReportVO> reportList, String taskName, String operator) {
		this.reportList = reportList;
		this.taskName = taskName;
		this.operator = operator;

		merge = new MergeBlImpl();
		split = new SplitBlImpl();
	}

	@Override
	public int execute() {
		return merge.saveMergeReport(reportList, taskName, operator);
	}

	@Override
	public void undo() {
		// ArrayList<ReportVO> temp;
		// temp = split.choose(reportList.get(0));
		split.splitForUndoMerge(reportList.subList(1, reportList.size()), reportList.get(0));
	}
}
