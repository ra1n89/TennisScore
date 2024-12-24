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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="index.jsp">Home</a>
                <a class="nav-link" href="matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <%
                    MatchDto matchDto = (MatchDto) request.getAttribute("match");
                %>
                <tbody>
                <tr class="player1">
                    <td><%= matchDto.getPlayer1().getName() %></td>
                    <td><%= matchDto.getSets1() %></td>
                    <td><%= matchDto.getGame1() %></td>
                    <td><%= matchDto.getScore1() %></td>
                    <td class="table-text">

                        <form action="<%= request.getContextPath() %>/match-score" method="post">
                            <input type="hidden" name="id" value=<%=matchDto.getPlayer1().getId()%>>
                            <input type="hidden" name="uuid" value=<%=matchDto.getId()%>>
                            <button class="score-btn" type="submit">Score</button>
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td><%= matchDto.getPlayer2().getName() %></td>
                    <td><%= matchDto.getSets2() %></td>
                    <td><%= matchDto.getGame2() %></td>
                    <td><%= matchDto.getScore2() %></td>
                    <td class="table-text">

                        <form action="<%= request.getContextPath() %>/match-score" method="post">
                            <input type="hidden" name="id" value=<%=matchDto.getPlayer2().getId()%>>
                            <input type="hidden" name="uuid" value=<%=matchDto.getId()%>>
                            <button class="score-btn" type="submit">Score</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>
<%--

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
--%>
