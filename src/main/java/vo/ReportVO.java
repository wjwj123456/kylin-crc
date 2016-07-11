package vo;

import po.ReportPO;

public class ReportVO {
	String taskName;
	String userName;
	String fileName;
	int page;
	int location;
	String description;
	
	
	public ReportVO(String taskName, String userName, String fileName, int page, int location, String description) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.fileName = fileName;
		this.page = page;
		this.location = location;
		this.description = description;
	}
	
	public ReportVO(ReportPO po) {
		super();
		this.taskName = po.getTaskName();
		this.userName = po.getUserName();
		this.fileName = po.getFileName();
		this.page = po.getPage();
		this.location = po.getLocation();
		this.description = po.getDescription();
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
