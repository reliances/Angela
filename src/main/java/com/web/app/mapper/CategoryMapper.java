package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.Category;

/**
 * @Title:CategoryMapper     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月9日 下午9:18:13       
 * @version V1.0
 */
public interface CategoryMapper {
	//添加类别
	public int insertCategory(Category category);
	//查询类别
	public List<Category> getAllCategory(Map<String,Object> map);
	//查询类别个数
	public int countByCriteria();
	//删除类别
	public int deleteCategoryById(String[] dicIds);
	//编辑类别
	public int updateCategoryById(Category category);
	//通过名称查找类别
	public Category selectCategoryByName(String cateName);
	//查询父类和子类
	public List<Category> selectCategoryParent();
	public List<Category> selectCategoryByParentId(String categoryId);
}
