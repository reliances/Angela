package com.web.app.controller.system;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import com.web.app.service.MenuService;
import com.web.app.service.RoleAuthService;
import com.web.app.service.RoleService;
import com.web.app.tools.Constant;
import com.web.app.tools.Pager;

/**
 * @Title:RoleAuthController     
 * @Description:权限管理
 * @Auth:LiangRui   
 * @CreateTime:2017年3月9日 下午4:02:22       
 * @version V1.0
 */
@Controller
@RequestMapping("/roleAuth")
public class RoleAuthController extends BaseController {
	@Autowired
	private RoleAuthService roleAuthService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	/**
	 * @Title: getAllRoleAuths
	 * @Description: 获取所有权限信息
	 * @param @param model
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @param request
	 * @param @return
	 * @retrun String
	 * @author LiangRui
	 * @throws
	 * @Time 2017年3月9日 下午4:03:54
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getAllRoleAuths")
	public String getAllRoleAuths(Model model, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List roleList = roleService.getAllRoles(map);
		
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
		List roleAuthList = roleService.getAllRoles(map);
		this.initResult(model, roleAuthList, map);
		
		Role role = roleService.selectRoleById(((Role)roleList.get(0)).getUuid());
		model.addAttribute("roleList", roleList);
		model.addAttribute("role", role);
		if(request.getParameter("sub") == null){
			request.setAttribute("sub", Constant.MENU_LEVEL_ONE);
		}
		try {
			Log("查询操作", "获取所有权限信息.", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "system/role_authority_manage";
	}
	
	/**
	 * @Title: getMenuTree
	 * @Description: 获取菜单树
	 * @param @param roleNo
	 * @param @param request
	 * @param @return
	 * @retrun Map<String,Object>
	 * @author LiangRui
	 * @throws
	 * @Time 2017年3月9日 下午4:04:09
	 */
	@RequestMapping("/getMenuTree")
	@ResponseBody
	public Map<String,Object> getMenuTree(String roleNo, HttpServletRequest request){
		RoleAuth roleAuth = roleAuthService.getRoleAuthByRoleNo(roleNo);
		List<Menu> menus = menuService.getAllMenu(null);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", menus);
		map.put("roleAuth", roleAuth);
		try {
			Log("查询操作", "获取菜单树形结构.", request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return map;
	}
	
	/**
	 * @Title: editRoleAuth
	 * @Description:编辑权限
	 * @param @param roleAuth
	 * @param @param request
	 * @param @return
	 * @retrun String
	 * @author LiangRui
	 * @throws
	 * @Time 2017年3月9日 下午4:05:26
	 */
	@RequestMapping("/editRoleAuth")
	public String editRoleAuth(RoleAuth roleAuth, HttpServletRequest request) {
		int count = roleAuthService.countByCriteria(roleAuth.getRoleNo());
		if (roleAuth.getAuthNo().length() == 0 || roleAuth.getAuthNo() == null) {
			//需要修改role表中的值为未设定
			Role role = new Role();
			role.setUuid(roleAuth.getRoleNo());
			role.setRoleStatus(0);
			roleService.updateRoleById(role);
			request.getSession().setAttribute("sub", request.getParameter("sub"));
			if (count != 0) {
				roleAuthService.updateRoleAuthByRoleNo(roleAuth);
				try {
					Log("修改操作", "对未设定的角色添加权限", request);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		} else {
			//需要修改role表中的值为设定
			Role role = new Role();
			role.setUuid(roleAuth.getRoleNo());
			role.setRoleStatus(1);
			roleService.updateRoleById(role);
			request.getSession().setAttribute("sub", request.getParameter("sub"));
			if (count != 0) {
				roleAuthService.updateRoleAuthByRoleNo(roleAuth);
				try {
					Log("修改操作", "修改已设定的角色权限", request);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Log("修改操作", "对未设定的角色添加权限", request);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				roleAuth.setUuid(UUID.randomUUID().toString());
				roleAuthService.insertRoleAuth(roleAuth);
			}
			
		}
		return "redirect:/roleAuth/getAllRoleAuths?sub="+Constant.MENU_LEVEL_ONE;
	}
}
