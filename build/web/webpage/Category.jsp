<%-- 
    Document   : Category
    Created on : Oct 1, 2018, 2:44:15 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Category"%>
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
        <h3>Categories</h3>
        <hr>
        <ul class="cateul">
            <%
                List<Category> x = (List<Category>) request.getAttribute("Catelist");
                for (Category elem : x) {
            %>       
            <li class="cateli">
                <form id="<%=elem.getID()%>" action="${pageContext.request.contextPath}/CategoryServlet" method="get">
                    <input type="text" class="categoryID" value="<%=elem.getID()%>" name="categoryID"/>
                    <a href="#" onclick="submitForm('<%=elem.getID()%>');"><%=elem.getCategoryName()%></a>
                </form>
            </li>
            <%
                }
            %>
            <form id="next" action="${pageContext.request.contextPath}/HomeServlet" method="post">
                <input type="text" class="categoryID" value="Next" name="LinkDescription"/>
                <a href="#" onclick="submitForm('next');">Next</a>
            </form>
            <form id="previous" action="${pageContext.request.contextPath}/HomeServlet" method="post">
                <input type="text" class="categoryID" value="previous" name="LinkDescription"/>
                <a href="#" onclick="submitForm('previous');">Previous</a>
            </form>
        </ul>
    </body>
</html>
