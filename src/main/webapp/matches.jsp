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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
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
                <a class="nav-link" href="index.html">Home</a>
                <a class="nav-link" href="matches.jsp">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <input class="input-filter" placeholder="Filter by name" type="text" />
            <div>
                <a href="matches.jsp">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <% List<Match> matchList = (List<Match>) request.getAttribute("list");

                for(Match match: matchList ){
            %>
            <tr>
                <td><%= match.getPlayer1().getName() %></td>
                <td><%= match.getPlayer2().getName()  %></td>
                <td><span class="winner-name-td"><%= match.getWinner().getName()  %></span></td>
            </tr>
            <%}%>
        </table>

        <% Long pages = (Long) request.getAttribute("pages");
            Long i = 1L;
        %>

        <div class="pagination">
            <a class="prev" href="<%= request.getContextPath() %>/matches?page=<%= i - 1 < 1 ? 1 : i - 1 %>"> < </a>
            <a class="num-page current" href="<%= request.getContextPath() %>/matches?page=<%= i %>"><%= i %></a>
            <a class="num-page" href="<%= request.getContextPath() %>/matches?page=<%= i + 1 %>"><%= i + 1 %></a>
            <a class="num-page" href="<%= request.getContextPath() %>/matches?page=<%= i + 2 %>"><%= i + 2 %></a>
            <a class="next" href="<%= request.getContextPath() %>/matches?page=<%= i + 1 > pages ? i = pages : i + 1 %>"> > </a>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
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
--%>
