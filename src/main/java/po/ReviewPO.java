package po;

import java.util.Calendar;

import vo.Type;

public class ReviewPO {
	String name;
	Type type;
	String project;
	String describe;
	Calendar deadline;
	public ReviewPO(String name, Type type, String project, String describe, Calendar deadline) {
		super();
		this.name = name;
		this.type = type;
		this.project = project;
		this.describe = describe;
		this.deadline = deadline;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Calendar getDeadline() {
		return deadline;
	}
	public void setDeadline(Calendar deadline) {
		this.deadline = deadline;
	}
	
	
}
