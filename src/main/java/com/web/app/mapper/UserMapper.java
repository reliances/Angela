package com.web.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.app.entity.User;
/**
 * @Title:UserMapper    
 * @Description:人员接口
 * @Auth:BaiXueFeng   
 * @CreateTime:2015年7月22日 下午8:02:4       
 * @version V1.0
 */
public interface UserMapper {

	//查询人员列表
	public List<User> getAllUsers(Map<String,Object> map);
	public List<User> getApplyUser(Map<String,Object> map);
    //删除人员
	public int deleteUserById(List<String> userIds);
    //添加人员
	public int insertUser(User user);
	//获得人员个数
	public int countByCriteria();
	
	public int updateUserById(User user);
	
	public int countByNameAndPassword(Map<String, String> map);
	
	public User selectUserByNameAndPassword(Map<String, String> map);
	
	public List<User> selectUserByRole(List<String> roleIds);
	public int updatePasswordById(@Param(value = "userId") String userId, 
			@Param(value="oldPassword") String oldPassword, @Param(value="newPassword") String newPassword);
	
	//获取代理人列表
	public List<User> selectAgent(Map<String, Object> map);
	
	//获取人员信息
	public User getUserInfoById(String userId);
	
	//查询用户帐号是否存在
	public int getUserByCount(String userCounts);
	public int countByNumber(String id);
}
