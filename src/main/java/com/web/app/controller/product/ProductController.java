package com.web.app.controller.product;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.app.controller.BaseController;
import com.web.app.entity.Category;
import com.web.app.entity.Dictionary;
import com.web.app.entity.Pictures;
import com.web.app.entity.Product;
import com.web.app.entity.User;
import com.web.app.service.CategoryService;
import com.web.app.service.DictionaryService;
import com.web.app.service.PicturesService;
import com.web.app.service.ProductService;
import com.web.app.tools.Constant;
import com.web.app.tools.DateTools;
import com.web.app.tools.Pager;

/**
 * @Title:CategoryController
 * @Description:
 * @Auth:LiangRui
 * @CreateTime:2017年8月8日 下午10:43:26
 * @version V1.0
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PicturesService pictureService;

	// 查询产品
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllproduct")
	public String getAllproduct(Model model, @RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Pictures> pic = pictureService.getAllPictures(map);
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
		int totalCount = productService.countByCriteria();
		this.initPage(map, pageNum, pageSize, totalCount);
		map.put("startIndex", (pageNum - 1) * pageSize);
		map.put("endIndex", pageSize);
		@SuppressWarnings("rawtypes")
		List cateList = productService.getAllProduct(map);
		this.initResult(model, cateList, map);
		if (request.getParameter("sub") == null) {
			request.setAttribute("sub", 2);
		}
		request.setAttribute("picList", pic);
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/product_manage";
	}

	// 添加商品
	@RequestMapping("/addProduct")
	public String addProduct(Product product, @RequestParam(value = "file", required = false) MultipartFile[] file,@RequestParam(value = "file2", required = false) MultipartFile[] file2, HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// product
		product.setId(UUID.randomUUID().toString());
		product.setCreateUser(user.getUserId());
		product.setCreateDate(DateTools.getCurrentTime());
		productService.insertProduct(product);
		// Pictures upload
		uploadFile(product, file, request, user);
		uploadFile2(product, file2, request, user);
		try {
			Log("新增操作", "新增一条名为" + product.getProductName() + "的商品", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/product/getAllproduct";
	}

	/**
	 * 图片上传
	 * @param product
	 * @param file
	 * @param request
	 * @param user
	 * @throws IOException
	 */
	private void uploadFile(Product product, MultipartFile[] file, HttpServletRequest request, User user) throws IOException {
		for (MultipartFile mf : file) {
			if (!mf.isEmpty()) {
				// 取得当前上传文件的文件名称
				// String contentType = mf.getContentType(); 文件类型
				// String myFileName = contentType.substring(contentType.indexOf("/")+1);
				String myFileName = mf.getOriginalFilename();
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (myFileName.trim() != "") {
					// 重命名上传后的文件名
					//String fileName = DateTools.getTimes() + "_" + myFileName;
					String prefix = myFileName.substring(myFileName.lastIndexOf(".")+1);
					String fileName = DateTools.getTimes()+ "."+ prefix;
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
					pic.setImageType(1);// 产品图片
					pic.setImageUrl(fileName);
					pic.setProductId(product.getId());
					pic.setImageUrlSmall(localFile.toString());
					pictureService.insertPictures(pic);
				}
			}
		}
	}
	
	
	/**
	 * 图片详情上传
	 * @param product
	 * @param file
	 * @param request
	 * @param user
	 * @throws IOException
	 */
	private void uploadFile2(Product product, MultipartFile[] file2, HttpServletRequest request, User user) throws IOException {
		for (MultipartFile mf : file2) {
			if (!mf.isEmpty()) {
				// 取得当前上传文件的文件名称
				// String contentType = mf.getContentType(); 文件类型
				// String myFileName = contentType.substring(contentType.indexOf("/")+1);
				String myFileName = mf.getOriginalFilename();
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (myFileName.trim() != "") {
					// 重命名上传后的文件名
					//String fileName = DateTools.getTimes() + "_" + myFileName;
					//String fileName = DateTools.getTimes();
					String prefix = myFileName.substring(myFileName.lastIndexOf(".")+1);
					String fileName = DateTools.getTimes()+ "."+ prefix;
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
					pic.setImageType(4);// 产品详情
					pic.setImageUrl(fileName);
					pic.setProductId(product.getId());
					pic.setImageUrlSmall(localFile.toString());
					pictureService.insertPictures(pic);
				}
			}
		}
	}

	// 到商品添加页面
	@RequestMapping("/toAddProductPage")
	public String toAddProductPage(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Dictionary> dict = dictionaryService.getAllDictionary(map);
		List<Category> cate = categoryService.getAllCategory(map);
		request.setAttribute("dictionary", dict);
		request.setAttribute("category", cate);
		return "product/product_add";
	}

	// 到商品编辑页面
	@RequestMapping("/toEditProductPage")
	public String toEditProductPage(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Dictionary> dict = dictionaryService.getAllDictionary(map);
		List<Category> cate = categoryService.getAllCategory(map);
		request.setAttribute("dictionary", dict);
		request.setAttribute("category", cate);

		String id = request.getParameter("id");
		Product product = productService.getProductById(id);
		request.setAttribute("product", product);

		// 查询所有图片
		Map<String, Object> hmap = new HashMap<String, Object>();
		List<Pictures> pic = pictureService.getAllPictures(hmap);
		request.setAttribute("picList", pic);

		return "product/product_edit";
	}

	// 修改商品
	@RequestMapping("/updateProduct")
	public String updateProduct(Model model, Product product, @RequestParam(value = "myFiles", required = false) MultipartFile[] myFiles, @RequestParam(value = "myFiles2", required = false) MultipartFile[] myFiles2, HttpServletRequest request) throws IllegalStateException, IOException {
		String imgIds = request.getParameter("imgIds");
		if (imgIds != "") {
			String[] imageid = imgIds.split(",");
			pictureService.deletePicturesById(imageid);
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		uploadFile(product, myFiles, request, user);
		uploadFile2(product, myFiles2, request, user);
		productService.updateProductById(product);
		try {
			Log("修改操作", "修改一条名为" + product.getProductName() + "的商品", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/product/getAllproduct";
	}

	// 根据id删除
	@RequestMapping("/deleteProductById")
	public String deleteProductById(HttpServletRequest request, HttpServletResponse response) {
		String dicId = request.getParameter("dicId");
		String[] dicIds = dicId.split(",");
		productService.deleteProductById(dicIds);
		pictureService.deletePicturesByProductId(dicIds);
		try {
			Log("删除操作", "删除数据产品,ID为" + dicId, request);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/product/getAllproduct";
	}

}
