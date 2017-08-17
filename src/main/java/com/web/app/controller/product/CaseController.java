package com.web.app.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.web.app.controller.BaseController;
import com.web.app.entity.CaseInfo;
import com.web.app.entity.Pictures;
import com.web.app.entity.Product;
import com.web.app.entity.User;
import com.web.app.service.CaseInfoService;
import com.web.app.service.PicturesService;
import com.web.app.tools.DateTools;
import com.web.app.tools.Pager;

/**
 * @Title:CaseController     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年8月15日 下午8:25:40       
 * @version V1.0
 */
@Controller
@RequestMapping("/case")
public class CaseController extends BaseController {
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private PicturesService pictureService;
	
	//查询案例
	@SuppressWarnings("unchecked")
	@RequestMapping("/getAllCase")
	public String getAllCase(Model model, @RequestParam(required=false) Integer pageNum, 
			@RequestParam(required=false) Integer pageSize, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Pager pager = new Pager();
		if (pageNum == null) {
			pageNum = pager.getCurPage();
		}
		if (pageSize == null) {
			pageSize = pager.getPageSize();
		}
		int totalCount = caseInfoService.countByCriteria();
	    this.initPage(map, pageNum, pageSize, totalCount);
	    map.put("startIndex", (pageNum-1) * pageSize);
		map.put("endIndex", pageSize);
		@SuppressWarnings("rawtypes")
		List caseList = caseInfoService.getAllCaseInfo(map);
		this.initResult(model, caseList, map);
		if(request.getParameter("sub") == null){
			request.setAttribute("sub", 2);
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "product/case_manage";
	}
	
	//添加案例
	@RequestMapping("/addCase")
	public String addCase(Model model, CaseInfo caseInfo,HttpServletRequest request) throws IllegalStateException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		caseInfo.setId(UUID.randomUUID().toString());
		caseInfo.setCreateUser(user.getUserId());
		caseInfo.setCreateDate(DateTools.getCurrentTime());
		caseInfoService.insertCaseInfo(caseInfo);
		//Pictures upload
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
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
                        pic.setImageType(2);//案例图片
                        pic.setImageUrl(fileName);
                        pic.setProductId(caseInfo.getId());
                        pic.setImageUrlSmall(localFile.toString());
                        pictureService.insertPictures(pic);
                    }  
                }  
            }  
        }  
		try {
			Log("新增操作", "新增一条名为"+caseInfo.getTitle()+"的案例", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("sub", request.getParameter("sub"));
		return "redirect:/case/getAllCase";
	}
	
	
	//到案例添加页面
	@RequestMapping("/toAddCasePage")
	public String toAddCasePage(Model model,HttpServletRequest request) {
		//Map<String,Object> map = new HashMap<String,Object>();
		//List<Dictionary> dict = dictionaryService.getAllDictionary(map);
		//List<CaseInfo> cate = caseInfoService.getAllCaseInfo(map);
		//request.setAttribute("dictionary", dict);
		//request.setAttribute("category", cate);
		return "product/case_add";
	}
	
	//到案例编辑页面
	@RequestMapping("/toEditCasePage")
	public String toEditCasePage(Model model,HttpServletRequest request) {
		String id = request.getParameter("id");
		CaseInfo caseInfo = caseInfoService.getCaseInfoById(id);
		request.setAttribute("caseInfo", caseInfo);
		return "product/case_edit";
	}
	
	//删除操作
	@RequestMapping("/deleteCase")
	public String deleteCase(Model model,HttpServletRequest request) {
		String ids = request.getParameter("Ids");
		String[] id = ids.split(",");
		caseInfoService.deleteCaseInfoById(id);
		return "redirect:/case/getAllCase";
	}
	
	//修改案例
	@RequestMapping("/updateCase")
	public String updateCase(Model model, CaseInfo caseInfo,HttpServletRequest request){
		caseInfoService.updateCaseInfoById(caseInfo);
		try {
			Log("修改操作", "修改一条名为"+caseInfo.getTitle()+"的案例", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/case/getAllCase";
	}
}


