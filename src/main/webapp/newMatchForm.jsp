<%--
  Created by IntelliJ IDEA.
  User: prora
  Date: 01.12.2024
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NEW MATCH</title>
</head>
<body>
<br/>
<br/>
<br/>
<form method="post" action="new-match">
    <label>
        First player name:
        <input name="firstPlayerName" required>
    </label>
    <br>
    <br>
    <label>
        Second player name:
        <input name="secondPlayerName" required>
    </label>
    <br>
    <br>
    <label>
        <input type="submit" value="Начать">
    </label>
</form>
</body>
</html>
