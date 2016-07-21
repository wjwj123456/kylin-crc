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
 * split one report which was merged by several reports into some reports
 * 
 * @author song
 */
public class SplitCommand implements Command {

	/**
	 * 要拆分的条目
	 */
	private ArrayList<ReportVO> reportList;

	/**
	 * 被拆分的条目
	 */
	private ReportVO report;

	/**
	 * 备份所有要拆分条目的直接子节点
	 */
	private List<ReportVO> reportBackUp[];

	private SplitBlService splitBl;
	private MergeBlService mergeBl;

	@SuppressWarnings("unchecked")
	public SplitCommand(ArrayList<ReportVO> vos, ReportVO vo) {
		this.reportList = vos;
		this.report = vo;

		reportBackUp = new ArrayList[vos.size()];
		try {
			for (int i = 0; i < vos.size(); i++) {
				reportBackUp[i] = splitBl.choose(vos.get(i));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		splitBl = new SplitBlImpl();
		mergeBl = new MergeBlImpl();
	}

	@Override
	public int execute() {
		int result = 0;
		try {
			boolean flag = splitBl.split(reportList, report);

			result = flag ? 0 : 1;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void undo() {
		reportList.add(0, report);
		mergeBl.saveMergeReport(reportList, report.getTaskName());
	}
}
