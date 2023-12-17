package tictactoe;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TicTacToeServlet")
public class TicTacToeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int size = Integer.parseInt(request.getParameter("size"));
        TicTacToe game = new TicTacToe(size);
        game.setPlay(true);
        request.setAttribute("game", game);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	TicTacToe game = (TicTacToe) request.getSession().getAttribute("game");

        int row = Integer.parseInt(request.getParameter("row"));
        int col = Integer.parseInt(request.getParameter("col"));

        if (game.makeMove(row, col)) {
            request.setAttribute("message", "Move successful");
        } else {
            request.setAttribute("message", "Invalid move. Try again.");
        }
        
        if(game.isBoardFull()) {
        	request.setAttribute("message", "Board Full, Please Restart!");
        }
        if (game.checkWinner()) {
            request.setAttribute("message", "Player " + game.getCurrentPlayer() + " wins!");
            game.setPlay(false);
        }
        
        
        
        request.setAttribute("game", game);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
