package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.SystemLog;

/**
 * @Title:SystemLogMapper     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年3月8日 下午4:02:54       
 * @version V1.0
 */
public interface SystemLogMapper{
	
	public void insertLog(SystemLog logs);
	
	public List<SystemLog> getAllSysLog(Map<String,Object> map);
	
	public int getSysLogCount(Map<String,Object> map);
}













