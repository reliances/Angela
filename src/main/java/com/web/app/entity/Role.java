package com.web.app.entity;

public class Role {

	private String uuid;
	private String roleId;
	private String roleName;
	private String roleDesc;
	private Integer roleStatus;
	private Integer isDelete = 0;
	
	 
	/**
	 * @return the isDelete
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * @return the roleStatus
	 */
	public Integer getRoleStatus() {
		return roleStatus;
	}
	/**
	 * @param roleStatus the roleStatus to set
	 */
	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
}
