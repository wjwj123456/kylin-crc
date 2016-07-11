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
* @since 2016Äê7ÔÂ10ÈÕ
* @see
*/
public interface MergeDataService {
		/**
		 * judge is it the first one submitted
		 * @param userName
		 * @param taskName
		 * @return boolean
		 * @author      lpt14
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public boolean canMerge(String taskName) throws SQLException, ClassNotFoundException;
		/**
		 * give ui the list to merge
		 * @param userNme
		 * @param taskName    
		 * @return List<ReportPO>   
		 * @author      lpt14
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public List<ReportPO> mergeReport(String taskName) throws SQLException, ClassNotFoundException;
		 /**
		  * save merge record
		  * @param reportList      
		  * @return void
		  * @author      lpt14
		  */
		public void saveMergeReport(List<ReportPO> reportList);
		
	

}
