package vo;

public class Message {

	/*
	 * status	0 means failure, 1 means success
	 * inform	"undefined"
	 * 			path + "dbfail"
	 * 			"length_differ"
	 * 			path2 + "repeat_changed_to" + path1
	 */
	private int status;
	private String inform;
	
	public Message() {
		setStatus(1);
		setInform("undefined");
	}
	
	private void setStatus(int status) {
		this.status = status;
	}

	public void setFail() {
		setStatus(0);	
	}
	
	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}
	
	public boolean isSuccess() {
		if(status == 0) return false;
		else	return true;
	}
	
}
