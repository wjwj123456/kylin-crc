/**
* @author       lpt14
* @version      V1.0
*/
package vo;

import java.util.Arrays;

import po.UserInfoPO;

/**
 * TODO: 锛堢被鎻忚堪锛�
 *
 * @author lpt14
 * @since 2016骞�7鏈�19鏃�
 * @see
 */
public class UserInfoVO {
	private String userName;
	private String trueName;
	private Sex sex;
	private String job;
	private String province;
	private String city;
	private String description;
	private String picture;
	private Language[] languages;

	public UserInfoVO(String userName, String trueName, Sex sex, String job, String province, String city,
			String description, String picture, Language[] languages) {
		super();
		this.userName = userName;
		this.trueName = trueName;
		this.sex = sex;
		this.job = job;
		this.province = province;
		this.city = city;
		this.description = description;
		this.picture = picture;
		this.languages = languages;
	}
	
	public UserInfoVO(UserInfoPO po) {
		this.userName = po.getUserName();
		this.trueName = po.getTrueName();
		this.sex = po.getSex();
		this.job = po.getJob();
		this.province = po.getProvince();
		this.city = po.getCity();
		this.description = po.getDescription();
		this.picture = po.getPicture();
		this.languages = po.getLanguages();
	}

	@Override
	public String toString() {
		return "UserInfoVO [userName=" + userName + ", trueName=" + trueName + ", sex=" + sex + ", job=" + job
				+ ", province=" + province + ", city=" + city + ", description=" + description + ", picture=" + picture
				+ ", languages=" + Arrays.toString(languages) + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Language[] getLanguages() {
		return languages;
	}

	public void setLanguages(Language[] languages) {
		this.languages = languages;
	}

}
