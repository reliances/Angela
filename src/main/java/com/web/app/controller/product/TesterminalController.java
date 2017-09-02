package com.web.app.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.app.controller.BaseController;
import com.web.app.service.TesterMinalService;
import com.web.app.tools.Pager;

/**
 * @Title:ReviewController
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月18日 下午8:25:40
 * @version V1.0
 */
@Controller
@RequestMapping("/testerminal")
public class TesterminalController extends BaseController {
	@Autowired
	private TesterMinalService testerMinalService;

	// 查询评价
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllTesterminal")
	public String getAllTesterminal(Model model, @RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
		int totalCount = testerMinalService.countByCriteria();
		this.initPage(map, pageNum, pageSize, totalCount);
		map.put("startIndex", (pageNum - 1) * pageSize);
		map.put("endIndex", pageSize);
		@SuppressWarnings("rawtypes")
		List listInfo = testerMinalService.getAllTesterMinal(map);
		this.initResult(model, listInfo, map);
		if (request.getParameter("sub") == null) {
			request.setAttribute("sub", 2);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/tester_minal_manage";
	}
	
}
