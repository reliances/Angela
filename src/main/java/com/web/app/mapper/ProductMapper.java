package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.Category;
import com.web.app.entity.Product;

/**
 * @Title:ProductMapper     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月14日 下午7:45:30       
 * @version V1.0
 */
public interface ProductMapper {
	//添加产品
	public int insertProduct(Product product);
	//查询产品
	public List<Product> getAllProduct(Map<String,Object> map);
	//查询产品个数
	public int countByCriteria();
	//删除产品
	public int deleteProductById(String[] dicIds);
	//编辑产品
	public int updateProductById(Product product);
	//通过名称查找产品
	public List<Category> selectProductByName(String cateName);
}
