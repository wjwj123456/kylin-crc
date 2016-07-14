package datatest;

import java.sql.SQLException;
import java.util.List;

import data.ReportDataImpl;
import dataservice.ReportDataService;
import po.ReportPO;

public class ReportGetMergeTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ReportDataService reportDataService = new ReportDataImpl();
		String taskname = "task1";
		List<ReportPO> reportPOs = reportDataService.getMergeReport(taskname);
		for(ReportPO po: reportPOs) {
			System.out.println(po.getTaskName()+po.getUserName()+po.getPage()+po.getLocation()+po.getDescription());
		}
	}
}
