  /**
 * @author       lpt14
 * @version      V1.0
 */
package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.MergeBlService;
import data.MergeDataImpl;
import dataservice.MergeDataService;
import po.ReportPO;
import vo.ReportVO;

/**
* TODO: （类描述）
*
* @author lpt14
* @since 2016年7月11日
* @see
*/
public class MergeBlImpl implements MergeBlService{
	MergeDataService mergeDataService=new MergeDataImpl();
	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月11日
	* @param taskName
	* @return
	* @see blservice.MergeBlService#canMerge(java.lang.String)
	*
	*/
	public boolean canMerge(String taskName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			flag = mergeDataService.canMerge(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月11日
	* @param taskName
	* @return
	* @see blservice.MergeBlService#mergeReport(java.lang.String)
	*
	*/
	public List<ReportVO> mergeReport(String taskName) {
		// TODO Auto-generated method stub
		List<ReportVO> voList=new ArrayList<ReportVO>();
		List<ReportPO> poList=null;
		try {
			poList=mergeDataService.mergeReport(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(ReportPO po: poList){
			voList.add(new ReportVO(po));
		}
		return voList;
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月11日
	* @param reportList
	* @see blservice.MergeBlService#saveMergeReport(java.util.List)
	*
	*/
	public void saveMergeReport(List<ReportVO> reportList,String taskName) {
		List<ReportPO> list=new ArrayList<ReportPO>();
		for(ReportVO vo:reportList){
			list.add(new ReportPO(vo));
		}
		try {
			mergeDataService.saveMergeReport(list,taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
		
	}

	/**
	* TODO:（方法描述）
	*
	* @author lpt14
	* @since 2016年7月11日
	* @param reportList
	* @param taskName
	* @see blservice.MergeBlService#saveAddedMergeReport(java.util.List, java.lang.String)
	*
	*/
	public void saveAddedMergeReport(List<ReportVO> reportList, String taskName) {
		// TODO Auto-generated method stub
		List<ReportPO> list=new ArrayList<ReportPO>();
		for(ReportVO vo:reportList){
			list.add(new ReportPO(vo));
		}
		try {
			mergeDataService.saveAddedMergeReport(list,taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

}
