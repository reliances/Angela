package com.web.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title:Menu     
 * @Description:左侧菜单Bean
 * @Auth:LiangRui   
 * @CreateTime:2015年7月29日 下午2:54:53       
 * @version V1.0
 */
public class Menu  implements Serializable{
	private static final long serialVersionUID = 7248860948888174440L;
	
	private String mId;        //主键ID
	private String mName;      //菜单名称
	private String url;        //请求方法名称
	private String mClass;     //样式
	private String parentId;   //父级ID
	private Date createtime;   //父级ID
	private List<SubURL> subURLs;
	private String isShow;     // 0可用  1不可用
	
	public List<SubURL> getSubURLs() {
		return subURLs;
	}
	public void setSubURLs(List<SubURL> subURLs) {
		this.subURLs = subURLs;
	}
	/**
	 * @return the mId
	 */
	public String getmId() {
		return mId;
	}
	/**
	 * @param mId the mId to set
	 */
	public void setmId(String mId) {
		this.mId = mId;
	}
	/**
	 * @return the mName
	 */
	public String getmName() {
		return mName;
	}
	/**
	 * @param mName the mName to set
	 */
	public void setmName(String mName) {
		this.mName = mName;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the mClass
	 */
	public String getmClass() {
		return mClass;
	}
	/**
	 * @param mClass the mClass to set
	 */
	public void setmClass(String mClass) {
		this.mClass = mClass;
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	
}
