package bltest;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bl.InviteBlImpl;


public class InviteBlTest {
	@Before
	public void setUp() throws Exception {
	}
	private InviteBlImpl inviteBlImpl = new InviteBlImpl();
	@Test
	public void testGetInvitationInfo() {
		
		assertEquals(1,inviteBlImpl.getInvitationInfo("aoliao").size());
	}

	@Test
	public void testDeleteInvitationInfo() {
//		assertEquals(0,inviteBlImpl.deleteInvitationInfo("aoliao", "task2"));
	}
	
	
	@Test
	public void testGetAllDoingTask() {
		assertEquals(4,inviteBlImpl.getAllDoingTask("crc").size());
	}

	@Test
	public void testGetAllCompleteTask() {
		assertEquals(1,inviteBlImpl.getAllCompleteTask("crc").size());
	}

	@Test
	public void testGetAgreeUser() {
		assertEquals(1,inviteBlImpl.getAgreeUser("task1").size());
	}

	@Test
	public void testGetDisagreeUser() {
		assertEquals(1,inviteBlImpl.getDisagreeUser("task1").size());
	}
}
