package blservice;

import java.sql.SQLException;
import java.util.ArrayList;

import vo.ReportVO;

public interface SplitBlService {

	/**
	 * return all faults that have been found or merged
	 * @param	taskname
	 * @return	
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<ReportVO> show(String taskname) throws ClassNotFoundException, SQLException;
	
	/**
	 * choose a default and see the included defaults
	 * if the default chose an included default's description as its description, do not show the chosen default
	 * @param vo
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<ReportVO> choose(ReportVO vo) throws ClassNotFoundException, SQLException;
	
	/**
	 * choose defaults in the default-list and split them
	 * we need to show the list again
	 * @param vos	faults that should be separated from the fault
	 * @param vo	fault that is separated
	 * @return	whether the split is done
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean split(ArrayList<ReportVO> vos, ReportVO vo) throws ClassNotFoundException, SQLException;
}
