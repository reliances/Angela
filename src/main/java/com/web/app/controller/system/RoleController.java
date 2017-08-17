package com.web.app.controller.system;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.app.controller.BaseController;
import com.web.app.entity.Role;
import com.web.app.service.RoleService;
import com.web.app.tools.Constant;
import com.web.app.tools.Pager;
/**
 * @Title:RoleController     
 * @Description: 角色管理
 * @Auth:LiangRui   
 * @CreateTime:2017年3月9日 下午4:02:05       
 * @version V1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	@Autowired
	private RoleService roleService;
	
	/**
	 * @Title: getAllRoles
	 * @Description: 查询所有角色
	 * @param @param model
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @param request
	 * @param @return
	 * @retrun String
	 * @author LiangRui
	 * @throws
	 * @Time 2017年3月9日 下午4:08:22
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getAllRoles")
	public String getAllRoles(Model model, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
	    int totalCount = roleService.countByCriteria();
	    this.initPage(map, pageNum, pageSize, totalCount);
	    map.put("startIndex", (pageNum-1) * pageSize);
		map.put("endIndex", pageSize);
		List roleList = roleService.getAllRoles(map);
		this.initResult(model, roleList, map);
		if(request.getParameter("sub") == null){
			request.setAttribute("sub", Constant.MENU_LEVEL_ONE);
		}
		try {
			Log("查询操作", "获取所有角色信息.", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "system/role_manage";
	}
	
	@RequestMapping("/addRole")
	public String addRole(Role role, HttpServletRequest request) {
		role.setUuid(UUID.randomUUID().toString());
		roleService.insertRole(role);	
		try {
			Log("新增操作", "添加新的角色信息,角色名称为"+role.getRoleName(), request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/role/getAllRoles?sub="+Constant.MENU_LEVEL_ONE;
	}
	
	/**
	 * @Title: deleteRoleById
	 * @Description: 删除含批量删除
	 * @param @param roleId
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @retrun String
	 * @author LiangRui
	 * @throws
	 * @Time 2017年3月9日 下午4:10:38
	 */
	@RequestMapping("/deleteRoleById")
	public String deleteRoleById(String roleId, HttpServletRequest request,HttpServletResponse response) {
		String[] roleIds = roleId.split(",");
		roleService.deleteRoleById(roleIds);
		try {
			Log("删除操作", "根据id删除角色信息,id为"+roleId, request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/role/getAllRoles?sub="+Constant.MENU_LEVEL_ONE;
	}
	
	/**
	 * @Title: updateRoleById
	 * @Description: 根据ID进行角色修改
	 * @param @param role
	 * @param @param request
	 * @param @return
	 * @param @throws IOException
	 * @retrun String
	 * @author LiangRui
	 * @throws
	 * @Time 2017年3月9日 下午4:10:57
	 */
	@RequestMapping("/updateRoleById")
	public String updateRoleById(Role role, HttpServletRequest request) throws IOException{
		roleService.updateRoleById(role);
		try {
			Log("修改操作", "修改角色信息,角色名为"+role.getRoleName(), request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/role/getAllRoles?sub="+Constant.MENU_LEVEL_ONE;
	}
	
	@RequestMapping("/getRoleById")
	@ResponseBody
	public Role getRoleById(String roleId,HttpServletRequest request) {
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		Role role = roleService.selectRoleById(roleId);
		return role;
	}
	
	@RequestMapping("/countRoleById")
	@ResponseBody
	public Map<String, Object> countRoleById(String id) {
		int userCount = roleService.countRoleById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if(userCount == 0){
			map.put("message", "0");
		}else{
			map.put("message", "1");
		}
		return map;
	}
}
