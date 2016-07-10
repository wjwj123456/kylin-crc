package vo;

public class ReportVO {
	String taskName;
	String userName;
	String location;
	String description;
	
	public ReportVO(String taskName, String userName, String location, String description) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.location = location;
		this.description = description;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
