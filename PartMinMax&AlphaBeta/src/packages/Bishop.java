package packages;
import java.util.ArrayList;

public class Bishop extends Piece {
	
	
	// constructor
	public Bishop(int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wBishop;
		else m_type = Utils.bBishop;
		
	}
	
	public ArrayList<Action> getPossibleActions(State state, Position pos){
		ArrayList<Action> list = null;
		
		if (m_color == 0) {
			list = this.getDownLeftMoves(state,pos);
			list.addAll(this.getDownRightMoves(state,pos));
			list.addAll(this.getUpLeftMoves(state,pos));
			list.addAll(this.getUpRightMoves(state,pos));
		}
		if (m_color == 1) {
			list = this.getUpRightMoves(state,pos);
			list.addAll(this.getUpLeftMoves(state,pos));
			list.addAll(this.getDownRightMoves(state,pos));
			list.addAll(this.getDownLeftMoves(state,pos));
		}
		return list;
	}
	

}
