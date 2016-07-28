package bl.command;

import java.util.ArrayList;
import java.util.List;

import bl.LockBlImpl;
import bl.MergeBlImpl;
import bl.SplitBlImpl;
import blservice.Command;
import blservice.LockBlService;
import blservice.MergeBlService;
import blservice.SplitBlService;
import vo.ReportVO;

/**
 * merge some reports into one report
 *
 * @author song
 */
public class MergeCommand implements Command {

    private List<ReportVO> reportList;
    private String taskName;
    private String operator;

    /**
     * 备份所有合并条目的操作者
     */
    private List<String> operator_backup;

    private MergeBlService merge;

    private SplitBlService split;

    public MergeCommand(List<ReportVO> reportList, String taskName, String operator) {
        this.reportList = reportList;
        this.taskName = taskName;
        this.operator = operator;

        operator_backup = new ArrayList<>();
        for (ReportVO report : reportList) {
            operator_backup.add(report.getOperator());
        }

        merge = new MergeBlImpl();
        split = new SplitBlImpl();
    }

    @Override
    public int execute() {
        LockBlService lockBl = new LockBlImpl();

        if (lockBl.getCurrentUser(taskName).equals("")) {
            lockBl.setCurrentUser(taskName, operator);
        }

        if (lockBl.getCurrentUser(taskName).equals(operator)) {
            return merge.saveMergeReport(reportList, taskName, operator);
        } else {
            return 404;
        }
    }

    @Override
    public void undo() {
        // ArrayList<ReportVO> temp;
        // temp = split.choose(reportList.get(0));
        split.splitForUndoMerge(
                reportList.subList(1, reportList.size()), reportList.get(0),
                operator_backup.subList(1, reportList.size()));
    }
}
