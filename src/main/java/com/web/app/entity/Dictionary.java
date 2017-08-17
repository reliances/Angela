package com.web.app.entity;

public class Dictionary {

	private String dicId;//字典编号
	private String dicName;//字典名称
	private String dicKey;//字典键
	private String dicVal;//字典值
	private String dicDes;//说明
	private String createDate;
	private Integer isDelete = 0;
	
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getDicId() {
		return dicId;
	}
	public void setDicId(String dicId) {
		this.dicId = dicId;
	}
	public String getDicKey() {
		return dicKey;
	}
	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}
	public String getDicVal() {
		return dicVal;
	}
	public void setDicVal(String dicVal) {
		this.dicVal = dicVal;
	}
	public String getDicDes() {
		return dicDes;
	}
	public void setDicDes(String dicDes) {
		this.dicDes = dicDes;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
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
	
}
