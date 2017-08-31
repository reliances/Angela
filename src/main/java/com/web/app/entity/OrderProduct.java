package com.web.app.entity;

import java.io.Serializable;

/**
 * @Title:OrderProduct     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月28日 下午9:48:20       
 * @version V1.0
 */
public class OrderProduct implements Serializable {
	private static final long serialVersionUID = -5726395780608524817L;
	
	private String id;
	private String orderId;
	private String productId;
	private Integer quantity;
	private String deliveryTime;
	private Double targetPrice;
	private String phoneNumber;
	private String asiSageNo;
	private String message;
	private String createDate;
	private Integer status = 0;   //有效
	private Integer isDelete = 0; //未删除
	
	private Product product;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Double getTargetPrice() {
		return targetPrice;
	}
	public void setTargetPrice(Double targetPrice) {
		this.targetPrice = targetPrice;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAsiSageNo() {
		return asiSageNo;
	}
	public void setAsiSageNo(String asiSageNo) {
		this.asiSageNo = asiSageNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	 
}
