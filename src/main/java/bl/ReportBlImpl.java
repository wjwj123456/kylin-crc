package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.ReportBlService;
import data.ReportDataImpl;
import dataservice.ReportDataService;
import po.ReportPO;
import vo.ReportVO;

public class ReportBlImpl implements ReportBlService {

	private ReportDataService reportDataService = new ReportDataImpl();
	private MergeBlImpl mergeBlImpl = new MergeBlImpl();

	public int createReport(List<ReportVO> vos) {
		int flag = -1;
		List<ReportPO> pos = new ArrayList<ReportPO>();
		for (int i = 0; i < vos.size(); i++) {
			ReportPO po = new ReportPO(vos.get(i).getTaskName(), vos.get(i).getUserName(), vos.get(i).getFileName(),
					vos.get(i).getPage(), vos.get(i).getLocation(), vos.get(i).getDescription(), vos.get(i).getState(),
					vos.get(i).getOrigin());
			pos.add(po);
		}
		try {
			flag = reportDataService.createReport(pos);
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
	public int deleteReport(ReportVO vo) {
		// TODO Auto-generated method stub
		int flag = -1;
		ReportPO po = new ReportPO(vo.getTaskName(), vo.getUserName(), vo.getFileName(), vo.getPage(), vo.getLocation(),
				vo.getDescription(), vo.getState(), vo.getOrigin());

		try {
			flag = reportDataService.deleteReport(po);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public int setCompleteTime(String taskName, String reviewerName, double time) {
		// TODO Auto-generated method stub
		int flag = -1;
		try {
			flag = reportDataService.setCompleteTime(taskName, reviewerName, time);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AchievementBlImpl achievementBlImpl = new AchievementBlImpl();
		achievementBlImpl.reviewAccumulate(reviewerName, time);

		return flag;
	}

	@Override
	public List<ReportVO> getTempReport(String taskName, String reviewerName) {
		List<ReportVO> result = new ArrayList<ReportVO>();
		List<ReportPO> list = new ArrayList<ReportPO>();
		try {
			list = reportDataService.getTempReport(taskName, reviewerName);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ReportPO po : list) {
			ReportVO vo = new ReportVO(po);
			result.add(vo);
		}
		return result;
	}

	@Override
	public List<ReportVO> getAllReportsByTaskName(String taskName) {
		// TODO Auto-generated method stub
		List<ReportVO> result = new ArrayList<ReportVO>();
		result = mergeBlImpl.mergeReport(taskName);
		return result;
	}

	public List<ReportVO> getMergeReport(String taskname) {
		List<ReportVO> result = new ArrayList<ReportVO>();
		List<ReportPO> list = new ArrayList<ReportPO>();
		try {
			list = reportDataService.getMergeReport(taskname);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ReportPO po : list) {
			ReportVO vo = new ReportVO(po);
			result.add(vo);
		}
		return result;
	}

	@Override
	public boolean abandonReport(ReportVO vo) throws ClassNotFoundException, SQLException {
		ReportPO po = new ReportPO(vo);
		return reportDataService.abandonReport(po);
	}

	@Override
	public boolean recoverReport(ReportVO vo) throws ClassNotFoundException, SQLException {
		ReportPO po = new ReportPO(vo);
		return reportDataService.recoverReport(po);
	}

}
