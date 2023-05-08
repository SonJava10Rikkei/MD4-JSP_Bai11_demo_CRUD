<%--
  Created by IntelliJ IDEA.
  User: icom
  Date: 5/8/2023
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" style="text-align: center" width="100%">
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
    </tr>
    <tr>
        <td>${requestScope["customer"].getId()}</td>
        <td>${requestScope["customer"].getName()}</td>
        <td>${requestScope["customer"].getEmail()}</td>
        <td>${requestScope["customer"].getAddress()}</td>
    </tr>
</table>
</body>
</html>
