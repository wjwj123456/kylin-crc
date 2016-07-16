/**
* @author       lpt14
* @version      V1.0
*/
package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.MergeBlService;
import data.MergeDataImpl;
import dataservice.MergeDataService;
import po.ReportPO;
import vo.ReportVO;

public class MergeBlImpl implements MergeBlService {
	MergeDataService mergeDataService = new MergeDataImpl();

	public boolean canMerge(String taskName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = mergeDataService.canMerge(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public List<ReportVO> mergeReport(String taskName) {
		// TODO Auto-generated method stub
		List<ReportVO> voList = new ArrayList<ReportVO>();
		List<ReportPO> poList = new ArrayList<ReportPO>();
		try {
			poList = mergeDataService.mergeReport(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ReportPO po : poList) {
			voList.add(new ReportVO(po));
		}
		return voList;
	}

	public int saveMergeReport(List<ReportVO> reportList, String taskName) {
		int flag = 0;
		List<ReportPO> list = new ArrayList<ReportPO>();
		for (ReportVO vo : reportList) {
			list.add(new ReportPO(vo));
		}
		try {
			flag = mergeDataService.saveMergeReport(list, taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public int saveAddedMergeReport(List<ReportVO> reportList, String taskName) {
		int flag = 0;
		// TODO Auto-generated method stub
		List<ReportPO> list = new ArrayList<ReportPO>();
		for (ReportVO vo : reportList) {
			list.add(new ReportPO(vo));
		}
		try {
			flag = mergeDataService.saveAddedMergeReport(list, taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int saveHistory(String userName, String taskName) {

		try {
			mergeDataService.saveMergeState(userName, taskName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		int flag = 0;
		AssessmentBlImpl assessmentBlImpl = new AssessmentBlImpl();
		ReportBlImpl reportBlImpl = new ReportBlImpl();
		int assessFault = assessmentBlImpl.getAssessmentValue(taskName);
		int fault = reportBlImpl.getMergeReport(taskName).size();
		try {
			try {
				flag = mergeDataService.saveHistory(userName, taskName, fault, assessFault);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int deleteMergeRecord(ReportVO vo) {
		int flag = 0;
		// TODO Auto-generated method stub
		ReportPO po = new ReportPO(vo);
		try {
			flag = mergeDataService.deleteMergeRecord(po);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) {
		MergeBlImpl mergeBlImpl = new MergeBlImpl();
		mergeBlImpl.mergeReport("oriTest");
		System.out.println(mergeBlImpl.mergeReport("oriTest"));
	}

}
