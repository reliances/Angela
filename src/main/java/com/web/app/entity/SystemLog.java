package com.web.app.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title:SystemLog     
 * @Description:操作日志
 * @Auth:LiangRui   
 * @CreateTime:2017年3月8日 下午3:54:24       
 * @version V1.0
 */
public class SystemLog implements Serializable{
	private static final long serialVersionUID = -7141484104580850866L;
	
	private String uuid;		   //uuID
    private String userId;		   //用户ID
    private String userName;	   //用户ID
    private String ipAddress;      //操作者IP地址
    private String operationType;  //操作类型
    private String logComment;     //操作内容
    private Date createDate;       //添加时间
    private String createDate2;    //查询时间区间
    
    
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getLogComment() {
		return logComment;
	}
	public void setLogComment(String logComment) {
		this.logComment = logComment;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateDate2() {
		return createDate2;
	}
	public void setCreateDate2(String createDate2) {
		this.createDate2 = createDate2;
	}
	
}
