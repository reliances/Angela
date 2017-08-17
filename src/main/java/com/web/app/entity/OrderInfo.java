package com.web.app.entity;

import java.io.Serializable;

/**
 * @Title:OrderInfo
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月17日 下午16:07:49
 * @version V1.0
 */
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = -7809732860592303381L;

	private String id;
	private String connectStatus;
	private String orderId;
	private String productCount;
	private String productId;
	private String createDate;
	private String createUser;
	private Integer status = 0;
	private Integer isDelete = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(String connectStatus) {
		this.connectStatus = connectStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductCount() {
		return productCount;
	}

	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

}
