package com.web.app.tools;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @Title:Pager     
 * @Description:
 * @Auth:LiangRui   
 * @CreateTime:2017年3月7日 上午9:30:12       
 * @version V1.0
 */
public class Pager extends TagSupport {

	private static final long serialVersionUID = 1L;

	private Integer curPage = 1;
	private Integer totalPage;
	private Integer pageSize = 10;
	private Integer totalCount = 0;
	private String formId;
    
	
	public Integer getCurPage() {
		return curPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public int doStartTag() throws JspException {
		
		JspWriter out = pageContext.getOut();

		int pageNumber = 0;
		if (totalPage%pageSize==0) {
			pageNumber = totalPage/pageSize;
		} else {
			pageNumber = (totalPage/pageSize)+1;
		}
		if (curPage < 1) {
			curPage = 1;
		}

		try {
			if (pageNumber > 0) {
				out.print("<script type='text/javascript'>" + 
						  		"function go(pageNum){" + 
						  			"var f = document.getElementById('" + formId + "');"+
						  			"f.action = f.action + '?pageNum=' + pageNum + '&pageSize="+pageSize+"';"+
						  			"f.submit();"+
		  						"}" + 
						  "</script>");
				
				out.print("<div class='pagination right'><ul>");
				int start = 1;
				int end = totalPage;
				for(int i=4;i>=1;i--){
					if((curPage-i)>=1){
						start = curPage-i;
						break;
					}
				}
				for(int i=4;i>=1;i--){
					if((curPage+i)<=totalPage){
						end = curPage+i;
						break;
					}
				}
				//如果小于9则右侧补齐
				if(end-start+1<=9){
					Integer padLen = 9-(end-start+1);
					for(int i=padLen;i>=1;i--){
						if((end+i)<=totalPage){
							end = end+i;
							break;
						}
					}
				}
				
				//如果还小于9左侧补齐
				if(end-start+1<=9){
					Integer padLen = 9-(end-start+1);
					for(int i=padLen;i>=1;i--){
						if((start-i)>=1){
							start = start-i;
							break;
						}
					}
				}
				
				if(curPage>1){
					if(start>1){
						out.print("<li><a href='javascript:go(1)'>首页</a></li>");
					}
					out.print("<li><a href='javascript:go("+(curPage-1)+")'>上一页</a></li>");
				}
				
				for(int i=start;i<=end;i++){
					if(i==curPage){
						out.print("<li class='active'><a href='#'>" + i + "</a></li>");
					}else{
						out.print("<li><a href='javascript:go("+i+")'>" + i + "</a></li>");
					}
				}
				if(curPage<totalPage){
					out.print("<li><a href='javascript:go("+(curPage+1)+")'>下一页</a></li>");
					if(end<totalPage){
						out.print("<li><a href='javascript:go("+totalPage+")'>尾页</a></li>");
					}
				}
				out.print("<li><a href='javascript:void(0)'>共" + totalPage + "页" + this.totalCount + "条</a></li>");
				out.print("</ul>");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doStartTag();

	}
	
	public static Integer getStartIndex(Integer pageNum, Integer pageSize){
		Integer res = 0;
		if(pageNum>0){
			res = (pageNum-1)*pageSize;
		}
		return res;
	}

}
