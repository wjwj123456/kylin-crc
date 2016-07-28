package bl.command;

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
        LockBlService lockBl = new LockBlImpl();

        System.out.println(operator);
        System.out.println("MergeCommand.execute");
        if (lockBl.getCurrentUser(taskName).equals("")) {
            lockBl.setCurrentUser(taskName, operator);
        }

        System.out.println(lockBl.getCurrentUser(taskName));

        if (lockBl.getCurrentUser(taskName).equals(operator)) {
            System.out.println(reportList);
            System.out.println(taskName);
            System.out.println(operator);

            return merge.saveMergeReport(reportList, taskName, operator);
        } else {
            return 404;
        }
    }

    @Override
    public void undo() {
        // ArrayList<ReportVO> temp;
        // temp = split.choose(reportList.get(0));
        split.splitForUndoMerge(reportList.subList(1, reportList.size()), reportList.get(0));
    }
}
