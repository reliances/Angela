<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="page" value="${sessionScope.page}" /> 
<c:set var="path" value="${pageContext.request.contextPath }"/>
<c:set var="url" value="${param.url}" />
<c:set var="urlParams" value="${param.urlParams}" />
<c:set var="pathurl" value="${path}/${url}" />

	<div class="tdpage">
		<p class="fl">
			共${page.totalCount}条记录 共${page.totalPage}页 每页显示${page.everyPage}条
			当前第${page.currentPage}页&nbsp;</p>
		<p class="fr">
		<c:choose>
			<c:when test="${page.hasPrePage eq false}">
 			    <input class="btn37" type="button" value="首页" />
				<input class="btn37" type="button" value="上页" />
 			</c:when>
			<c:otherwise>
				<input class="btn37" onclick="window.location.href='${pathurl}?&pageAction=first${urlParams}'" type="button" value="首页" />
				<input class="btn37" onclick="window.location.href='${pathurl}?pageAction=previous${urlParams}'" type="button" value="上页" />
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.hasNextPage eq false}">
 			   <input class="btn37" type="button" value="下页" />
			   <input class="btn37" type="button" value="尾页" />
 			</c:when>
			<c:otherwise>
				<input class="btn37" onclick="window.location.href='${pathurl}?&pageAction=next${urlParams}'" type="button" value="下页" />
				<input class="btn37" onclick="window.location.href='${pathurl}?pageAction=last${urlParams}'" type="button" value="尾页" />
			</c:otherwise>
		</c:choose>
		&nbsp;
		<SELECT name="indexChange" id="indexChange" onchange="getCurrentPage(this.value);">
			<c:forEach var="index" begin="1" end="${page.totalPage}" step="1">
				<option value="${index}" ${page.currentPage eq index ? "selected" : ""}>
					第${index}页
				</option>
			</c:forEach>
		</SELECT>
		&nbsp;
		每页显示:<select name="everyPage" id="everyPage" onchange="setEveryPage(this.value);">
			       <c:forEach var="pageCount" begin="5" end="${page.totalCount}" step="5">			       	    
						<option value="${pageCount}" ${page.everyPage eq pageCount ? "selected" : ""}>
							${pageCount}条
						</option>
					</c:forEach>
		       </select>
		</p>
	</div>

<div style='display: none'>
	<a class=listlink id="indexPageHref" href='#'></a>
</div>
<script>
function getCurrentPage(index){	
	var a = document.getElementById("indexPageHref");	
	a.href = '${pathurl}?pageAction=gopage&pageKey='+index+'${urlParams}';        
    a.setAttribute("onclick",'');          
    a.click("return false");   
}
function setEveryPage(everyPage){	
	var a = document.getElementById("indexPageHref");
	var currentPage = document.getElementById('indexChange').value;
	a.href = '${pathurl}?pageAction=setpage&pageKey='+everyPage+'${urlParams}';       
    a.setAttribute("onclick",'');          
    a.click("return false");   
}
function sortPage(sortName){	
	var a = document.getElementById("indexPageHref");	
	a.href = '${pathurl}?pageAction=sort&pageKey='+sortName+'${urlParams}';      
    a.setAttribute("onclick",'');      
    a.click("return false");   
}
</script>