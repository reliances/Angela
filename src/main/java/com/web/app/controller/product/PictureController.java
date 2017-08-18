package com.web.app.controller.product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.app.controller.BaseController;
import com.web.app.entity.Pictures;
import com.web.app.entity.User;
import com.web.app.service.PicturesService;
import com.web.app.tools.DateTools;
import com.web.app.tools.Pager;

/**
 * @Title:PictureController
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月17日 下午17:35:40
 * @version V1.0
 */
@Controller
@RequestMapping("/picture")
public class PictureController extends BaseController {
	@Autowired
	private PicturesService pictureService;

	// 查询图片
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllPictures")
	public String getAllPictures(Model model, @RequestParam(required = false) Integer pageNum,
			@RequestParam(required = false) Integer pageSize, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
		int totalCount = pictureService.countByCriteria();
		this.initPage(map, pageNum, pageSize, totalCount);
		map.put("startIndex", (pageNum - 1) * pageSize);
		map.put("endIndex", pageSize);
		@SuppressWarnings("rawtypes")
		List listinfo = pictureService.getAllPictures(map);
		this.initResult(model, listinfo, map);
		if (request.getParameter("sub") == null) {
			request.setAttribute("sub", 3);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "picture/picture_manage";
	}

	// 添加图片
	@RequestMapping("/addPictures")
	public String addPictures(Model model, Pictures pictures, HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		pictures.setImageId(UUID.randomUUID().toString());
		pictures.setCreateUser(user.getUserId());
		pictures.setCreateDate(DateTools.getCurrentTime());
		pictureService.insertPictures(pictures);
		try {
			Log("新增操作", "新增一条名为" + pictures.getImageId() + "的图片", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/picture/getAllPictures";
	}

	// 到图片添加页面
	@RequestMapping("/toAddPicPage")
	public String toAddPicPage(Model model, HttpServletRequest request) {
		return "picture/picture_add";
	}

	// 到图片编辑页面
	@RequestMapping("/toEditPicPage")
	public String toEditPicPage(Model model, HttpServletRequest request) {
//		String id = request.getParameter("id");
//		Pictures picInfo = pictureService.getPicturesById(id);
//		request.setAttribute("picInfo", picInfo);
		return "picture/picture_edit";
	}

	// 删除操作
	@RequestMapping("/deletePictures")
	public String deletePictures(Model model, HttpServletRequest request) {
		String ids = request.getParameter("Ids");
		String[] id = ids.split(",");
		pictureService.deletePicturesById(id);
		return "redirect:/picture/getAllPictures";
	}

	// 修改图片
	@RequestMapping("/updatePictures")
	public String updatePictures(Model model, Pictures pictures, HttpServletRequest request) {
		pictureService.updatePicturesById(pictures);
		try {
			Log("修改操作", "修改一条名为" + pictures.getImageId() + "的订单", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/picture/getAllPictures";
	}
}
