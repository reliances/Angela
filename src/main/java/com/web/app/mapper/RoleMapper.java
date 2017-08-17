package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import com.web.app.entity.Role;

/**
 * @Title:RoleMapper    
 * @Description:��ɫ�ӿ�
 * @Auth:BaiXueFeng   
 * @CreateTime:2015��7��23�� ����1:24:4       
 * @version V1.0
 */
public interface RoleMapper {
    
	public List<Role> getAllRoles(Map<String,Object> map);
    
	public int deleteRoleById(String[] roleIds);
  
	public int insertRole(Role role);
 
	public int countByCriteria();
	
	public int updateRoleById(Role role);
	
	public Role selectRoleById(String uuid);

	public int countRoleById(String id);
}
