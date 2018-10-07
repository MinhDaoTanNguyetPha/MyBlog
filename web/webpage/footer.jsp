<%-- 
    Document   : footer
    Created on : Oct 7, 2018, 3:33:54 PM
    Author     : Tusk
--%>

<%@page import="entity.Blog"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Blog> x = (List<Blog>) request.getAttribute("Bloglist");
            for (Blog elem : x) {
        %>
    <li class="cateli">
        <form id="<%="b" + elem.getID()%>" action="${pageContext.request.contextPath}/CategoryServlet" method="post">
            <input type="text" class="categoryID" value="<%=elem.getID()%>" name="categoryID"/>
            <a href="#" onclick="submitForm('<%="b" + elem.getID()%>');"><%=elem.getHeader()%></a>
        </form>
    </li>
    <% }%>
</body>
</html>
