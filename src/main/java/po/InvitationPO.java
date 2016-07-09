/**
* @author       lpt14
* @version      V1.0
*/
package po;

/**
 * TODO: ����������
 *
 * @author lpt14
 * @since 2016��7��9��
 * @see
 */
public class InvitationPO {
	TaskPO taskPO;
	String url;

	public InvitationPO(TaskPO taskPO, String url) {
		super();
		this.taskPO = taskPO;
		this.url = url;
	}

	public TaskPO getTaskPO() {
		return taskPO;
	}

	public void setTaskPO(TaskPO taskPO) {
		this.taskPO = taskPO;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOwnerName() {
		return taskPO.getUserName();
	}

	public String getTaskName() {
		return taskPO.getTaskName();
	}

}
