package blservice;

import java.util.ArrayList;

import vo.ReportVO;

public interface SplitBlService {

	/**
	 * return all faults that have been found or merged
	 * @param	taskname
	 * @return	
	 */
	public ArrayList<ReportVO> show(String taskname);
	
	/**
	 * choose a default and see the included defaults
	 * if the default chose an included default's description as its description, do not show the chosen default
	 * @param vo
	 * @return
	 */
	public ArrayList<ReportVO> choose(ReportVO vo);
	
	/**
	 * choose defaults in the default-list and split them
	 * we need to show the list again
	 * @param vos	faults that should be separated from the fault
	 * @param vo	fault that is separated
	 * @return	whether the split is done
	 */
	public boolean split(ArrayList<ReportVO> vos, ReportVO vo);
}
