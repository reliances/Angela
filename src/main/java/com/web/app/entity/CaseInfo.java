package com.web.app.entity;

import java.io.Serializable;

/**
 * 
 * @Title:CaseInfo
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月15日 下午8:28:49
 * @version V1.0
 */
public class CaseInfo implements Serializable {
	private static final long serialVersionUID = -7809732860592303381L;

	private String id;
	private String title;
	private String brief;
	private String description;
	private String remarks;
	private Integer isUseful=0;
	private String createDate;
	private String createUser;
	private Integer isDelete=0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsUseful() {
		return isUseful;
	}

	public void setIsUseful(Integer isUseful) {
		this.isUseful = isUseful;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
