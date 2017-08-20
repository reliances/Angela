package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Pictures;
import com.web.app.mapper.PicturesMapper;

@Service("picturesService")
public class PicturesService {
	@Autowired
	private PicturesMapper picturesMapper;

	public List<Pictures> getAllPictures(Map<String, Object> map) {
		return picturesMapper.getAllPictures(map);
	}

	public int countByCriteria() {
		return picturesMapper.countByCriteria();
	}

	public int deletePicturesById(String[] dicIds) {
		return picturesMapper.deletePicturesById(dicIds);
	}

	public int updatePicturesById(Pictures pictures) {
		return picturesMapper.updatePicturesById(pictures);
	}

	public int insertPictures(Pictures pictures) {
		return picturesMapper.insertPictures(pictures);
	}

	public List<Pictures> selectPicturesByProductId(String proId) {
		return picturesMapper.selectPicturesByProductId(proId);
	}

	public int deletePicturesByProductId(String[] dicIds) {
		return picturesMapper.deletePicturesByProductId(dicIds);
	}

	public Pictures findPicById(String imageId) {
		return picturesMapper.findPicById(imageId);
	}
	
}
