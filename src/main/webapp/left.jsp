 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <ul>
	<li class=""><a href="../user/home"><i class="icon icon-home"></i> <span>首页</span></a></li>
    <c:forEach items="${menus }" var="men">
    	<c:if test="${men.mName eq '系统管理' }">
    		<c:if test="${sub ==1 }">
    			<li class="submenu open">
    		</c:if>
    		<c:if test="${sub !=1 }">
    			<li class="submenu">
    		</c:if>
			<a href="#"><i class="${men.mClass }"></i> <span>系统管理</span> <span class="label"></span></a>
			<ul>
				<c:forEach items="${menus }" var="men">
					<c:if test="${men.mName eq '系统管理' }">
						<c:forEach items="${menus }" var="ch">
			       			<c:if test="${men.mId eq ch.parentId }">
								<li><a href="${ch.url }">${ch.mName }</a></li>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</ul>
		</li>
    	</c:if>
    	
    	<c:if test="${men.mName eq '商品管理' && men.parentId eq null}">
    		<c:if test="${sub == 2}">
    			<li class="submenu open">
    		</c:if>
    		<c:if test="${sub != 2}">
    			<li class="submenu">
    		</c:if>
	           <a href="#"><i class="${men.mClass }"></i> <span>商品管理</span><span class="label"></span></a>
	           <ul>
				 <c:forEach items="${menus }" var="men">
					<c:if test="${men.mName eq '商品管理' }">
						<c:forEach items="${menus }" var="ch">
			       			<c:if test="${men.mId eq ch.parentId }">
								<li><a href="${ch.url }">${ch.mName }</a></li>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			   </ul>
		    </li>
    	</c:if>
    	
    	<c:if test="${men.mName eq '图片管理' && men.parentId eq null}">
	    	<c:if test="${sub == 3}">
    			<li class="submenu open">
    		</c:if>
    		<c:if test="${sub != 3}">
    			<li class="submenu">
    		</c:if>
			     <a href="#"><i class="${men.mClass }"></i> <span>图片管理</span><span class="label"></span></a>
		         <ul>
					<c:forEach items="${menus }" var="men">
						<c:if test="${men.mName eq '图片管理' }">
							<c:forEach items="${menus }" var="ch">
				       			<c:if test="${men.mId eq ch.parentId }">
									<li><a href="${ch.url }">${ch.mName }</a></li>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
				 </ul>
			</li>
	    </c:if>
	    
    	<c:if test="${men.mName eq '案例管理' && men.parentId eq null}">
    		<c:if test="${sub == 4}">
    			<li class="submenu open">
    		</c:if>
    		<c:if test="${sub != 4}">
    			<li class="submenu">
    		</c:if>
	           <a href="#"><i class="${men.mClass }"></i> <span>案例管理</span><span class="label"></span></a>
	           <ul>
				 <c:forEach items="${menus }" var="men">
					<c:if test="${men.mName eq '案例管理' }">
						<c:forEach items="${menus }" var="ch">
			       			<c:if test="${men.mId eq ch.parentId }">
								<li><a href="${ch.url }">${ch.mName }</a></li>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			   </ul>
		    </li>
    	</c:if>
    </c:forEach>
</ul>
   
    
 
