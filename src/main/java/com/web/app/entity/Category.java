package com.web.app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:Category     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月8日 下午10:45:34       
 * @version V1.0
 */
public class Category implements Serializable{
	private static final long serialVersionUID = 828569102121852733L;
	
	private String id;
	private String cateName;
	private Integer depth;
	private String parentId;
	private Integer priority;  //优先级
	private Integer status;
	private Integer isDelete;
	
	private List<Category> category;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
}
