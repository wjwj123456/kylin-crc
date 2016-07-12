package bltest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bl.UserBlImpl;

public class UserBlTest {
	@Before
	public void setUp() throws Exception {
	}

	private UserBlImpl userBlImpl = new UserBlImpl();

	@Test
	public void testGetUserPOByName() {
		assertEquals("aoliao", userBlImpl.getUserVOByName("aoliao"));
	}

}
