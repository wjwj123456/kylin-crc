package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataservice.AssessmentDataService;
import po.AssessmentPO;
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
		int[][] matrix = getMatix(taskName, vos);
		crc = new CrcModule(matrix);
		return crc.getDefectsNum();
	}

	private int[][] getMatix(String taskName, List<ReportVO> vos) throws SQLException, ClassNotFoundException {
		List<String> nameList = new ArrayList<String>();
		nameList = getReviewerNames(taskName);

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

		return matrix;
	}

	private List<String> getReviewerNames(String taskName) throws SQLException, ClassNotFoundException {

		List<String> nameList = new ArrayList<String>();
		// for (ReportVO vo : vos) {
		// String sql = "SELECT included_id FROM merge where final_id = " +
		// mergeDataImpl.getID(new ReportPO(vo));
		// rSet = DBManager.getResultSet(sql);
		//
		// while (rSet.next()) {
		// String name = getUNameById(rSet.getInt(1));
		// if (!nameList.contains(name))
		// nameList.add(name);
		// }
		// DBManager.closeConnection();
		// }
		String sql = "SELECT id,uname FROM history WHERE tname = '" + taskName + "' ORDER BY id";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next())
			nameList.add(rSet.getString(2));
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

	@Override
	public int[][] getHistoryValues(String taskName) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Integer> faults = new ArrayList<Integer>();
		List<Integer> assessfaults = new ArrayList<Integer>();
		String sql = "SELECT fault,assessfault FROM history where tname = '" + taskName + "'";
		rSet = DBManager.getResultSet(sql);
		while (rSet.next()) {
			faults.add(rSet.getInt(1));
			assessfaults.add(rSet.getInt(2));
		}
		int[][] values = new int[2][faults.size()];
		for (int i = 0; i < faults.size(); i++) {
			values[0][i] = faults.get(i);
			values[1][i] = assessfaults.get(i);
		}

		DBManager.closeConnection();
		return values;
	}

	@Override
	public List<AssessmentPO> getAllAssessments(String taskName, List<ReportVO> vos)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<AssessmentPO> pos = new ArrayList<AssessmentPO>();

		List<String> nameList = new ArrayList<String>();
		nameList = getReviewerNames(taskName);
		if (nameList.size() > 1) {
			int[][] matrix = getMatix(taskName, vos);
			CrcModule crcM = new CrcModule(matrix);
			int assessfault = crcM.getDefectsNum();

			for (int i = 0; i < nameList.size(); i++) {
				int findedfault = 0;
				for (int j = 0; j < matrix.length; j++) {
					if (matrix[j][i] == 1)
						findedfault++;
				}
				pos.add(new AssessmentPO(nameList.get(i), assessfault, findedfault));
			}
		} else {

		}
		return pos;
	}

}
