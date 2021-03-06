package vo;

import java.io.Serializable;

/**
 * Created by lpt on 16-7-8.
 */
public class UserVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9125368302077343977L;
	private String name;
	private String email;
	private String password;

	public UserVO(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
