package bltest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bl.ReportBlImpl;
import vo.ReportVO;

public class ReportBlTest {
	@Before
	public void setUp() throws Exception {
	}

	private ReportBlImpl reportBlImpl = new ReportBlImpl();

	@Test
	public void testCreateReport() {
		List<ReportVO> vos = new ArrayList<ReportVO>();
		ReportVO vo1 = new ReportVO("task1", "aoliao", "Hello.java", 0, 9, "”Ô∑®¥ÌŒÛ", 0, 0);
		ReportVO vo2 = new ReportVO("task1", "cr", "Hello.java", 0, 9, "”Ô∑®¥ÌŒÛ", 0, 0);
		vos.add(vo1);
		vos.add(vo2);
		assertEquals(0, reportBlImpl.createReport(vos));
	}

	@Test
	public void testSetCompleteTime() {

		assertEquals(0, reportBlImpl.setCompleteTime("task1", "aoliao", 10.9));
	}
}
