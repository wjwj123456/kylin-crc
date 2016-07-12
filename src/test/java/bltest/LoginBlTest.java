
package bltest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import data.LoginDataImpl;

public class LoginBlTest {
	@Before
	public void setUp() throws Exception {
	}

	private LoginDataImpl loginDataImpl = new LoginDataImpl();

	@Test
	public void testCreateAccount() throws ClassNotFoundException, SQLException {
		// assertEquals(0,loginDataImpl.createAccount("cr", "aaaaaaad", "123"));
		// assertEquals(1,loginDataImpl.createAccount("crc", "aaaaaaad",
		// "123"));
		assertEquals(0, loginDataImpl.createAccount("crccc", "aaaaaaad", "123"));
	}

	@Test
	public void testVerifyAccount() throws ClassNotFoundException, SQLException {
		// assertEquals(0,loginDataImpl.verifyAccount("crc", "123") );
		// assertEquals(0,loginDataImpl.verifyAccount("aoliao", "aoliao123") );
	}

	@Test
	public void testChangePassword() throws ClassNotFoundException, SQLException {
		// assertEquals(0,loginDataImpl.changePassword("aoliao", "123"));
		// assertEquals(2,loginDataImpl.verifyAccount("aoliao", "aoliao123") );

	}
}
