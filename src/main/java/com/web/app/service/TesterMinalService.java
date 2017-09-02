package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Testerminal;
import com.web.app.mapper.TesterMinalMapper;

@Service("testerMinalService")
public class TesterMinalService {
	@Autowired
	private TesterMinalMapper testerMinalMapper;

	public int insertTesterminal(Testerminal testerminal) {
		return testerMinalMapper.insertTesterminal(testerminal);
	}

	public List<Testerminal> getAllTesterMinal(Map<String, Object> map) {
		return testerMinalMapper.getAllTesterMinal(map);
	}

	public int countByCriteria() {
		return testerMinalMapper.countByCriteria();
	}

 
}
