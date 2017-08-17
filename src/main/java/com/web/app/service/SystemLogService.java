package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.SystemLog;
import com.web.app.mapper.SystemLogMapper;

@Service("syslogService")
public class SystemLogService {
	
	@Autowired
	private SystemLogMapper systemLogMapper;

	/**
	 * @param logs
	 * @see com.face.webapp.mapper.SystemLogMapper#insertLog(com.face.webapp.entity.SystemLog)
	 */
	public void insertLog(SystemLog logs) {
		systemLogMapper.insertLog(logs);
	}

	/**
	 * @return
	 * @see com.face.webapp.mapper.SystemLogMapper#getSysLogCount()
	 */
	public int getSysLogCount(Map<String,Object> map) {
		return systemLogMapper.getSysLogCount(map);
	}

	/**
	 * @param map
	 * @return
	 * @see com.face.webapp.mapper.SystemLogMapper#getAllSysLog(java.util.Map)
	 */
	public List<SystemLog> getAllSysLog(Map<String, Object> map) {
		return systemLogMapper.getAllSysLog(map);
	}

 

	 
	
	
}
