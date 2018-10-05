<%-- 
    Document   : listBlog
    Created on : Oct 2, 2018, 2:15:08 PM
    Author     : ADMIN
--%>

<%@page import="entity.Blog"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/categorycss.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/categoryjs.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <jsp:include page="header.jsp"></jsp:include>
            </div>
            <ul class="cateul">
            <%
                List<Blog> x = (List<Blog>) request.getAttribute("listBlog");
                for (Blog elem : x) {
            %>       
            <li class="cateli">
                <form id="<%=elem.getID()%>" action="${pageContext.request.contextPath}/CategoryServlet" method="post">
                    <input type="text" class="categoryID" value="<%=elem.getID()%>" name="categoryID"/>
                    <a href="#" onclick="submitForm('<%=elem.getID()%>');"><%=elem.getHeader()%></a>
                </form>

            </li>
            <%
                }
            %>
            <form id="next" action="${pageContext.request.contextPath}/CategoryServlet" method="post">
                <input type="text" class="categoryID" value="Next" name="LinkDescription"/>
                <a href="#" onclick="submitForm('next');">Next</a>
            </form>
            <form id="previous" action="${pageContext.request.contextPath}/CategoryServlet" method="post">
                <input type="text" class="categoryID" value="previous" name="LinkDescription"/>
                <a href="#" onclick="submitForm('previous');">Previous</a>
            </form>
        </ul>
    </body>
</html>
