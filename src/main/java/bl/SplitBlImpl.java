package bl;

import java.sql.SQLException;
import java.util.ArrayList;

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

	public boolean split(ArrayList<ReportVO> vos, ReportVO vo) throws ClassNotFoundException, SQLException {
		ArrayList<ReportPO> reportPOs = new ArrayList<ReportPO>();
		for(ReportVO reportVO: vos) {
			ReportPO reportPO = new ReportPO(reportVO);
			reportPOs.add(reportPO);
		}
		ReportPO po = new ReportPO(vo);
		boolean isSuc = splitDataService.splitFaults(reportPOs, po);
		return isSuc;
	}

}
