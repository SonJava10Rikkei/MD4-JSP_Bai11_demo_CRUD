<%--
  Created by IntelliJ IDEA.
  User: icom
  Date: 5/8/2023
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="text-align : center; color : violet">======== CUSTOMER MANAGER =========</h1>
<a href="/customer?action=create">Create Customer</a>
<table border="1" style="text-align: center" width="100%">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>EMAIL</th>
        <th>ADDRESS</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items='${requestScope["customerList"]}' var="ctm">
        <tr>
            <td>${ctm.getId()}</td>
            <td><a href="/customer?action=detail&id=${ctm.getId()}">${ctm.getName()}</a></td>
            <td>${ctm.getEmail()}</td>
            <td>${ctm.getAddress()}</td>
            <td>
                <a href="/customer?action=edit&id=${ctm.getId()}">
                    <button>Edit</button>
                </a>
            </td>
            <td>
                <a href="/customer?action=delete&id=${ctm.getId()}">
                    <button>Delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
