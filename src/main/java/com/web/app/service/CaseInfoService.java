package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.CaseInfo;
import com.web.app.mapper.CaseInfoMapper;

@Service("CaseInfoService")
public class CaseInfoService {
	@Autowired
	private CaseInfoMapper caseInfoMapper;
	
	public int insertCaseInfo(CaseInfo caseInfo) {
		return caseInfoMapper.insertCaseInfo(caseInfo);
	}

	public List<CaseInfo> getAllCaseInfo(Map<String, Object> map) {
		return caseInfoMapper.getAllCaseInfo(map);
	}

	public int countByCriteria() {
		return caseInfoMapper.countByCriteria();
	}

	public int deleteCaseInfoById(String[] caseIds) {
		return caseInfoMapper.deleteCaseInfoById(caseIds);
	}

	public int updateCaseInfoById(CaseInfo caseInfo) {
		return caseInfoMapper.updateCaseInfoById(caseInfo);
	}

	public CaseInfo getCaseInfoById(String id) {
		return caseInfoMapper.getCaseInfoById(id);
	}

}
