package com.web.app.entity;

import java.io.Serializable;

/**
 * @Title:Testerminal     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年9月2日 下午8:31:49       
 * @version V1.0
 */
public class Testerminal implements Serializable{
	private static final long serialVersionUID = 1316360659534182808L;
	
	private String id;
	private String company;
	private String contact;
	private String tellNumber;
	private String fax;
	private String email;
	private String asi;
	private Integer supplierRating;
	private Integer satisfaction;
	private Integer productQuality;
	private Integer responsiveness;
	private Integer delivery;
	private Integer problemResolution;
	private Integer imprinting;
	private String adivseNote;
	private String createTime;
	private Integer isDelete = 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTellNumber() {
		return tellNumber;
	}
	public void setTellNumber(String tellNumber) {
		this.tellNumber = tellNumber;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAsi() {
		return asi;
	}
	public void setAsi(String asi) {
		this.asi = asi;
	}
	public Integer getSupplierRating() {
		return supplierRating;
	}
	public void setSupplierRating(Integer supplierRating) {
		this.supplierRating = supplierRating;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getAdivseNote() {
		return adivseNote;
	}
	public void setAdivseNote(String adivseNote) {
		this.adivseNote = adivseNote;
	}
	
}
