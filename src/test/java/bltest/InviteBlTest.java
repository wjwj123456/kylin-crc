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
		assertEquals(0,inviteBlImpl.deleteInvitationInfo("aoliao", "task2"));
	}
}
