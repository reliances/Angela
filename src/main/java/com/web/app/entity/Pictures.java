package com.web.app.entity;

import java.io.Serializable;

/**
 * @Title:Pictures     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月14日 下午8:31:09       
 * @version V1.0
 */
public class Pictures implements Serializable{
	private static final long serialVersionUID = 1770807012737784547L;
	
	private String imageId;
	private String productId;
	private String imageUrl;
	private String imageUrlSmall;
	private String createDate;
	private String createUser;
	private Integer imageType;
	private Integer isDelete=0;
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageUrlSmall() {
		return imageUrlSmall;
	}
	public void setImageUrlSmall(String imageUrlSmall) {
		this.imageUrlSmall = imageUrlSmall;
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
	public Integer getImageType() {
		return imageType;
	}
	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}
