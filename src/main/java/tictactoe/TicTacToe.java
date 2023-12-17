package tictactoe;

import java.util.Arrays;

public class TicTacToe {

	private char[][] board;
    private int size;
    private char currentPlayer;
    private Boolean play;
    private static final int WIN = 3;

    public TicTacToe(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
        this.currentPlayer = 'X';
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            switchPlayer();
            return true;
        }
        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && board[row][col] == '-';
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < WIN; i++) {
            if (checkLine(board[i]) || checkLine(getColumn(i))) {
                return true;
            }
        }

        // Check diagonals
        if (checkLine(getDiagonal(true)) || checkLine(getDiagonal(false))) {
            return true;
        }

        return false;
    }

    private boolean checkLine(char[] line) {
        return Arrays.equals(line, new char[]{currentPlayer, currentPlayer, currentPlayer});
    }

    private char[] getColumn(int col) {
        char[] column = new char[WIN];
        for (int i = 0; i < WIN; i++) {
            column[i] = board[i][col];
        }
        return column;
    }

    private char[] getDiagonal(boolean mainDiagonal) {
        char[] diagonal = new char[WIN];
        for (int i = 0; i < WIN; i++) {
            diagonal[i] = mainDiagonal ? board[i][i] : board[i][size - 1 - i];
        }
        return diagonal;
    }

	public Boolean getPlay() {
		return play;
	}

	public void setPlay(Boolean play) {
		this.play = play;
	}
	
	public boolean isBoardFull() {
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (board[i][j] == '-') {
	                return false; // The board is not full
	            }
	        }
	    }
	    return true; // The board is full
	}
}
