package datatest;

import java.sql.SQLException;
import java.util.ArrayList;

import data.SplitDataImpl;
import dataservice.SplitDataService;
import po.ReportPO;

public class GetCanSplitReportsTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SplitDataService splitDataService = new SplitDataImpl();
		ArrayList<ReportPO> reportPOs = splitDataService.getCanSplitReports("task1");
		System.out.println(reportPOs.size());
		for(ReportPO po:reportPOs) {
			System.out.println(po.getTaskName() + po.getUserName() + po.getPage() + po.getLocation() + po.getDescription());
		}
	}
}
