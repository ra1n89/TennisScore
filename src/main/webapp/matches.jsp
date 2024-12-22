<%@ page import="ru.prorain.entity.Match" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.12.2024
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="matches">
    <input name = "filter_by_player_name" >
    <button type="submit"></button>
</form>

<table border="1">
    <tr>
        <td>Player One</td>
        <td>Player Two</td>
        <td>Winner</td>
    </tr>
    <% List<Match> matchList = (List<Match>) request.getAttribute("list");

    for(Match match: matchList ){
    %>
    <tr>
        <td><%= match.getPlayer1().getName() %></td>
        <td><%= match.getPlayer2().getName()  %></td>
        <td><%= match.getWinner().getName()  %></td>
    </tr>
<%}%>
</table>
<br>
<% Long pages = (Long) request.getAttribute("pages");
    Long i = 1L;
%>

<a href="<%= request.getContextPath() %>/matches?page=<%= i - 1 < 1 ? 1 : i - 1 %>"><</a>
<a href="<%= request.getContextPath() %>/matches?page=<%= i %>"><%= i %></a>
<a href="<%= request.getContextPath() %>/matches?page=<%= i + 1 > pages ? i = pages : i + 1 %>">></a>
</body>
</html>
