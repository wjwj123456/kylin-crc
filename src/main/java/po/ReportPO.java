package po;

import vo.ReportVO;

public class ReportPO {

	private String taskName;
	private String userName;
	private String fileName;
	private int page;
	private int location;
	private String description;
	private int state;
	private int origin;
	private int isMerged;
	private String operator;

	public ReportPO(String taskName, String userName, String fileName, int page, int location, String description,
			int state, int origin, int isMerged, String operator) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.fileName = fileName;
		this.page = page;
		this.location = location;
		this.description = description;
		this.state = state;
		this.origin = origin;
		this.isMerged = isMerged;
		this.operator = operator;
	}

	public ReportPO(String taskName, String userName, String fileName, int page, int location, String description,
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public ReportPO(ReportVO vo) {
		this.taskName = vo.getTaskName();
		this.userName = vo.getUserName();
		this.description = vo.getDescription();
		this.location = vo.getLocation();
		this.fileName = vo.getFileName();
		this.page = vo.getPage();
		this.state = vo.getState();
		this.origin = vo.getOrigin();
		this.isMerged = vo.getIsMerged();
		this.operator = vo.getOperator();
	}

	public int getIsMerged() {
		return isMerged;
	}

	public void setIsMerged(int isMerged) {
		this.isMerged = isMerged;
	}

	public ReportPO(String taskName, String userName, String fileName, int page, int location, String description) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.fileName = fileName;
		this.page = page;
		this.location = location;
		this.description = description;
	}

	public ReportPO(String taskName, String userName, String fileName, int page, int location, String description,
			int state, int origin, int isMerged) {
		super();
		this.taskName = taskName;
		this.userName = userName;
		this.fileName = fileName;
		this.page = page;
		this.location = location;
		this.description = description;
		this.state = state;
		this.origin = origin;
		this.isMerged = isMerged;
	}

	@Override
	public String toString() {
		return "ReportPO [taskName=" + taskName + ", userName=" + userName + ", fileName=" + fileName + ", page=" + page
				+ ", location=" + location + ", description=" + description + ", state=" + state + ", origin=" + origin
				+ ", isMerged=" + isMerged + "]";
	}

}
