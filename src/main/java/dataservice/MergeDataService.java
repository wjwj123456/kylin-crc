  /**
 * @author       lpt14
 * @version      V1.0
 */
package dataservice;

import java.util.List;

import po.ReportPO;

/**
* TODO: ����������
*
* @author lpt14
* @since 2016��7��10��
* @see
*/
public interface MergeDataService {
	
		public boolean canMerge(String userName,String taskName);

		public List<ReportPO> mergeReport(String userNme,String taskName);
		 
		public void saveMergeReport(List<ReportPO> reportList);
		
	

}
