package packages;
import java.util.ArrayList;

public class Queen extends Piece{

	// constructor
	public Queen(int color){
		m_color = color;
			
		if (color==0) m_type = Utils.wQueen;
		else m_type = Utils.bQueen;
			
	}
		
	public ArrayList<Action> getPossibleActions(State state,Position pos){
		ArrayList<Action> list = null;
		if(m_color==0) {
			list = this.getVerticalDownMoves(state,pos);
			list.addAll(this.getDownLeftMoves(state,pos));
			list.addAll(this.getDownRightMoves(state,pos));
			list.addAll(this.getHorizontalLeftMoves(state,pos));
			list.addAll(this.getHorizontalRightMoves(state,pos));
			list.addAll(this.getUpLeftMoves(state,pos));
			list.addAll(this.getUpRightMoves(state,pos));
			list.addAll(this.getVerticalUpMoves(state,pos));
		}
		if(m_color==1) {
			list = this.getVerticalUpMoves(state,pos); //Up move
			list.addAll(this.getUpRightMoves(state,pos));
			list.addAll(this.getUpLeftMoves(state,pos));
			list.addAll(this.getHorizontalRightMoves(state,pos));
			list.addAll(this.getHorizontalLeftMoves(state,pos));
			list.addAll(this.getDownRightMoves(state,pos));
			list.addAll(this.getDownLeftMoves(state,pos));
			list.addAll(this.getVerticalDownMoves(state,pos)); //Down move
		}
			
		return list;
	}
		
}
