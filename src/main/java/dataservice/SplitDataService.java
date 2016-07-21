package dataservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.ReportPO;

public interface SplitDataService {

	/**
	 * return all faults with the same task name in the report table
	 * @param taskname
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<ReportPO> getFaultsByTaskname(String taskname) throws ClassNotFoundException, SQLException;
	
	/**
	 * return all faults that were included in the fault chosen
	 * @param po
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<ReportPO> getIncludedfaultsByFaultkey(ReportPO po) throws ClassNotFoundException, SQLException;
	
	
	/**
	 * split faults from the fault
	 * @param pos
	 * @param po
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean splitFaults(ArrayList<ReportPO> pos, ReportPO po) throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * @param taskname
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<ReportPO> getCanSplitReports(String taskname) throws ClassNotFoundException, SQLException;
	
	
	/**
	 * 
	 * @param pos
	 * @param po
	 * @return
	 */
	public int splitForUndoMerge(List<ReportPO> pos, ReportPO po);
}
