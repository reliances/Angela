package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Category;
import com.web.app.mapper.CategoryMapper;

@Service("categoryService")
public class CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	public int insertCategory(Category category) {
		return categoryMapper.insertCategory(category);
	}

	public List<Category> getAllCategory(Map<String, Object> map) {
		return categoryMapper.getAllCategory(map);
	}

	public int countByCriteria() {
		return categoryMapper.countByCriteria();
	}

	public int deleteCategoryById(String[] dicIds) {
		return categoryMapper.deleteCategoryById(dicIds);
	}

	public int updateCategoryById(Category category) {
		return categoryMapper.updateCategoryById(category);
	}

	public List<Category> selectCategoryByParentId(String categoryId) {
		return categoryMapper.selectCategoryByParentId(categoryId);
	}

	public List<Category> selectCategoryParent() {
		return categoryMapper.selectCategoryParent();
	}

	public Category selectCategoryByName(String cateName) {
		return categoryMapper.selectCategoryByName(cateName);
	}
	
}
