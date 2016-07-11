package bltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
		TaskVO vo = new TaskVO("crc", "crc1", Type.code, "����", "��", Tools.stringToDate("2016-08-08 12:12:12"), 0);
		// assertEquals(0,reviewBlImpl.saveReviewInfo(vo) );

	}

	@Test
	public void testGeTaskList() {
		assertNotEquals(0, reviewBlImpl.geTaskList("crc").size());
		assertEquals(0, reviewBlImpl.geTaskList("fdjsjf").size());
	}

	@Test
	public void testSearchUserByKeyword() {
		// assertEquals(1, reviewBlImpl.searchUserByKeyword("crc").size());
		// assertEquals(2, reviewBlImpl.searchUserByKeyword("c").size());
		assertEquals(0, reviewBlImpl.searchUserByKeyword("iii").size());
		assertNotEquals(0, reviewBlImpl.searchUserByKeyword("crc").size());
		assertNotEquals(0, reviewBlImpl.searchUserByKeyword("c").size());
	}

	@Test
	public void testSaveReviewer() {

	}

	@Test
	public void testSaveInvitation() {
		String taskName = "task1";
		String[] names = new String[2];
		names[0] = "aoliao";
		names[1] = "cr";
		assertEquals(0, reviewBlImpl.saveInvitation(names, taskName));
	}

	@Test
	public void testSaveAcceptReviewer() {
		String taskName = "task1";
		String userName = "aoliao";

		assertEquals(0, reviewBlImpl.saveAcceptReviewer(userName, taskName));

		String userName2 = "cr";

		assertEquals(0, reviewBlImpl.saveAcceptReviewer(userName2, taskName));
	}

	@Test
	public void testGetTaskVOByTaskName() {

		assertEquals("task1", reviewBlImpl.getTaskVOByTaskName("task1").getTaskName());

		assertEquals("task2", reviewBlImpl.getTaskVOByTaskName("task2").getTaskName());
	}
}
