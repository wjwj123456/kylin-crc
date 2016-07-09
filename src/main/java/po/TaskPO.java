package po;


import java.util.Date;

import vo.Type;

public class TaskPO {
	String userName;
	String taskName;
	Type type;
	String project;
	String describe;
	Date deadline;
	int state;
	public TaskPO(String userName, String taskName, Type type, String project, String describe, Date deadline,
			int state) {
		super();
		this.userName = userName;
		this.taskName = taskName;
		this.type = type;
		this.project = project;
		this.describe = describe;
		this.deadline = deadline;
		this.state = state;
	}
	
	
	
	
	public TaskPO(String userName, String taskName) {
		super();
		this.userName = userName;
		this.taskName = taskName;
	}




	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
