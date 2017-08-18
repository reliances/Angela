package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.Review;

public interface ReviewMapper {
	// 查询所有
	public List<Review> getAllReview(Map<String, Object> map);

	// 查询总数
	public int countByCriteria();

	// 根据ID删除
	public int deletById(String[] id);

}
