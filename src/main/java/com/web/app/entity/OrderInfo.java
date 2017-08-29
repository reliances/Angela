package com.web.app.entity;

import java.io.Serializable;
import java.util.List;

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
	private String userId;
	private String companyName;
	private String customName;
	private String email;
	private String phoneNumber;
	private String asi;
	private String fax;
	private Double targetPrice;
	private String remarks;
	private String createDate;
	private Integer status = 0;
	private Integer isDelete = 0;
	
	private List<OrderProduct> orderProduct;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAsi() {
		return asi;
	}
	public void setAsi(String asi) {
		this.asi = asi;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Double getTargetPrice() {
		return targetPrice;
	}
	public void setTargetPrice(Double targetPrice) {
		this.targetPrice = targetPrice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public List<OrderProduct> getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(List<OrderProduct> orderProduct) {
		this.orderProduct = orderProduct;
	}

}
