package datatest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import data.AssessmentDataImpl;
import data.ReportDataImpl;
import po.ReportPO;
import vo.ReportVO;

public class AssessmentDataTest {
	@Before
	public void setUp() throws Exception {
	}

	private AssessmentDataImpl assessmentDataImpl = new AssessmentDataImpl();
	private ReportDataImpl reportDataImpl = new ReportDataImpl();

	@Test
	public void testGetAssessmentValue() {

	}

	@Test
	public void testGetReviewerNames() {
		try {
			List<ReportPO> pos = reportDataImpl.getAllReportsByTaskName("task1");
			List<ReportVO> vos = new ArrayList<ReportVO>();
			for (ReportPO po : pos) {
				vos.add(new ReportVO(po));
				System.out.println(po.getUserName());
			}
			System.out.println("---------------------");
			List<String> list = assessmentDataImpl.getReviewerNames(vos);
			for (String name : list) {
				System.out.println(name);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testGetUNameById() {
		try {
			assertEquals("aoliao", assessmentDataImpl.getUNameById(12));
			assertEquals("cr", assessmentDataImpl.getUNameById(20));
			assertEquals("cr", assessmentDataImpl.getUNameById(13));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
