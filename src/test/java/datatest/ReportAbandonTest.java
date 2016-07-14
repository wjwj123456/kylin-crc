package datatest;

import java.sql.SQLException;

import data.ReportDataImpl;
import dataservice.ReportDataService;
import po.ReportPO;

public class ReportAbandonTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ReportPO po = new ReportPO("task1", "iii", "Hello.java", 0, 6, "yus");
		ReportDataService dataService = new ReportDataImpl();
		System.out.println(dataService.abandonReport(po));
	}
}
