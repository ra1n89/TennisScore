<%@ page import="ru.prorain.dto.MatchDto" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07.12.2024
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/matches"><h4>Matches</h4></a>
<table border="1">
    <tr>
        <th>Player</th>
        <th>Sets</th>
        <th>Games</th>
        <th>Points</th>
        <th></th>
    </tr>
    <%
        MatchDto matchDto = (MatchDto) request.getAttribute("match");
    %>
    <tr>
        <td><%= matchDto.getPlayer1().getName() %></td>
        <td><%= matchDto.getSets1() %></td>
        <td><%= matchDto.getGame1() %></td>
        <td><%= matchDto.getScore1() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/match-score" method="post">
                <input type="hidden" name="id" value=<%=matchDto.getPlayer1().getId()%>>
                <input type="hidden" name="uuid" value=<%=matchDto.getId()%>>
                <button type="submit">Score</button>
            </form>
        </td>
    </tr>
    <tr>
        <td><%= matchDto.getPlayer2().getName() %></td>
        <td><%= matchDto.getSets2() %></td>
        <td><%= matchDto.getGame2() %></td>
        <td><%= matchDto.getScore2() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/match-score" method="post">
                <input type="hidden" name="id" value=<%=matchDto.getPlayer2().getId()%>>
                <input type="hidden" name="uuid" value=<%=matchDto.getId()%>>
                <button type="submit">Score</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
