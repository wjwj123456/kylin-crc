/**
* @author       lpt14
* @version      V1.0
*/
package dataservice;

import java.sql.SQLException;
import java.util.List;

import po.ReportPO;

/**
 * merge report
 *
 * @author lpt14
 * @since 2016��7��10��
 * @see
 */
public interface MergeDataService {
	/**
	 * judge is it the first one submitted
	 * 
	 * @param userName
	 * @param taskName
	 * @return boolean
	 * @author lpt14
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean canMerge(String taskName) throws SQLException, ClassNotFoundException;

	/**
	 * give ui the list to merge
	 * 
	 * @param userNme
	 * @param taskName
	 * @return List<ReportPO>
	 * @author lpt14
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<ReportPO> mergeReport(String taskName) throws SQLException, ClassNotFoundException;

	/**
	 * save merge record
	 * 
	 * @param reportList
	 * @return void
	 * @author lpt14
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int saveMergeReport(List<ReportPO> reportList, String taskName) throws SQLException, ClassNotFoundException;

	public int saveAddedMergeReport(List<ReportPO> reportList, String taskName)
			throws SQLException, ClassNotFoundException;

	public int deleteMergeRecord(ReportPO po) throws SQLException, ClassNotFoundException;

	public int saveHistory(String userName, String taskName, int fault, int assessfalut_mt, int assessfalut_mh)
			throws SQLException, ClassNotFoundException;

	public int getID(ReportPO po) throws ClassNotFoundException, SQLException;

	public int saveMergeState(String userName, String taskName) throws SQLException;

	/**
	 * @param @param
	 *            po
	 * @param @return
	 * @param @throws
	 *            SQLException
	 * @param @throws
	 *            ClassNotFoundException
	 * @return int
	 * @throws @author
	 *             lpt14
	 */
	public int recoverMergeRecord(ReportPO po) throws SQLException, ClassNotFoundException;

}
