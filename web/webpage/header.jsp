<%-- 
    Document   : header
    Created on : Oct 1, 2018, 2:19:03 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/headercss.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>My Blog</h1>
        <ul class="hearderul">
            <li class="liul"><a class="active" href="${pageContext.request.contextPath}/HomeServlet">Home</a></li>
            <li class="liul"><a href="${pageContext.request.contextPath}/ContactServlet">Contact</a></li>
        </ul>
    </body>
</html>
