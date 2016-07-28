package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.SplitBlService;
import data.SplitDataImpl;
import dataservice.SplitDataService;
import po.ReportPO;
import vo.ReportVO;

public class SplitBlImpl implements SplitBlService{
	
	private SplitDataService splitDataService;
	
	public SplitBlImpl() {
		this.splitDataService = new SplitDataImpl();
	}

	public ArrayList<ReportVO> show(String taskname) throws ClassNotFoundException, SQLException {
		ArrayList<ReportVO> reportVOs = new ArrayList<ReportVO>();
		ArrayList<ReportPO> reportPOs = splitDataService.getFaultsByTaskname(taskname);
		for(ReportPO po:reportPOs) {
			ReportVO vo = new ReportVO(po);
			reportVOs.add(vo);
		}
		return reportVOs;
	}

	public ArrayList<ReportVO> choose(ReportVO vo) throws ClassNotFoundException, SQLException {
		ArrayList<ReportVO> reportVOs = new ArrayList<ReportVO>();
		ReportPO reportPO = new ReportPO(vo);
		ArrayList<ReportPO> reportPOs = splitDataService.getIncludedfaultsByFaultkey(reportPO);
		for(ReportPO po:reportPOs) {
			ReportVO rvo = new ReportVO(po);
			reportVOs.add(rvo);
		}
		return reportVOs;
	}

	public boolean split(ArrayList<ReportVO> vos, ReportVO vo, String operator) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> reportPOs = new ArrayList<ReportPO>();
		for(ReportVO reportVO: vos) {
			ReportPO reportPO = new ReportPO(reportVO);
			reportPOs.add(reportPO);
		}
		ReportPO po = new ReportPO(vo);
		boolean isSuc = splitDataService.splitFaults(reportPOs, po, operator);
		return isSuc;
	}

	@Override
	public ArrayList<ReportVO> getCanSplitedReports(String taskname) throws ClassNotFoundException, SQLException {
		ArrayList<ReportVO> reportVOs = new ArrayList<>();
		ArrayList<ReportPO> reportPOs = splitDataService.getCanSplitReports(taskname);
		for(ReportPO po:reportPOs) {
			ReportVO vo = new ReportVO(po);
			reportVOs.add(vo);
		}
		return reportVOs;
	}

	@Override
	public int splitForUndoMerge(List<ReportVO> vos, ReportVO vo, List<String> operators) {
		List<ReportPO> pos = new ArrayList<>();
		for(ReportVO vo0: vos) {
			pos.add(new ReportPO(vo0));
		}
		int i = 1;
		try {
			i = splitDataService.splitForUndoMerge(pos, new ReportPO(vo), operators);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
