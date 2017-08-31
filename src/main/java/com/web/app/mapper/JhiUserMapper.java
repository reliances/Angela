package com.web.app.mapper;

import com.web.app.entity.JhiUser;
/**
 * @Title:JhiUserMapper     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月31日 下午2:00:13       
 * @version V1.0
 */
public interface JhiUserMapper {
	//获取人员信息
	public JhiUser getJhiuserInfo(String userId);
}
