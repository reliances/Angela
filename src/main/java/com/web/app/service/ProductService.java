package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Category;
import com.web.app.entity.Product;
import com.web.app.mapper.ProductMapper;

@Service("productService")
public class ProductService {
	@Autowired
	private ProductMapper productMapper;

	public int insertProduct(Product product) {
		return productMapper.insertProduct(product);
	}

	public List<Product> getAllProduct(Map<String, Object> map) {
		return productMapper.getAllProduct(map);
	}

	public int countByCriteria() {
		return productMapper.countByCriteria();
	}

	public int deleteProductById(String[] dicIds) {
		return productMapper.deleteProductById(dicIds);
	}

	public int updateProductById(Product product) {
		return productMapper.updateProductById(product);
	}

	public List<Category> selectProductByName(String cateName) {
		return productMapper.selectProductByName(cateName);
	}

	public Product getProductById(String id) {
		return productMapper.getProductById(id);
	}
}
