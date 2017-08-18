package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Review;
import com.web.app.mapper.ReviewMapper;

@Service("ReviewService")
public class ReviewService {
	@Autowired
	private ReviewMapper reviewMapper;

	public List<Review> getAllReview(Map<String, Object> map) {
		return reviewMapper.getAllReview(map);
	}

	public int countByCriteria() {
		return reviewMapper.countByCriteria();
	}

	public int deletById(String[] id) {
		return reviewMapper.deletById(id);
	}

}
