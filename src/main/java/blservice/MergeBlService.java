/**
* @author       lpt14
* @version      V1.0
*/
package blservice;

import java.util.List;

import vo.ReportVO;

/**
 * TODO: ����������
 *
 * @author lpt14
 * @since 2016��7��11��
 * @see
 */
public interface MergeBlService {
	public boolean canMerge(String taskName);

	public List<ReportVO> mergeReport(String taskName);

	public void saveMergeReport(List<ReportVO> reportList, String taskName);

	public void saveAddedMergeReport(List<ReportVO> reportList, String taskName);

	public void deleteMergeRecord(ReportVO vo);

	public void saveHistory(String userName, String taskName);

}
