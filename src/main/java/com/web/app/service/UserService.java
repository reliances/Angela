package com.web.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.User;
import com.web.app.mapper.UserMapper;

@Service("userService")
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> selectUserByRole(List<String> roleIds) {
		return userMapper.selectUserByRole(roleIds);
	}

	public List<User> getAllUsers(Map<String, Object> map) {
		return userMapper.getAllUsers(map);
	}

	public int deleteUserById(List<String> userIds) {
		return userMapper.deleteUserById(userIds);
	}

	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}

	public int countByCriteria() {
		return userMapper.countByCriteria();
	}

	public int updateUserById(User user) {
		return userMapper.updateUserById(user);
	}

	public int countByNameAndPassword(Map<String, String> map) {
		return userMapper.countByNameAndPassword(map);
	}

	public User getUserInformation(String userAccount, String password) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("userAccount", userAccount);
		map.put("password", password);
		User user = userMapper.selectUserByNameAndPassword(map);
		return user;
	}

	public int changePasswordById(String userId, String oldPassword, String newPassword) {
		return userMapper.updatePasswordById(userId, oldPassword, newPassword);
	}

	public List<User> selectAgent(Map<String, Object> map) {
		return userMapper.selectAgent(map);
	}

	public User getUserInfoById(String userId) {
		return userMapper.getUserInfoById(userId);
	}

	public int getUserByCount(String userCounts) {
		return userMapper.getUserByCount(userCounts);
	}

	public int countByNumber(String id) {
		return userMapper.countByNumber(id);
	}

	public List<User> getApplyUser(Map<String, Object> map) {
		return userMapper.getApplyUser(map);
	}

 

	 
	
}
