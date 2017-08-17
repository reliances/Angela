package com.web.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.RoleAuth;
import com.web.app.mapper.RoleAuthMapper;

@Service("roleAuthService")
public class RoleAuthService {

	@Autowired
	private RoleAuthMapper roleAuthMapper;

	public List<RoleAuth> getAllRoleAuth(Map<String, Object> map) {
		return roleAuthMapper.getAllRoleAuth(map);
	}

	public int deleteRoleAuthByRoleNo(String[] roleIds) {
		return roleAuthMapper.deleteRoleAuthByRoleNo(roleIds);
	}

	public int insertRoleAuth(RoleAuth roleAuth) {
		return roleAuthMapper.insertRoleAuth(roleAuth);
	}

	public int countByCriteria(String roleNo) {
		return roleAuthMapper.countByCriteria(roleNo);
	}

	public int updateRoleAuthByRoleNo(RoleAuth roleAuth) {
		return roleAuthMapper.updateRoleAuthByRoleNo(roleAuth);
	}

	public RoleAuth getRoleAuthByRoleNo(String roleNo) {
		return roleAuthMapper.getRoleAuthByRoleNo(roleNo);
	}
	
	

}
