package po;

import java.util.Date;

import vo.Language;
import vo.Power;
import vo.TaskVO;
import vo.Type;

public class TaskPO {

	private String userName;
	private String taskName;
	private Type type;
	private String project;
	private String describe;
	private Date deadline;
	private int state;
	private Language language;
	private Power power;

	public TaskPO(String userName, String taskName, Type type, String project, String describe, Date deadline,
			int state, Language language, Power power) {
		super();
		this.userName = userName;
		this.taskName = taskName;
		this.type = type;
		this.project = project;
		this.describe = describe;
		this.deadline = deadline;
		this.state = state;
		this.language = language;
		this.power = power;
	}

	public TaskPO(String userName, String taskName) {
		super();
		this.userName = userName;
		this.taskName = taskName;
	}

	public TaskPO() {
		super();

	}

	/**
	 * TODO: @param vo
	 */
	public TaskPO(TaskVO vo) {
		// TODO Auto-generated constructor stub
		super();
		this.userName = vo.getUserName();
		this.taskName = vo.getTaskName();
		this.type = vo.getType();
		this.project = vo.getProject();
		this.describe = vo.getDescribe();
		this.deadline = vo.getDeadline();
		this.state = vo.getState();
		this.language = vo.getLanguage();
		this.power = vo.getPower();
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

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
