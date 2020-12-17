package packages;

import java.util.ArrayList;

// This class contains the information needed to represent a state 
// and some useful methods

public class State {
	int[][] m_board = null;
	int m_boardSize = -1; 
	
	// constructor
	State(int[][] board){
		m_board = board;
		m_boardSize = board[0].length;
	}

	// compares if the current state is final, i.e. the agent is in the last row
	/*public boolean isFinal(){
		if (this.m_agentPos.row == this.m_boardSize-1) return true;
		else return false;
	}*/
	
	// hard copy of an State
	public State copy(){
		int[][] cBoard = new int[this.m_boardSize][this.m_boardSize];
		for(int r=0;r<this.m_boardSize;r++)
			for(int c=0; c<this.m_boardSize;c++)
				cBoard[r][c] = this.m_board[r][c];
		
		return new State(cBoard);
	}
	
	// apply a given action over the current state -which remains unmodified. Return a new state
	
	public State applyAction(Action action, Piece piece) {
		State newState = this.copy();
		newState.m_board[action.m_initPos.row][action.m_initPos.col] = Utils.empty;
		newState.m_board[action.m_finalPos.row][action.m_finalPos.col] = piece.m_type;
		
		return newState;
	}
	
	public Boolean findPiece(int color,int row, int col) {
		if((this.m_board[row][col]>=0) && (this.m_board[row][col]<=5) && (color==0)) {
			return true;
		}else if ((this.m_board[row][col]>=6) && (this.m_board[row][col]<=11) && (color == 1)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Position> getPositionPieceColor(int color){
		ArrayList<Position> posPieces = new ArrayList<Position>();
		for(int i = 0; i<this.m_boardSize;i++) {
			for(int j = 0; j<this.m_boardSize;j++) {
				if(this.findPiece(color, i, j)) {
					posPieces.add(new Position(i,j));
				}
			}
		}		
		return posPieces;
	}
	
	public Piece createPiece(int row, int col) {
		int pieceValue = this.m_board[row][col];
		switch(pieceValue){
		case Utils.wRook: return new Rook(0); 
		case Utils.bRook: return new Rook(1);
		case Utils.wPawn: return new Pawn(0);
		case Utils.bPawn: return new Pawn(1);
		case Utils.wBishop: return new Bishop(0);
		case Utils.bBishop: return new Bishop(1);
		case Utils.wKnight: return new Knight(0);
		case Utils.bKnight: return new Knight(1);
		case Utils.wQueen: return new Queen(0);
		case Utils.bQueen: return new Queen(1);
		case Utils.wKing: return new King(0);
		case Utils.bKing: return new King(1);
		default: break;
		}
		return null;	
	}
	
	public Boolean isNoKing(int color) {
		for(int i = 0; i<this.m_boardSize;i++) {
			for(int j = 0; j<this.m_boardSize;j++) {
				if((color == 0) && (Utils.wKing==this.m_board[i][j])) {
					return false;
				}else if((color == 1) && (Utils.bKing==this.m_board[i][j])){
					return false;
				}
			}
		}
		return true;
	}
	
	public Boolean positionForColor(ArrayList<Position> positions,Position posi) {
		for(Position po: positions) {
			if((po.row==posi.row) && (po.col == posi.col)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean moveForColor(ArrayList<Action> actions,Position posi) {
		for(Action ac: actions) {
			if((ac.m_finalPos.row==posi.row) && (ac.m_finalPos.col == posi.col)) {
				return true;
			}
		}
		return false;
	}
	
}
