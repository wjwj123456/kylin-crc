package datatest;

import java.sql.SQLException;
import java.util.ArrayList;

import data.SplitDataImpl;
import po.ReportPO;

public class SplitTestSplit {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SplitDataImpl splitDataImpl = new SplitDataImpl();
		ReportPO po1 = new ReportPO("task1", "iii", "Hello.java", 0, 6, "yus", 1, 0, 0);
		ReportPO po2 = new ReportPO("task1", "iiii", "Hello.java", 0, 6, "yus", 1, 0, 0);
		ReportPO po3 = new ReportPO("task1", "aoliao", "Hello.java", 0, 6, "�﷨����", 0, 1, 0);
		ArrayList<ReportPO> pos = new ArrayList<ReportPO>();
		pos.add(po1);
		pos.add(po2);
		splitDataImpl.splitFaults(pos, po3);
	}
}
