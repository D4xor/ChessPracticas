import java.util.ArrayList;

public class Bishop extends Piece {
	
	
	// constructor
	public Bishop(int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wBishop;
		else m_type = Utils.bBishop;
		
	}
	
	public ArrayList<Action> getPossibleActions(State state){
		ArrayList<Action> list = null;
		
		list = this.getDownLeftMoves(state);
		list.addAll(this.getDownRightMoves(state));
		list.addAll(this.getUpLeftMoves(state));
		list.addAll(this.getUpRightMoves(state));
		
		return list;
	}
	

}
