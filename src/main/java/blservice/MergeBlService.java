  /**
 * @author       lpt14
 * @version      V1.0
 */
package blservice;

import java.sql.SQLException;
import java.util.List;

import po.ReportPO;
import vo.ReportVO;

/**
* TODO: ����������
*
* @author lpt14
* @since 2016��7��11��
* @see
*/
public interface MergeBlService {
	public boolean canMerge(String taskName) ;
	public List<ReportVO> mergeReport(String taskName);
	public void saveMergeReport(List<ReportVO> reportList,String taskName);
	public void saveAddedMergeReport(List<ReportVO> reportList, String taskName);
		
	
	
}
