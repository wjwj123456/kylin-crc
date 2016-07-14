/**
* @author       lpt14
* @version      V1.0
*/
package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blservice.MergeBlService;
import blservice.SplitBlService;
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
public class MergeBlImpl implements MergeBlService {
	MergeDataService mergeDataService = new MergeDataImpl();

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
		SplitBlService splitBlService = new SplitBlImpl();
		// TODO Auto-generated method stub
		List<ReportVO> voList = new ArrayList<ReportVO>();
		List<ReportPO> poList = new ArrayList<ReportPO>();
		try {
			poList = mergeDataService.mergeReport(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ReportPO po : poList) {
			voList.add(new ReportVO(po));
		}
		List<ReportVO> mergeList = new ArrayList<>();
		try {
			mergeList = splitBlService.getCanSplitedReports(taskName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ReportVO vo : voList) {
			for (ReportVO vo1 : mergeList) {
				try {
					if (mergeDataService.getID(new ReportPO(vo)) == mergeDataService.getID(new ReportPO(vo1))) {
						vo.setIsMerged(1);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (vo.getState() == 1) {
				voList.remove(vo);
			}
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
	public int saveMergeReport(List<ReportVO> reportList, String taskName) {
		int flag = 0;
		List<ReportPO> list = new ArrayList<ReportPO>();
		for (ReportVO vo : reportList) {
			list.add(new ReportPO(vo));
		}
		try {
			flag = mergeDataService.saveMergeReport(list, taskName);
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
	 * @param reportList
	 * @param taskName
	 * @see blservice.MergeBlService#saveAddedMergeReport(java.util.List,
	 *      java.lang.String)
	 *
	 */
	public int saveAddedMergeReport(List<ReportVO> reportList, String taskName) {
		int flag = 0;
		// TODO Auto-generated method stub
		List<ReportPO> list = new ArrayList<ReportPO>();
		for (ReportVO vo : reportList) {
			list.add(new ReportPO(vo));
		}
		try {
			flag = mergeDataService.saveAddedMergeReport(list, taskName);
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
	 * @since 2016年7月13日
	 * @param userName
	 * @param taskName
	 * @see blservice.MergeBlService#saveHistory(java.lang.String,
	 *      java.lang.String)
	 *
	 */
	@Override
	public int saveHistory(String userName, String taskName) {
		// TODO Auto-generated method stub
		int flag = 0;
		AssessmentBlImpl assessmentBlImpl = new AssessmentBlImpl();
		ReportBlImpl reportBlImpl = new ReportBlImpl();
		int assessFault = assessmentBlImpl.getAssessmentValue(taskName);
		int fault = reportBlImpl.getMergeReport(taskName).size();
		try {
			try {
				flag = mergeDataService.saveHistory(userName, taskName, fault, assessFault);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	 * @since 2016年7月14日
	 * @param vo
	 * @see blservice.MergeBlService#deleteMergeRecord(vo.ReportVO)
	 *
	 */
	@Override
	public int deleteMergeRecord(ReportVO vo) {
		int flag = 0;
		// TODO Auto-generated method stub
		ReportPO po = new ReportPO(vo);
		try {
			flag = mergeDataService.deleteMergeRecord(po);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
