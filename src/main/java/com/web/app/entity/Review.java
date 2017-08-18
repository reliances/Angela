package com.web.app.entity;

import java.io.Serializable;

/**
 * 
 * @Title:Review
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月18日 上午10:47:49
 * @version V1.0
 */
public class Review implements Serializable {
	private static final long serialVersionUID = -7809732860592303381L;

	private String id;
	private String comment;
	private String createDate;
	private String createUser;
	private String productId;
	private String remarks;
	private Integer satisfaction;
	private Integer productQuality;
	private Integer responsiveness;
	private Integer delivery;
	private Integer problemResolution;
	private Integer imprinting;
	private Integer isUseful = 0;
	private Integer isDelete = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Integer getProductQuality() {
		return productQuality;
	}

	public void setProductQuality(Integer productQuality) {
		this.productQuality = productQuality;
	}

	public Integer getResponsiveness() {
		return responsiveness;
	}

	public void setResponsiveness(Integer responsiveness) {
		this.responsiveness = responsiveness;
	}

	public Integer getDelivery() {
		return delivery;
	}

	public void setDelivery(Integer delivery) {
		this.delivery = delivery;
	}

	public Integer getProblemResolution() {
		return problemResolution;
	}

	public void setProblemResolution(Integer problemResolution) {
		this.problemResolution = problemResolution;
	}

	public Integer getImprinting() {
		return imprinting;
	}

	public void setImprinting(Integer imprinting) {
		this.imprinting = imprinting;
	}

	public Integer getIsUseful() {
		return isUseful;
	}

	public void setIsUseful(Integer isUseful) {
		this.isUseful = isUseful;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
