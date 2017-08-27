package com.web.app.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.web.app.controller.BaseController;
import com.web.app.entity.Category;
import com.web.app.entity.Pictures;
import com.web.app.entity.User;
import com.web.app.service.CategoryService;
import com.web.app.service.PicturesService;
import com.web.app.tools.Constant;
import com.web.app.tools.DateTools;
import com.web.app.tools.Pager;

import net.sf.json.JSONObject;

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
	@Autowired
	private CategoryService categoryService;

	// 查询图片
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllPictures")
	public String getAllPictures(Model model, @RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		request.setAttribute("allPict", pictureService.getAllPictures(map));
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
		List listinfo = pictureService.selectPicturesByGroup(map);
		this.initResult(model, listinfo, map);
		if (request.getParameter("sub") == null) {
			request.setAttribute("sub", 3);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "picture/picture_manage";
	}

	// 添加图片
	@RequestMapping("/addPictures")
	public String addPictures(Pictures pictures,@RequestParam(value = "file", required = false) MultipartFile[] file, HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// Pictures upload
		uploadFile(pictures, file, request, user);
		try {
			Log("新增操作", "新增一条名为" + pictures.getImageId() + "的图片", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/picture/getAllPictures";
	}

	//图片上传
	private void uploadFile(Pictures pictures, MultipartFile[] file, HttpServletRequest request, User user) throws IOException {
		for (MultipartFile mf : file) {
			if (!mf.isEmpty()) {
				// 取得当前上传文件的文件名称
				// String contentType = mf.getContentType(); 文件类型
				// String myFileName = contentType.substring(contentType.indexOf("/")+1);
				String myFileName = mf.getOriginalFilename();
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (myFileName.trim() != "") {
					// 重命名上传后的文件名
					String fileName = DateTools.getTimes() + "_" + myFileName;
					//String fileName = DateTools.getTimes();
					// 定义上传路径
					String path = "";
					if(Constant.IS_SERVICE){
						path = Constant.FILE_UPLOAD_PATH;
					}else{
						path = request.getSession().getServletContext().getRealPath("upload");
					}
					File localFile = new File(path, fileName);
					if (!localFile.exists()) {
						localFile.mkdirs();
					}
					mf.transferTo(localFile);
					// pic
					Pictures pic = new Pictures();
					pic.setImageId(UUID.randomUUID().toString());
					pic.setCreateDate(DateTools.getCurrentTime());
					pic.setCreateUser(user.getUserId());
					pic.setImageType(pictures.getImageType());
					pic.setImageUrl(fileName);
					pic.setProductId(pictures.getProductId());
					pic.setImageUrlSmall(localFile.toString());
					pictureService.insertPictures(pic);
				}
			}
		}
	}

	// 到图片添加页面
	@RequestMapping("/toAddPicPage")
	public String toAddPicPage(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> cate = categoryService.getAllCategory(map);
		request.setAttribute("category", cate);
		return "picture/picture_add";
	}

	// 到图片编辑页面
	@RequestMapping("/toEditPicPage")
	public String toEditPicPage(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> cate = categoryService.getAllCategory(map);
		request.setAttribute("category", cate);
		String id = request.getParameter("id");
		Pictures picInfo = pictureService.findPicById(id);
		request.setAttribute("picInfo", picInfo);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		request.setAttribute("allPict", pictureService.getAllPictures(maps));
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

	// 修改图片信息
	@RequestMapping("/updatePictures")
	public String updatePictures(Model model, Pictures pictures, @RequestParam(value = "myFiles", required = false) MultipartFile[] myFiles, HttpServletRequest request) throws IOException {
		String imgIds = request.getParameter("imgIds");
		if (imgIds != "") {
			String[] imageid = imgIds.split(",");
			pictureService.deletePicturesById(imageid);
		}
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		uploadFile(pictures, myFiles, request, user);
		pictureService.updatePicturesById(pictures);
		try {
			Log("修改操作", "修改一条名为" + pictures.getImageId() + "的图片", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/picture/getAllPictures";
	}
	
	/**
	 * 图片修改通用方法(包括产品,案例..图片修改)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/image", method = RequestMethod.POST)
	public void updateImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		String productId = request.getParameter("productId");
		String imageId = request.getParameter("imgId");
		String imgtype = request.getParameter("imgType");

		DefaultMultipartHttpServletRequest defaultRequest = (DefaultMultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> fileMap = defaultRequest.getMultiFileMap();
		List<MultipartFile> fileList = fileMap.get("file");
		MultipartFile file = fileList.get(0);

		if (!file.isEmpty()) {
			// 取得当前上传文件的文件名称
			// String contentType = mf.getContentType(); 文件类型
			// String myFileName = contentType.substring(contentType.indexOf("/")+1);
			String myFileName = file.getOriginalFilename();
			// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
			if (myFileName.trim() != "") {
				// 重命名上传后的文件名
				//String fileName = DateTools.getTimes();
				String fileName = DateTools.getTimes() + "_" + myFileName;
				// 定义上传路径
				String path = "";
				if(Constant.IS_SERVICE){
					path = Constant.FILE_UPLOAD_PATH;
				}else{
					path = request.getSession().getServletContext().getRealPath("upload");
				}
				File localFile = new File(path, fileName);
				if (!localFile.exists()) {
					localFile.mkdirs();
				}
				file.transferTo(localFile);
				// pic
				Pictures pic = new Pictures();
				pic.setProductId(productId);
				pic.setImageId(imageId);
				pic.setImageType(Integer.parseInt(imgtype));// 设置图片类型
				pic.setImageUrl(fileName);
				pic.setImageUrlSmall(localFile.toString());
				pictureService.updatePicturesById(pic);
			}
		}

		Pictures picInfo = pictureService.findPicById(imageId);                 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("imageUrl", picInfo.getImageUrl());

		// 设置响应数据的类型json                                           
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(jsonObject.toString());
	}
}
