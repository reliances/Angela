package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Dictionary;
import com.web.app.mapper.DictionaryMapper;

@Service("dictionaryService")
public class DictionaryService {

	@Autowired
	private DictionaryMapper dictionaryMapper;

	public int insertDictionary(Dictionary dictionary) {
		return dictionaryMapper.insertDictionary(dictionary);
	}

	public List<Dictionary> getAllDictionary(Map<String, Object> map) {
		return dictionaryMapper.getAllDictionary(map);
	}

	public int countByCriteria() {
		return dictionaryMapper.countByCriteria();
	}

	public int deleteDictionaryById(String[] dicIds) {
		return dictionaryMapper.deleteDictionaryById(dicIds);
	}

	public int updateDictionaryById(Dictionary dictionary) {
		return dictionaryMapper.updateDictionaryById(dictionary);
	}

	public List<Dictionary> getDicByName(String dicName) {
		return dictionaryMapper.selectDicByName(dicName);
	}
	
}
