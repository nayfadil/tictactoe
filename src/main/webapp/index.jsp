<!-- index.jsp - Represents the JSP page for the web interface -->

<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.IntStream" %>
<%@ page import="tictactoe.TicTacToe" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tic Tac Toe</title>
</head>
<body>

<h2>Tic Tac Toe</h2>

<form action="TicTacToeServlet" method="get">
    <label for="size">Enter the board size:</label>
    <input type="number" name="size" required>
    <input type="submit" value="Start Game">
</form>

<%
    TicTacToe game = (TicTacToe) request.getAttribute("game");
    if (game != null) {
        char[][] board = game.getBoard();
%>
	<%session.setAttribute("game", game); %>
    <p>Current Player: <%= game.getCurrentPlayer() %></p>
	<% 
	
	if (game.getPlay()){
	%>
    <table border="1">
        <% for (int i = 0; i < board.length; i++) { %>
            <tr>
                <% for (int j = 0; j < board[i].length; j++) { %>
                    <td>
                        <form action="TicTacToeServlet" method="post">
                            <input type="hidden" name="row" value="<%= i %>">
                            <input type="hidden" name="col" value="<%= j %>">
                            <button type="submit" <% if (board[i][j] != '-') { %>disabled<% } %>>
                                <%= board[i][j] %>
                            </button>
                        </form>
                    </td>
                <% } %>
            </tr>
        <% } %>
    </table>
    <% } %>

    <p><%= request.getAttribute("message") %></p>

<% } %>

</body>
</html>
