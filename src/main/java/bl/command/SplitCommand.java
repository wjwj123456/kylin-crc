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
 * 符号说明： X <-- Y 将Y合并到X X <-\- Y 从X中拆分Y list 要拆分的条目 list[] 要拆分条目的直接子节点 vo 被拆分的条目
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
	 * 操作发起人
	 */
	private String operator;

	/**
	 * 备份所有要拆分条目的直接子节点
	 */
	private List<ReportVO> reportBackUp[];

	private SplitBlService splitBl;
	private MergeBlService mergeBl;

	@SuppressWarnings("unchecked")
	public SplitCommand(ArrayList<ReportVO> vos, ReportVO vo, String operator) {
		this.reportList = vos;
		this.report = vo;
		this.operator = operator;

		splitBl = new SplitBlImpl();
		mergeBl = new MergeBlImpl();

		reportBackUp = new ArrayList[vos.size()];
		try {
			for (int i = 0; i < vos.size(); i++) {
				reportBackUp[i] = splitBl.choose(vos.get(i));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int execute() {
		int result = 0;
		try {
			boolean flag = splitBl.split(reportList, report, operator);

			result = flag ? 0 : 1;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void undo() {
		List<ReportVO> temp = new ArrayList<>();
		for (int i = 0; i < reportBackUp.length; i++) {
			temp.clear();
			temp.add(reportList.get(i));
			temp.addAll(reportBackUp[i]);
			// 1. list <-- list[]
			mergeBl.saveMergeReport(temp, report.getTaskName(), operator);
			// 2. vo <-\- list[]
			try {
				splitBl.split((ArrayList<ReportVO>) reportBackUp[i], report, operator);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		// 3. vo <-- list
		temp.clear();
		temp.add(report);
		temp.addAll(reportList);
		mergeBl.saveMergeReport(temp, report.getTaskName(), operator);
	}
}
