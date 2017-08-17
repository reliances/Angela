package com.web.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Menu;
import com.web.app.mapper.MenuMapper;

@Service("menuService")
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	/**
	 * @return
	 * @see com.web.app.mapper.MenuMapper#getAllMenu()
	 */
	public List<Menu> getAllMenu(String[] authNos) {
		return menuMapper.getAllMenu(authNos);
	}

	 

	 
	
	
}
