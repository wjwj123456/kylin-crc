package bl.command;

import bl.MergeBlImpl;
import blservice.Command;
import blservice.MergeBlService;
import vo.ReportVO;

/**
 * delet a merged report
 * 
 * @author song
 */
public class DeleteMergeCommand implements Command {

	private ReportVO report;

	private MergeBlService mergeBl;

	public DeleteMergeCommand(ReportVO report) {
		this.report = report;

		mergeBl = new MergeBlImpl();
	}

	@Override
	public void execute() {
		mergeBl.deleteMergeRecord(report);
	}

	@Override
	public void undo() {
		mergeBl.recoverMergeRecord(report);
	}
}
