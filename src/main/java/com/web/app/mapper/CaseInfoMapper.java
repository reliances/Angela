package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.CaseInfo;

/**
 * @Title:CaseInfoMapper
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月15日 下午8:35:06
 * @version V1.0
 */
public interface CaseInfoMapper {
	// 添加案例
	public int insertCaseInfo(CaseInfo caseInfo);

	// 查询案例
	public List<CaseInfo> getAllCaseInfo(Map<String, Object> map);

	// 查询案例个数
	public int countByCriteria();

	// 删除案例
	public int deleteCaseInfoById(String[] dicIds);

	// 编辑案例
	public int updateCaseInfoById(CaseInfo caseInfo);

	// 根据id查询
	public CaseInfo getCaseInfoById(String id);

}
