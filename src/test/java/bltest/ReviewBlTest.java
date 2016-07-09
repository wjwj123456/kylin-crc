package bltest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import bl.ReviewBlImpl;
import tools.Tools;
import vo.TaskVO;
import vo.Type;

public class ReviewBlTest {
	@Before
	public void setUp() throws Exception {
	}
	
	private ReviewBlImpl reviewBlImpl = new ReviewBlImpl();
	@Test
	public void testSaveReviewInfo() {
		TaskVO vo = new TaskVO("crc", "crc1", Type.code, "¹¤³Ì", "ÎÞ", Tools.stringToDate("2016-08-08 12:12:12"), 0);
//		assertEquals(0,reviewBlImpl.saveReviewInfo(vo) );

	}

	@Test
	public void testGeTaskList() {
		assertNotEquals(0, reviewBlImpl.geTaskList("crc").size());
		assertEquals(0, reviewBlImpl.geTaskList("fdjsjf").size());
	}

	@Test
	public void testSearchUserByKeyword() {
		assertEquals(1, reviewBlImpl.searchUserByKeyword("crc").size());
		assertEquals(2, reviewBlImpl.searchUserByKeyword("c").size());
		assertEquals(0, reviewBlImpl.searchUserByKeyword("iii").size());
	
	}

}
