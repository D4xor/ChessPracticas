import java.util.ArrayList;

public class Queen extends Piece{

	// constructor
	public Queen(int color){
		m_color = color;
			
		if (color==0) m_type = Utils.wQueen;
		else m_type = Utils.bQueen;
			
	}
		
	public ArrayList<Action> getPossibleActions(State state){
		ArrayList<Action> list = null;
			
		list = this.getUpLeftMoves(state) ;
		list.addAll(this.getUpRightMoves(state));
		list.addAll(this.getDownLeftMoves(state));
		list.addAll(this.getDownRightMoves(state));
		list.addAll(this.getHorizontalLeftMoves(state));
		list.addAll(this.getHorizontalRightMoves(state));
		list.addAll(this.getVerticalDownMoves(state));
		list.addAll(this.getVerticalUpMoves(state));
			
		return list;
	}
		
}
