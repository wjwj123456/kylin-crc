
package bltest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
	public void testCreateAccount() {
	//	assertEquals(true,loginDataImpl.createAccount(userName, email, password));
	}

	@Test
	public void testVerifyAccount() throws ClassNotFoundException, SQLException {
		assertEquals(0,loginDataImpl.verifyAccount("crc", "123") );
	}

	@Test
	public void testChangePassword() {
	//	assertEquals(true,loginDataImpl.changeAccount(userName, password));
	}
}

