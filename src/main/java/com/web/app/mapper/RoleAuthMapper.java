package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.app.entity.RoleAuth;

public interface RoleAuthMapper {

	public List<RoleAuth> getAllRoleAuth(Map<String,Object> map);

	public int deleteRoleAuthByRoleNo(String[] roleIds);

	public int insertRoleAuth(RoleAuth roleAuth);

	public int countByCriteria(@Param(value = "roleNo") String roleNo);
	
	public int updateRoleAuthByRoleNo(RoleAuth roleAuth);
	
	public RoleAuth getRoleAuthByRoleNo(@Param(value = "roleNo") String roleNo);
}
