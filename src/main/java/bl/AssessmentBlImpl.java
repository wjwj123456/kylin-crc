package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.AssessmentBlService;
import data.AssessmentDataImpl;
import dataservice.AssessmentDataService;
import po.AssessmentPO;
import vo.AssessmentVO;
import vo.ReportVO;

public class AssessmentBlImpl implements AssessmentBlService {

	private AssessmentDataService assessmentDataService = new AssessmentDataImpl();
	private ReportBlImpl reportBlImpl = new ReportBlImpl();

	@Override
	public int[] getHistoryAssessmentValues_Mt(String taskName) {
		// TODO Auto-generated method stub
		int[] i = new int[1];
		try {
			return assessmentDataService.getHistoryValues(taskName)[1];
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}
	}

	@Override
	public int[] getHistoryAssessmentValues_Mh(String taskName) {
		// TODO Auto-generated method stub
		int[] i = new int[1];
		try {
			return assessmentDataService.getHistoryValues(taskName)[2];
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}
	}

	@Override
	public int[] getHistoryFaultValues(String taskName) {
		// TODO Auto-generated method stub
		int[] i = new int[1];
		try {
			return assessmentDataService.getHistoryValues(taskName)[0];
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return i;
		}
	}

	public int[] getAssessmentValue(String taskName, List<ReportVO> vos) {
		int[] counts = { 0, 0 };
		try {
			counts = assessmentDataService.getAssessmentValue(taskName, vos);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counts;
	}

	@Override
	public List<AssessmentVO> getAllAssessments(String taskName) {
		// TODO Auto-generated method stub
		List<ReportVO> vos = reportBlImpl.getMergeReport(taskName);
		List<AssessmentVO> aVos = new ArrayList<AssessmentVO>();
		List<AssessmentPO> pos = new ArrayList<AssessmentPO>();
		try {
			pos = assessmentDataService.getAllAssessments(taskName, vos);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (AssessmentPO po : pos)
			aVos.add(new AssessmentVO(po));

		return aVos;
	}

	@Override
	public List<List<String>> getAllFindedReviewerNames(List<ReportVO> vos) {
		// TODO Auto-generated method stub
		List<List<String>> names = new ArrayList<List<String>>();
		try {
			names = assessmentDataService.getAllFindedReviewerNames(vos);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}

}
