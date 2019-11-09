package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 * 
 * @author 峰
 * 
 * @version 0.0.1
 * @since 0.0.1
 *
 */

abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3122958702938259476L;
	private String createdUser;
	private Date createdTime;
	private String modifiedUser;
	private Date modifiedTime;

	// GET/SET/toString()
	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "BaseEntity [createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser="
				+ modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}

}
