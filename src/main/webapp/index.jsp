<%@ page import="com.example.servletapp.model.UserModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<%
  UserModel user = (UserModel) session.getAttribute("user");
%>
<%if (user != null) {%>
  <h1>Hello <%=user.getFullName()%>. Welcome to servlet page</h1>
<% } else { %>
  <h1>Hello. Welcome to servlet page</h1>
<% } %>

<br/>
<form method="post" style="display: flex; flex-direction: column">
  <%if (user == null) {%>
  <a href="/login"><button type="button">Login</button></a><br>
  <a href="/register"><button type="button">Register</button></a>
  <% } %>

</form>
<%if (user != null) {%>
  <a href="/logout"><button type="submit">Logout</button></a>
<% } %>
</body>
</html>