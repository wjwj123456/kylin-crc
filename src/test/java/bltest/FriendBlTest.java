package bltest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bl.FriendBlImpl;

public class FriendBlTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetFriends() {

	}

	@Test
	public void testAddFriend() {
		FriendBlImpl friendBlImpl = new FriendBlImpl();

		assertEquals(0, friendBlImpl.addFriend("aoliao", "crc"));
	}

}
