import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Pawn extends Piece {
	
	
	// constructor
	public Pawn( int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wPawn;
		else m_type = Utils.bPawn;
		
	}
	
	
	// this method must be completed with all the possible pieces
	
	public ArrayList<Action> getPossibleActions(State state){
		
		int c,r;
		c = state.m_agentPos.col;
		r = state.m_agentPos.row;
		Action action = null;
		ArrayList<Action> list = new ArrayList<Action>(3);
		
		if (m_color == 1){
			if ((r<(state.m_boardSize-1)) && state.m_board[r+1][c] == Utils.empty){//standard pawn move
				list.add( new Action(state.m_agentPos, new Position(r+1,c)) );
			}
			if ((r==1) && (state.m_board[r+2][c] == Utils.empty)){//starting pawn move
				list.add( new Action(state.m_agentPos, new Position(r+2,c)) );
			}
			if ((c>0) && (r<(state.m_boardSize-1)) && (state.m_board[r+1][c-1] != Utils.empty) 
						&& (Utils.getColorPiece(state.m_board[r+1][c-1]) == 0)){//capture
				list.add( new Action(state.m_agentPos, new Position(r+1,c-1)) );
			}
			if ((c<(state.m_boardSize-1)) && (r<(state.m_boardSize-1)) && (state.m_board[r+1][c+1] != Utils.empty) 
						&& (Utils.getColorPiece(state.m_board[r+1][c+1]) == 0)){//capture
				list.add( new Action(state.m_agentPos, new Position(r+1,c+1)) );
			}
		}
		
		
		if (m_color == 0){// white pawn
			if ((r<(state.m_boardSize-1)) && state.m_board[r+1][c] == Utils.empty){//standard pawn move
				list.add( new Action(state.m_agentPos, new Position(r+1,c)) );
			}
			if ((r==1) && (state.m_board[r+2][c] == Utils.empty)){//starting pawn move
				list.add( new Action(state.m_agentPos, new Position(r+2,c)) );
			}
			if ((c>0) && (r<(state.m_boardSize-1)) && (state.m_board[r+1][c-1] != Utils.empty) 
						&& (Utils.getColorPiece(state.m_board[r+1][c-1]) == 1)){//capture
				list.add( new Action(state.m_agentPos, new Position(r+1,c-1)) );
			}
			if ((c<(state.m_boardSize-1)) && (r<(state.m_boardSize-1)) && (state.m_board[r+1][c+1] != Utils.empty) 
						&& (Utils.getColorPiece(state.m_board[r+1][c+1]) == 1)){//capture
				list.add( new Action(state.m_agentPos, new Position(r+1,c+1)) );
			}
		}	
				
		return list;
	}
	
	
	
	
}
