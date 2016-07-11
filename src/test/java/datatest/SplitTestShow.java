package datatest;

import java.sql.SQLException;
import java.util.ArrayList;

import data.SplitDataImpl;
import po.ReportPO;

public class SplitTestShow {

	
	public static void main(String[] args) {
		SplitDataImpl splitDataImpl = new SplitDataImpl();
		try {
			ArrayList<ReportPO> pos = splitDataImpl.getFaultsByTaskname("task1");
			for(ReportPO po: pos) {
				System.out.println(po.getTaskName() + po.getUserName() + po.getPage() + po.getLocation() + po.getDescription());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
