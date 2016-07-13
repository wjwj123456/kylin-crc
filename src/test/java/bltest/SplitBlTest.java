package bltest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import bl.SplitBlImpl;
import blservice.SplitBlService;
import vo.ReportVO;

public class SplitBlTest {

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		SplitBlService service = new SplitBlImpl();
		ReportVO vo1 = new ReportVO("task1", "cr", "Hello.java", 0, 5, "�﷨����", 1, 0,0);
		ReportVO vo2 = new ReportVO("task1", "iii", "Hello.java", 0, 6, "yus", 1, 0, 0);
		ReportVO vo3 = new ReportVO("task1", "aoliao", "Hello.java", 0, 10, "�﷨����1", 0, 1, 0);
		ReportVO vo4 = new ReportVO("task1", "iiii", "Hello.java", 0, 6, "yus", 1, 0, 0);
		ArrayList<ReportVO> vos = new ArrayList<ReportVO>();
		vos.add(vo1);
		vos.add(vo2);
		vos.add(vo4);
		assertEquals(true, service.split(vos, vo3));
	}

}
