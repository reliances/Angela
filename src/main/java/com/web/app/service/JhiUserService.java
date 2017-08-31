package com.web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.entity.JhiUser;
import com.web.app.mapper.JhiUserMapper;

@Service("jhiUserService")
public class JhiUserService {

	@Autowired
	private JhiUserMapper jhiUserMapper;

	public JhiUser getJhiuserInfo(String userId) {
		return jhiUserMapper.getJhiuserInfo(userId);
	}
	
}
