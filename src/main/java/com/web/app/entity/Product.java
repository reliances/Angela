package com.web.app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:Product     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月9日 下午10:40:39       
 * @version V1.0
 */
public class Product implements Serializable{
	private static final long serialVersionUID = -2556047093066756620L;
	
	private String id;
    private String productSn;
    private String categoryId;
    private String productName;
    private Integer clickCount;
    private Double marketPrice;
    private Double productPrice;
    private String brief;
    private String productDetails;
    private Integer isOnSale;
    private Integer isHot;
    private Integer sortOrder;
    private String productUnit;
    private Integer stock;
    private String productColor;
    private String productArea;
    private String material;
    private String proTag;
    private String proTagName;
    private Integer sizeL;
    private Integer sizeW;
    private Integer sizeH;
    private Integer sellCount;
    private String createUser;
    private String createDate;
    private String lastUpdate;
    private Integer productStatus=0;
    private Integer isDelete=0;
    
    private List<Pictures> pictures;
    private List<Review> reviews;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public Integer getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductArea() {
		return productArea;
	}

	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getProTag() {
		return proTag;
	}

	public void setProTag(String proTag) {
		this.proTag = proTag;
	}

	public Integer getSizeL() {
		return sizeL;
	}

	public void setSizeL(Integer sizeL) {
		this.sizeL = sizeL;
	}

	public Integer getSizeW() {
		return sizeW;
	}

	public void setSizeW(Integer sizeW) {
		this.sizeW = sizeW;
	}

	public Integer getSizeH() {
		return sizeH;
	}

	public void setSizeH(Integer sizeH) {
		this.sizeH = sizeH;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public List<Pictures> getPictures() {
		return pictures;
	}

	public void setPictures(List<Pictures> pictures) {
		this.pictures = pictures;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getProTagName() {
		return proTagName;
	}

	public void setProTagName(String proTagName) {
		this.proTagName = proTagName;
	}
    
}
