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
<table>
    <tr>
        <th>Player</th>
        <th>Points</th>
        <th></th>
    </tr>
    <%
        MatchDto matchDto = (MatchDto) request.getAttribute("match");
    %>
    <tr>
        <td>Player 1</td>
        <td><%= matchDto.getPlayer1() %></td>
        <td><%= matchDto.getScore1() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/match-score" method="post">
                <input type="hidden" name="id" value=<%=matchDto.getPlayer1()%>>
                <button type="submit">Score</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>Player 2</td>
        <td><%= matchDto.getPlayer2() %></td>
        <td><%= matchDto.getScore2() %></td>
        <td>
            <form action="<%= request.getContextPath() %>/match-score" method="post">
                <input type="hidden" name="id" value=<%=matchDto.getPlayer2()%>>
                <button type="submit">Score</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
