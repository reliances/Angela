package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.Role;
import com.web.app.mapper.RoleMapper;
@Service("roleService")
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	public List<Role> getAllRoles(Map<String, Object> map) {
		return roleMapper.getAllRoles(map);
	}

	public int deleteRoleById(String[] roleIds) {
		return roleMapper.deleteRoleById(roleIds);
	}

	public int insertRole(Role role) {
		return roleMapper.insertRole(role);
	}

	public int countByCriteria() {
		return roleMapper.countByCriteria();
	}
	
	public int updateRoleById(Role role) {
		return roleMapper.updateRoleById(role);
	}

	public Role selectRoleById(String uuid) {
		return roleMapper.selectRoleById(uuid);
	}

	public int countRoleById(String id) {
		// TODO Auto-generated method stub
		return roleMapper.countRoleById(id);
	}

}
