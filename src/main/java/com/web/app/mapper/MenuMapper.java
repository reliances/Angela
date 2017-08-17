package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.app.entity.Menu;
/**
 * @Title:SectorMapper     
 * @Description:菜单接口
 * @Auth:LiangRui   
 * @CreateTime:2015年7月21日 下午2:59:47       
 * @version V1.0
 */
public interface MenuMapper{

	public List<Menu> getAllMenu(@Param(value = "authNos") String[] authNos);
 
}













