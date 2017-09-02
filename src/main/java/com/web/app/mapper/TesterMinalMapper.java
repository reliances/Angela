package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.Testerminal;

public interface TesterMinalMapper {
	//添加
	public int insertTesterminal(Testerminal testerminal);
	
	// 查询所有
	public List<Testerminal> getAllTesterMinal(Map<String, Object> map);

	// 查询总数
	public int countByCriteria();

}
