package com.web.app.controller.system;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.controller.BaseController;
import com.web.app.entity.Menu;
import com.web.app.entity.Role;
import com.web.app.entity.RoleAuth;
import com.web.app.entity.User;
import com.web.app.service.MenuService;
import com.web.app.service.RoleAuthService;
import com.web.app.service.RoleService;
import com.web.app.service.UserService;
import com.web.app.tools.Constant;
import com.web.app.tools.MD5Util;
import com.web.app.tools.Pager;
/**
 * @Title:UserController     
 * @Description: 用户管理
 * @Auth:LiangRui   
 * @CreateTime:2017年3月7日 下午5:13:30       
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleAuthService roleAuthService;
	
	//修改密码
	@RequestMapping("/changePassword")
	@ResponseBody
	public Map<String, Object> changePassword(Model model, @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		int updateStatus = 0;
		oldPassword = MD5Util.MD5(oldPassword);
		newPassword = MD5Util.MD5(newPassword);
		updateStatus = userService.changePasswordById(user.getUuid(), oldPassword, newPassword);
		if (updateStatus >= 1) {
			try {
				Log("修改密码", "用户成功修改密码", request);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			map.put("message", "1");
	        if(session!=null){
	            session.invalidate();
	        }
		} else {
			map.put("message", "0");
		}
		return map;
	} 
	
	//登录 + 页面跳转
	@RequestMapping("/loginAction")
	public String loginAction(Model model, @RequestParam(value = "userAccount") String userAccount,
            @RequestParam(value = "password") String password, HttpServletRequest request){
		if (!StringUtils.isNotEmpty(userAccount)) {
			model.addAttribute("errorMessageFlag", "fail");
            return "login";
        }
        if (!StringUtils.isNotEmpty(password)) {
        	model.addAttribute("errorMessageFlag", "fail");
            return "login";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("userAccount", userAccount);
        map.put("password", MD5Util.MD5(password));
        int userCount = userService.countByNameAndPassword(map);
        if (userCount != 0) {
        	User user = userService.getUserInformation(userAccount,MD5Util.MD5(password));
            if (user == null) {
            	model.addAttribute("errorMessageFlag", "fail");
                return "login";
            }
            RoleAuth roleAuth = roleAuthService.getRoleAuthByRoleNo(user.getUserRole());
            if (roleAuth == null) {
            	model.addAttribute("errorMessageFlag", "authFail");
            	return "login";
            } else if (roleAuth.getAuthNo() == null) {
            	model.addAttribute("errorMessageFlag", "authFail");
            	return "login";
            }
            List<Menu> menus = menuService.getAllMenu(roleAuth.getAuthNo().split(","));
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(30*60);
            session.setAttribute("menus", menus);
            session.setAttribute("user", user);
            try {
				Log("登录系统", userAccount+"成功登录系统", request);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
            return "home";
        } else {
        	 try {
 				Log("登录系统", userAccount+"登录系统失败", request);
 			} catch (UnknownHostException e) {
 				e.printStackTrace();
 			}
        	model.addAttribute("errorMessageFlag", "fail");
        	return "login";
        }
	}	
	
	@RequestMapping("/getUserById")
	@ResponseBody
	public Map<String, Object> getUserById(String id) {
		int totalCount = userService.countByNumber(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (totalCount >= 1) {
			map.put("message", "1");
		} else {
			map.put("message", "0");
		}
		return map;
	}
	
	//查询所有职位
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getAllUsers")
	public String getAllUsers(Model model, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
	    int totalCount = userService.countByCriteria();
	    this.initPage(map, pageNum, pageSize, totalCount);
	    map.put("startIndex", (pageNum-1) * pageSize);
		map.put("endIndex", pageSize);
		List userList = userService.getAllUsers(map);
		this.initResult(model, userList, map);
		Map<String,Object> criteriaMap = new HashMap<String,Object>();
	    List<Role> roleList = roleService.getAllRoles(criteriaMap);
	    model.addAttribute("roleList", roleList);
	    if(request.getParameter("sub") == null){
			request.setAttribute("sub", Constant.MENU_LEVEL_ONE);
		}
	    try {
			Log("查询数据", "查询所有用户数据信息", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	    request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "system/user_manage";
	}
	
	//根据id删除人员
	@RequestMapping("/deleteUserById")
	public String deleteUserById(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String userId = request.getParameter("userId");
		String[] userIds = userId.split(",");
		List<String> rsIds = new ArrayList<String>();
		int length = userIds.length;
		for (int i = 0; i < length; i ++) {
			rsIds.add(userIds[i]);
		}
		userService.deleteUserById(rsIds);
		Log("删除操作", "删除用户"+userIds+"的个人信息", request);
		return "redirect:/user/getAllUsers?sub="+Constant.MENU_LEVEL_ONE;
	}
	
	//跳转到首页
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		return "home";
	}
	
	@RequestMapping("/getUserByCount")
	public String getUserByCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int userCount = userService.getUserByCount(request.getParameter("userCount"));
		String returnStr = null;
		if(userCount == 0){
			returnStr = "success";
		}else{
			returnStr = "no";
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	@RequestMapping("/countUserByAccount")
	@ResponseBody
	public Map<String, Object> countUserByAccount(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int userCount = userService.getUserByCount(request.getParameter("id"));
		Map<String, Object> map = new HashMap<String, Object>();
		if(userCount == 0){
			map.put("message", "0");
		}else{
			map.put("message", "1");
		}
		return map;
	}
	
	//添加人员
	@RequestMapping("/addUser")
	public String addPosition(User user, HttpServletRequest request) {
		user.setUuid(UUID.randomUUID().toString());
		user.setUserPassword(MD5Util.MD5(user.getUserPassword()));
		userService.insertUser(user);
		try {
			Log("增加操作", "添加新用户用户:"+user.getUserName(), request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "redirect:/user/getAllUsers?sub="+Constant.MENU_LEVEL_ONE;
	}
	
	//修改人员
	@RequestMapping("/updateUserById")
	public String updateUserById(User user, HttpServletRequest request){
		if (user.getUserPassword().length()<32) {
			user.setUserPassword(MD5Util.MD5(user.getUserPassword()));
		}
		userService.updateUserById(user);
		try {
			Log("修改操作", "修改用户:"+user.getUserName()+"的个人信息.", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "redirect:/user/getAllUsers?sub="+Constant.MENU_LEVEL_ONE;
	}
	
	//登出
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		User us = (User) session.getAttribute("user");
        try {
			Log("登出操作", "用户"+us.getUserName()+"退出登录!", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        if(session!=null){
            session.invalidate();
        }
		return "redirect:/login.jsp";
	}
}
