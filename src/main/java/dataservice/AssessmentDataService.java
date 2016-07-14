package dataservice;

import java.sql.SQLException;
import java.util.List;

import vo.ReportVO;

public interface AssessmentDataService {
	/**
	 * ldk
	 * 
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int getAssessmentValue(String taskName, List<ReportVO> vos) throws SQLException, ClassNotFoundException;

	/**
	 * ldk
	 * 
	 * @param taskName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int[][] getHistoryValues(String taskName) throws SQLException, ClassNotFoundException;
}
