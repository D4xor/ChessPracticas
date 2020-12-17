package packages;
import java.util.ArrayList;

public class King extends Piece {
	
	
	// constructor
	public King( int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wKing;
		else m_type = Utils.bKing;
		
	}
	
	
	// this method must be completed with all the possible pieces
	
	public ArrayList<Action> getPossibleActions(State state, Position pos){
		
		int c,r;
		c = pos.col;
		r = pos.row;
		Action action = null;
		ArrayList<Action> list = new ArrayList<Action>();
		
		if (m_color == 1){
			//Top move
			if ((r>0) && ((state.m_board[r-1][c] == Utils.empty) || (state.m_board[r-1][c] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c]) == 0))) {
				list.add(new Action(pos,new Position(r-1,c)));
			}
			//Top Right move
			if((r>0) && (c<(state.m_boardSize-1)) && ((state.m_board[r-1][c+1] == Utils.empty) || (state.m_board[r-1][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c+1]) == 0))) {
				list.add(new Action(pos,new Position(r-1,c+1)));
			}
			//Top left move
			if((r>0) && (c>0) && ((state.m_board[r-1][c-1] == Utils.empty) || (state.m_board[r-1][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c-1]) == 0))) {
				list.add(new Action(pos,new Position(r-1,c-1)));
			}
			//Right move
			if ((c<state.m_boardSize-1) && ((state.m_board[r][c+1] == Utils.empty) || (state.m_board[r][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r][c+1]) == 0))) {
				list.add(new Action(pos,new Position(r,c+1)));
			}
			//Left move
			if ((c>0) && ((state.m_board[r][c-1] == Utils.empty) || (state.m_board[r][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r][c-1]) == 0))) {
				list.add(new Action(pos,new Position(r,c-1)));
			}
			//Bottom right move
			if((r<state.m_boardSize-1) && (c<(state.m_boardSize-1)) && ((state.m_board[r+1][c+1] == Utils.empty) || (state.m_board[r+1][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c+1]) == 0))) {
				list.add(new Action(pos,new Position(r+1,c+1)));
			}
			//Bottom left move
			if((r<state.m_boardSize-1) && (c>0) && ((state.m_board[r+1][c-1] == Utils.empty)|| (state.m_board[r+1][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c-1]) == 0))) { 
				list.add(new Action(pos,new Position(r+1,c-1)));
			}
			//Bottom move
			if ((r<state.m_boardSize-1) && ((state.m_board[r+1][c] == Utils.empty) || (state.m_board[r+1][c] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c]) == 0))) { 
				list.add(new Action(pos,new Position(r+1,c)));
			}
		}
		if (m_color == 0){
			//Bottom move
			if ((r<state.m_boardSize-1) && ((state.m_board[r+1][c] == Utils.empty) || (state.m_board[r+1][c] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c]) == 1))) { 
				list.add(new Action(pos,new Position(r+1,c)));
			}
			//Bottom left move
			if((r<state.m_boardSize-1) && (c>0) && ((state.m_board[r+1][c-1] == Utils.empty)|| (state.m_board[r+1][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c-1]) == 1))) { 
				list.add(new Action(pos,new Position(r+1,c-1)));
			}
			//Bottom right move
			if((r<state.m_boardSize-1) && (c<(state.m_boardSize-1)) && ((state.m_board[r+1][c+1] == Utils.empty) || (state.m_board[r+1][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c+1]) == 1))) {
				list.add(new Action(pos,new Position(r+1,c+1)));
			}
			//Left move
			if ((c>0) && ((state.m_board[r][c-1] == Utils.empty) || (state.m_board[r][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r][c-1]) == 1))) {
				list.add(new Action(pos,new Position(r,c-1)));
			}
			//Right move
			if ((c<state.m_boardSize-1) && ((state.m_board[r][c+1] == Utils.empty) || (state.m_board[r][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r][c+1]) == 1))) {
				list.add(new Action(pos,new Position(r,c+1)));
			}
			//Top left move
			if((r>0) && (c>0) && ((state.m_board[r-1][c-1] == Utils.empty) || (state.m_board[r-1][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c-1]) == 1))) {
				list.add(new Action(pos,new Position(r-1,c-1)));
			}
			//Top Right move
			if((r>0) && (c<(state.m_boardSize-1)) && ((state.m_board[r-1][c+1] == Utils.empty) || (state.m_board[r-1][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c+1]) == 1))) {
				list.add(new Action(pos,new Position(r-1,c+1)));
			}
			//Top move
			if ((r>0) && ((state.m_board[r-1][c] == Utils.empty) || (state.m_board[r-1][c] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c]) == 1))) {
				list.add(new Action(pos,new Position(r-1,c)));
			}
		}		
		return list;
	}
	
	
	
	
}
