package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.Dictionary;

public interface DictionaryMapper {
	
    //添加字典
	public int insertDictionary(Dictionary dictionary);
	//查询字典列表
	public List<Dictionary> getAllDictionary(Map<String,Object> map);
	//查询字典个数
	public int countByCriteria();
	//删除字典
	public int deleteDictionaryById(String[] dicIds);
	//批量查询
	public List<Dictionary> getDictionaryByIds(String[] dicIds);
	//编辑字典
	public int updateDictionaryById(Dictionary dictionary);
	//通过名称查找字典
	public List<Dictionary> selectDicByName(String dicName);
}
