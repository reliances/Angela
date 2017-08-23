package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.Pictures;

/**
 * @Title:PicturesMapper     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月14日 下午8:35:58       
 * @version V1.0
 */
public interface PicturesMapper {
	//添加图片
	public int insertPictures(Pictures pictures);
	//查询图片
	public List<Pictures> getAllPictures(Map<String,Object> map);
	//查询图片个数
	public int countByCriteria();
	//删除图片
	public int deletePicturesById(String[] dicIds);
	//根据产品ID删除图片
	public int deletePicturesByProductId(String[] dicIds);
	//编辑图片
	public int updatePicturesById(Pictures pictures);
	//通过产品ID查找图片
	public List<Pictures> selectPicturesByProductId(String productId);
	//根据类型查询
	public List<Pictures> selectPicturesByType(String type);
	//分组查询
	public List<Pictures> selectPicturesByGroup(Map<String,Object> map);
	//根据图片ID查询图片信息
	public Pictures findPicById(String imageId);
}
