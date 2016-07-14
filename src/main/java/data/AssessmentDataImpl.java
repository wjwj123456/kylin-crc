package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataservice.AssessmentDataService;
import po.ReportPO;
import tools.CrcModule;
import vo.ReportVO;

public class AssessmentDataImpl implements AssessmentDataService {
	private CrcModule crc = null;
	private ResultSet rSet = null;
	private MergeDataImpl mergeDataImpl = new MergeDataImpl();

	@Override
	public int getAssessmentValue(String taskName, List<ReportVO> vos) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<String> nameList = new ArrayList<String>();
		nameList = getReviewerNames(vos);

		int[][] matrix = new int[vos.size()][nameList.size()];

		int i = 0;
		int j = 0;
		for (i = 0; i < vos.size(); i++) {
			for (j = 0; j < nameList.size(); j++) {
				matrix[i][j] = 0;
			}
		}
		i = 0;
		j = 0;
		for (ReportVO vo : vos) {

			String sql = "SELECT included_id FROM merge where final_id = " + mergeDataImpl.getID(new ReportPO(vo));
			rSet = DBManager.getResultSet(sql);
			if (!rSet.next()) {
				String name = "";
				name = getUNameById(mergeDataImpl.getID(new ReportPO(vo)));
				j = nameList.indexOf(name);
				matrix[i][j] = 1;
			} else {
				int includedId = rSet.getInt(1);
				String name = "";
				name = getUNameById(includedId);
				while (rSet.next()) {
					j = nameList.indexOf(name);
					matrix[i][j] = 1;
				}
			}
			i++;
			DBManager.closeConnection();
		}

		crc = new CrcModule(matrix);
		return (int) (crc.getMhCH() + crc.getMtCH());
	}

	public List<String> getReviewerNames(List<ReportVO> vos) throws SQLException, ClassNotFoundException {

		List<String> nameList = new ArrayList<String>();
		for (ReportVO vo : vos) {
			String sql = "SELECT included_id FROM merge where final_id = " + mergeDataImpl.getID(new ReportPO(vo));
			rSet = DBManager.getResultSet(sql);

			while (rSet.next()) {
				String name = getUNameById(rSet.getInt(1));
				if (!nameList.contains(name))
					nameList.add(name);
			}
			DBManager.closeConnection();
		}

		return nameList;
	}

	public String getUNameById(int id) throws SQLException, ClassNotFoundException {
		String name = "";
		String sql = "SELECT uname FROM report where id = " + id;
		Connection connection = DBManager.connect();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			name = rs.getString(1);
		}
		DBManager.stopAll(rs, ps, connection);
		return name;
	}

}
