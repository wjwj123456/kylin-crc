  /**
 * @author       lpt14
 * @version      V1.0
 */
package bltest;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bl.InviteBlImpl;
import bl.MergeBlImpl;
import blservice.MergeBlService;
import vo.ReportVO;

/**
* TODO: （类描述）
*
* @author lpt14
* @since 2016年7月11日
* @see
*/
public class MergeBlTest {
	@Before
	public void setUp() throws Exception {
	}
	private MergeBlService mergeBlService = new MergeBlImpl();
	/**
	 * Test method for {@link blservice.MergeBlService#canMerge(java.lang.String)}.
	 */
	@Test
	public void testCanMerge() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link blservice.MergeBlService#mergeReport(java.lang.String)}.
	 */
	@Test
	public void testMergeReport() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link blservice.MergeBlService#saveMergeReport(java.util.List, java.lang.String)}.
	 */
	@Test
	public void testSaveMergeReport() {
		List<ReportVO> vos = new ArrayList<ReportVO>(); 
		ReportVO vo1 = new ReportVO("task1", "aoliao", "Hello.java", 0, 6, "语法错误");
		ReportVO vo2 = new ReportVO("task1", "cr", "Hello.java", 0, 5, "语法错误");
		ReportVO vo3 = new ReportVO("task1", "iii", "Hello.java", 0, 6, "yus");
		ReportVO vo4 = new ReportVO("task1", "iiii", "Hello.java", 0, 6, "yus");
		
		vos.add(vo1);
		vos.add(vo2);
		vos.add(vo3);
		vos.add(vo4);
		mergeBlService.saveAddedMergeReport(vos, "task1");
	}

	/**
	 * Test method for {@link blservice.MergeBlService#saveAddedMergeReport(java.util.List, java.lang.String)}.
	 */
	@Test
	public void testSaveAddedMergeReport() {
		List<ReportVO> vos = new ArrayList<ReportVO>(); 
		ReportVO vo1 = new ReportVO("task1", "aoliao", "Hello.java", 0, 6, "语法错误");
		ReportVO vo2 = new ReportVO("task1", "cr", "Hello.java", 0, 5, "语法错误");
		ReportVO vo3 = new ReportVO("task1", "iii", "Hello.java", 0, 6, "yus");
		ReportVO vo4 = new ReportVO("task1", "iiii", "Hello.java", 0, 6, "yus");
		
		vos.add(vo1);
		vos.add(vo2);
		vos.add(vo3);
		vos.add(vo4);
		mergeBlService.saveAddedMergeReport(vos, "task1");
	}
}
