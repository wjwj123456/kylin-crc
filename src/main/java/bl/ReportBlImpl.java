package bl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.fabric.xmlrpc.base.Array;

import blservice.ReportBlService;
import data.ReportDataImpl;
import dataservice.ReportDataService;
import po.ReportPO;
import vo.ReportVO;

public class ReportBlImpl implements ReportBlService{

	private ReportDataService reportDataService= new ReportDataImpl() ;
			
	public int createReport(List<ReportVO> vos) {
		// TODO Auto-generated method stub
		List<ReportPO> pos  = new ArrayList<ReportPO>();
		for(int i =0;i<vos.size();i++){
			ReportPO po = new ReportPO(vos.get(i).getTaskName(), vos.get(i).getUserName(), vos.get(i).getFileName(),vos.get(i).getPage(),
					vos.get(i).getLocation(), vos.get(i).getDescription());
			pos.add(po);
		}
		try {
			reportDataService.createReport(pos);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int setCompleteTime(double time) {
		// TODO Auto-generated method stub
		return 0;
	}

}
