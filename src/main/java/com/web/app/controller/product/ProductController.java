package com.web.app.controller.product;

import java.io.File;
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
	
	//查询产品
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllproduct")
	public String getAllproduct(Model model, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
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
	    map.put("startIndex", (pageNum-1) * pageSize);
		map.put("endIndex", pageSize);
		@SuppressWarnings("rawtypes")
		List cateList = productService.getAllProduct(map);
		this.initResult(model, cateList, map);
		if(request.getParameter("sub") == null){
			request.setAttribute("sub", 2);
		}
		request.setAttribute("picList", pic);
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/product_manage";
	}
	
	//添加商品
	@RequestMapping("/addProduct")
	public String addProduct(Product product, @RequestParam(value="file",required=false) MultipartFile[] file,HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
 		User user = (User) session.getAttribute("user");
 		//product
		product.setId(UUID.randomUUID().toString());
		product.setCreateUser(user.getUserId());
		product.setCreateDate(DateTools.getCurrentTime());
		productService.insertProduct(product);
		//Pictures upload
		for(MultipartFile mf : file) {  
	       if(!mf.isEmpty()){  
	    	   //取得当前上传文件的文件名称  
	    	   //String contentType = mf.getContentType();  文件类型
               //String myFileName = contentType.substring(contentType.indexOf("/")+1);  
	    	   String myFileName = mf.getOriginalFilename();
               //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
               if(myFileName.trim() !=""){  
                   //重命名上传后的文件名  
                   String fileName = DateTools.getTimes() +"_"+ myFileName;
                   //定义上传路径  
                   String path = request.getSession().getServletContext().getRealPath("upload");
                   File localFile = new File(path,fileName);  
                   if(!localFile.exists()){  
                   	  localFile.mkdirs();  
			       }
                   mf.transferTo(localFile); 
                   //pic
                   Pictures pic = new Pictures();
                   pic.setImageId(UUID.randomUUID().toString());
                   pic.setCreateDate(DateTools.getCurrentTime());
                   pic.setCreateUser(user.getUserId());
                   pic.setImageType(1);//产品图片
                   pic.setImageUrl(fileName);
                   pic.setProductId(product.getId());
                   pic.setImageUrlSmall(localFile.toString());
                   pictureService.insertPictures(pic);
               }  		
	       }
	    }
		try {
			Log("新增操作", "新增一条名为"+product.getProductName()+"的商品", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/product/getAllproduct";
	}
	
	
	//到商品添加页面
	@RequestMapping("/toAddProductPage")
	public String toAddProductPage(Model model,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Dictionary> dict = dictionaryService.getAllDictionary(map);
		List<Category> cate = categoryService.getAllCategory(map);
		request.setAttribute("dictionary", dict);
		request.setAttribute("category", cate);
		return "product/product_add";
	}
	
	//到商品编辑页面
	@RequestMapping("/toEditProductPage")
	public String toEditProductPage(Model model,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Dictionary> dict = dictionaryService.getAllDictionary(map);
		List<Category> cate = categoryService.getAllCategory(map);
		request.setAttribute("dictionary", dict);
		request.setAttribute("category", cate);
		
		String id = request.getParameter("id");
		Product product = productService.getProductById(id);
		request.setAttribute("product", product);
		
		return "product/product_edit";
	}
	
	//修改商品
	@RequestMapping("/updateProduct")
	public String updateProduct(Model model, Product product,HttpServletRequest request){
		productService.updateProductById(product);
		try {
			Log("修改操作", "修改一条名为"+product.getProductName()+"的商品", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/product/getAllproduct";
	}
	
	
	
	//创建一个通用的多部分解析器  
    /*CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
    //判断 request 是否有文件上传,即多部分请求  
    if(multipartResolver.isMultipart(request)){  
        //转换成多部分request    
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
        //取得request中的所有文件名  
        Iterator<String> iter = multiRequest.getFileNames();  
        while(iter.hasNext()){  
            //取得上传文件  
            MultipartFile file = multiRequest.getFile(iter.next());  
            if(file != null){  
                //取得当前上传文件的文件名称  
                String myFileName = file.getOriginalFilename();  
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                if(myFileName.trim() !=""){  
                    //重命名上传后的文件名  
                    String fileName = DateTools.getTimes() +"_"+ file.getOriginalFilename();
                    //定义上传路径  
                    String path = request.getSession().getServletContext().getRealPath("upload");
                    File localFile = new File(path,fileName);  
                    if(!localFile.exists()){  
                    	localFile.mkdirs();  
			        }
                    file.transferTo(localFile); 
                    //pic
                    Pictures pic = new Pictures();
                    pic.setImageId(UUID.randomUUID().toString());
                    pic.setCreateDate(DateTools.getCurrentTime());
                    pic.setCreateUser(user.getUserId());
                    pic.setImageType(1);//产品图片
                    pic.setImageUrl(fileName);
                    pic.setProductId(product.getId());
                    pic.setImageUrlSmall(localFile.toString());
                    pictureService.insertPictures(pic);
                }  
            }  
        }  
    }  */
	
	
	
}


