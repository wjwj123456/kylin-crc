package vo;

public class Message {

	/*
	 * status	0 means failure, 1 means success
	 * inform	"undefined", path + "dbfail"
	 */
	private int status;
	private String inform;
	
	public Message() {
		this.setStatus(1);
		this.setInform("undefined");
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}
	
}
