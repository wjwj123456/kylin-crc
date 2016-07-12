package vo;

import java.io.Serializable;

import po.ReportPO;

public class ReportVO implements Serializable {
	private String taskName;
	private String userName;
	private String fileName;
	private int page;
	private int location;
	private String description;
	private int state;
	private int origin;

	public ReportVO(String taskName, String userName, String fileName, int page, int location, String description,
			int state, int origin) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.fileName = fileName;
		this.page = page;
		this.location = location;
		this.description = description;
		this.state = state;
		this.origin = origin;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public ReportVO(ReportPO po) {
		this.taskName = po.getTaskName();
		this.userName = po.getUserName();
		this.description = po.getDescription();
		this.location = po.getLocation();
		this.fileName = po.getFileName();
		this.page = po.getPage();
		this.state = po.getState();
		this.origin = po.getOrigin();
	}

	public ReportVO(String taskName, String userName, String fileName, int page, int location, String description) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.fileName = fileName;
		this.page = page;
		this.location = location;
		this.description = description;
	}

}
