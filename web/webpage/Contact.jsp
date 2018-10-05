<%-- 
    Document   : Contact
    Created on : Oct 2, 2018, 8:05:11 PM
    Autdor     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/homepagecss.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contactcss.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <jsp:include page="header.jsp"></jsp:include>
        </div>
        <div >
            <div id="cgd">
                <form class="table1" action="ContactServlet" method="post">
                    <fieldset>
                        <legend>All fields are required</legend>
                    <table>
                    <tr>
                        <td>Name:</td><td><input type="text"/></td>
                    </tr>
                    <tr>
                        <td>Email:</td><td><input type="email"/></td>
                    </tr>
                    <tr>
                        <td>Phone:</td><td><input type="text"/></td>
                    </tr>
                    <tr>
                        <td>Company:</td><td><input type="text"/></td>
                    </tr>
                    <tr>
                        <td class="MessageHandler">Message:</td><td><textarea rows="4" cols="30"></textarea></td>
                    </tr>
                    <tr>
                        <td class="buttonHeight"><button>Send</button></td>
                    </tr>
                    </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div id="cgd2">

            <jsp:include page="Category.jsp"></jsp:include>

        </div>
    </body>
</html>
