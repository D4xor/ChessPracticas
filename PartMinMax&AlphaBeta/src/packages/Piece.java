package packages;
import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Piece {
	
	// members
	int m_color = -1;
	int m_type = -1;
	
	// this method must be overwritten by all the possible pieces
	                                                                              
	public ArrayList<Action> getPossibleActions(State state, Position po){
			
		return null; //never arrive here
	}
	
	
	// horizontal left moves
	public ArrayList<Action> getHorizontalLeftMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int c=c0-1; (c>=0) && !busyCell; c--){
			if (state.m_board[r0][c] == Utils.empty) {//add action
				action = new Action(pos, new Position(r0,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r0][c])) { // capture piece
					action = new Action(pos, new Position(r0,c));
					list.add(action);
				}
			}
		}
		
		return list;
	}
	
	// horizontal right moves
	public ArrayList<Action> getHorizontalRightMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int c=c0+1; (c<state.m_boardSize) && !busyCell; c++){
			if (state.m_board[r0][c] == Utils.empty) {//add action
				action = new Action(pos, new Position(r0,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r0][c])) { // capture piece
					action = new Action(pos, new Position(r0,c));
					list.add(action);
				}
			}
		}
		
		return list;
	}
		
	// vertical down moves
	public ArrayList<Action> getVerticalDownMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int r=r0+1; (r<state.m_boardSize) && !busyCell; r++){
			if (state.m_board[r][c0] == Utils.empty) {//add action
				action = new Action(pos, new Position(r,c0));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c0])) { // capture piece
					action = new Action(pos, new Position(r,c0));
					list.add(action);
				}
			}
		}
		
		return list;
	}
	
	// vertical up moves
	public ArrayList<Action> getVerticalUpMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		for(int r=r0-1; (r>=0) && !busyCell; r--){
			if (state.m_board[r][c0] == Utils.empty) {//add action
				action = new Action(pos, new Position(r,c0));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c0])) { // capture piece
					action = new Action(pos, new Position(r,c0));
					list.add(action);
				}
			}
		}
		
		return list;
	}
	
	public ArrayList<Action> getUpLeftMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0,r,c;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		c=c0-1;
		r=r0-1;
		
		while((c>=0) && (r>=0) && !busyCell){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(pos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(pos, new Position(r,c));
					list.add(action);
				}
			}
			c--;
			r--;
		}
		
		
		return list;
	}
	
	public ArrayList<Action> getUpRightMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0,r,c;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		c=c0+1;
		r=r0-1;
		
		while((c<state.m_boardSize) && (r>=0) && !busyCell){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(pos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(pos, new Position(r,c));
					list.add(action);
				}
			}
			c++;
			r--;
		}
		
		
		return list;
	}
	
	public ArrayList<Action> getDownRightMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0,r,c;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		c=c0+1;
		r=r0+1;
		
		while((c<state.m_boardSize) && (r<state.m_boardSize) && !busyCell){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(pos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(pos, new Position(r,c));
					list.add(action);
				}
			}
			c++;
			r++;
		}
		return list;
	}
		
	public ArrayList<Action> getDownLeftMoves(State state, Position pos){
		ArrayList<Action> list = new ArrayList<Action>(10);
		int agentColor = this.m_color;
		
		int c0,r0,r,c;
		c0 = pos.col;
		r0 = pos.row;
		Action action = null;
		
		Boolean busyCell = false;
		c=c0-1;
		r=r0+1;
		
		while((c>=0) && (r<state.m_boardSize) && !busyCell){
			if (state.m_board[r][c] == Utils.empty) {//add action
				action = new Action(pos, new Position(r,c));
				list.add(action);
			}else{
				busyCell = true;
				if (agentColor != Utils.getColorPiece( state.m_board[r][c])) { // capture piece
					action = new Action(pos, new Position(r,c));
					list.add(action);
				}
			}
			c--;
			r++;
		}
		return list;
	}
	
}
