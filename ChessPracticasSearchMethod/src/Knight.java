import java.util.ArrayList;

public class Knight extends Piece {
	
	
	// constructor
	public Knight( int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wKnight;
		else m_type = Utils.bKnight;
		
	}
	
	
	// this method must be completed with all the possible pieces
	
	public ArrayList<Action> getPossibleActions(State state){
		
		int c,r;
		c = state.m_agentPos.col;
		r = state.m_agentPos.row;
		Action action = null;
		ArrayList<Action> list = new ArrayList<Action>();
		
		if (m_color == 1){
			if((c>0) && (r<(state.m_boardSize-2)) && ((state.m_board[r+2][c-1] == Utils.empty) || ((state.m_board[r+2][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+2][c-1]) == 0)))) { //Left bottom side
				list.add(new Action(state.m_agentPos,new Position(r+2,c-1)));
			}
			if((c>1) && (r<(state.m_boardSize-1)) && ((state.m_board[r+1][c-2] == Utils.empty) || ((state.m_board[r+1][c-2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c-2]) == 0)))) {
				list.add(new Action(state.m_agentPos,new Position(r+1,c-2)));
			}
			if((c<(state.m_boardSize-1)) && (r<(state.m_boardSize-2)) && ((state.m_board[r+2][c+1] == Utils.empty) || ((state.m_board[r+2][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+2][c+1]) == 0)))) { //Right bottom side
				list.add(new Action(state.m_agentPos,new Position(r+2,c+1)));
			}
			if((c<(state.m_boardSize-2)) && (r<(state.m_boardSize-1)) && ((state.m_board[r+1][c+2] == Utils.empty) || ((state.m_board[r+1][c+2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c+2]) == 0)))) {
				list.add(new Action(state.m_agentPos,new Position(r+1,c+2)));
			}
			if ((r>1) && (c>0) && ((state.m_board[r-2][c-1] == Utils.empty) || ((state.m_board[r-2][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-2][c-1]) == 0)))) { //left top side
				list.add(new Action(state.m_agentPos,new Position(r-2,c-1)));
			}
			if ((r>0) && (c>1) && ((state.m_board[r-1][c-2] == Utils.empty) || ((state.m_board[r-1][c-2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c-2]) == 0)))) {
				list.add(new Action(state.m_agentPos,new Position(r-1,c-2)));
			}
			if((r>0) && (c<(state.m_boardSize-2)) && ((state.m_board[r-1][c+2] == Utils.empty) || ((state.m_board[r-1][c+2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c+2]) == 0)))) { //Right top side
				list.add(new Action(state.m_agentPos,new Position(r-1,c+2)));
			}
			if((r>1) && (c<(state.m_boardSize-1)) && ((state.m_board[r-2][c+1] == Utils.empty) || ((state.m_board[r-2][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-2][c+1]) == 0)))) {
				list.add(new Action(state.m_agentPos,new Position(r-2,c+1)));
			}
			
		}
		if (m_color == 0){
			if((c>0) && (r<(state.m_boardSize-2)) && ((state.m_board[r+2][c-1] == Utils.empty) || ((state.m_board[r+2][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+2][c-1]) == 1)))) { //Left bottom side
				list.add(new Action(state.m_agentPos,new Position(r+2,c-1)));
			}
			if((c>1) && (r<(state.m_boardSize-1)) && ((state.m_board[r+1][c-2] == Utils.empty) || ((state.m_board[r+1][c-2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c-2]) == 1)))) {
				list.add(new Action(state.m_agentPos,new Position(r+1,c-2)));
			}
			if((c<(state.m_boardSize-1)) && (r<(state.m_boardSize-2)) && ((state.m_board[r+2][c+1] == Utils.empty) || ((state.m_board[r+2][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+2][c+1]) == 1)))) { //Right bottom side
				list.add(new Action(state.m_agentPos,new Position(r+2,c+1)));
			}
			if((c<(state.m_boardSize-2)) && (r<(state.m_boardSize-1)) && ((state.m_board[r+1][c+2] == Utils.empty) || ((state.m_board[r+1][c+2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r+1][c+2]) == 1)))) {
				list.add(new Action(state.m_agentPos,new Position(r+1,c+2)));
			}
			if ((r>1) && (c>0) && ((state.m_board[r-2][c-1] == Utils.empty) || ((state.m_board[r-2][c-1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-2][c-1]) == 1)))) { //left top side
				list.add(new Action(state.m_agentPos,new Position(r-2,c-1)));
			}
			if ((r>0) && (c>1) && ((state.m_board[r-1][c-2] == Utils.empty) || ((state.m_board[r-1][c-2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c-2]) == 1)))) {
				list.add(new Action(state.m_agentPos,new Position(r-1,c-2)));
			}
			if((r>0) && (c<(state.m_boardSize-2)) && ((state.m_board[r-1][c+2] == Utils.empty) || ((state.m_board[r-1][c+2] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-1][c+2]) == 1)))) { //Right top side
				list.add(new Action(state.m_agentPos,new Position(r-1,c+2)));
			}
			if((r>1) && (c<(state.m_boardSize-1)) && ((state.m_board[r-2][c+1] == Utils.empty) || ((state.m_board[r-2][c+1] != Utils.empty) && (Utils.getColorPiece(state.m_board[r-2][c+1]) == 1)))) {
				list.add(new Action(state.m_agentPos,new Position(r-2,c+1)));
			}
			
		}		
		return list;
	}
	
	
	
	
}