<%-- 
    Document   : Homepage
    Created on : Oct 1, 2018, 2:13:45 PM
    Author     : ADMIN
--%>

<%@page import="entity.Blog"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/homepagecss.css">
        <title>Home page</title>
    </head>
    <body>
        <div>
            <jsp:include page="header.jsp"></jsp:include>
        </div>
        <div >
            <div id="cgd">
                <%
                    List<Blog> x = (List<Blog>) request.getAttribute("Bloglist");
                    for (Blog elem : x) {
                %>
                <li class="cateli">
                    <form id="<%="b"+elem.getID()%>" action="${pageContext.request.contextPath}/CategoryServlet" method="post">
                        <input type="text" class="categoryID" value="<%=elem.getID()%>" name="categoryID"/>
                        <a href="#" onclick="submitForm('<%="b"+elem.getID()%>');"><%=elem.getHeader()%></a>
                    </form>
                </li>
                <% } %>
                <jsp:include page="footer.jsp"></jsp:include>
            </div>
        </div>
        <div id="cgd2">

            <jsp:include page="Category.jsp"></jsp:include>

        </div>
</body>
</html>
