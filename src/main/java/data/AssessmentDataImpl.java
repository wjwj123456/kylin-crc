package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dataservice.AssessmentDataService;
import po.ReportPO;
import tools.CrcModule;
import vo.ReportVO;

public class AssessmentDataImpl implements AssessmentDataService {
	private CrcModule crc = null;
	private PreparedStatement pStatement;
	private ResultSet rSet = null;
	private MergeDataImpl mergeDataImpl = new MergeDataImpl();

	@Override
	public int getAssessmentValue(String taskName, List<ReportVO> vos) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		for (ReportVO vo : vos) {

			String sql = "SELECT included_id FROM merge where final_id = " + mergeDataImpl.getID(new ReportPO(vo));
		}
		int[][] matrix = new int[vos.size()][];
		crc = new CrcModule(matrix);
		return 0;
	}

}
