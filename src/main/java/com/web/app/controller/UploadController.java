package com.web.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/file")
public class UploadController {
	
	/**
	 * 推荐使用 速度快
	 * SpringMVC自带的上传文件方法  
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/upload")
	public String upload2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile((String) iter.next());
				if(file != null){
					String fileName = "demoUpload" + file.getOriginalFilename();
					//String path = "C:/"+fileName;
					String path = request.getSession().getServletContext().getRealPath("upload"); 
					System.out.println("路径：" + path);
					File targetFile = new File(path, fileName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }  
			        
					//File localFile = new File(path);
					file.transferTo(targetFile);
				}
			}
		}
		return "uploadSucc";
	}
	
	@RequestMapping("/toUpload")
	public String toUpload(){
		return "/upload";
	}
	
}
