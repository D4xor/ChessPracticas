package packages;
import java.util.ArrayList;

//this class implements the getPossibleActions for each type of piece

public class Rook extends Piece {
	
	
	// constructor
	public Rook( int color){
		m_color = color;
		
		if (color==0) m_type = Utils.wRook;
		else m_type = Utils.bRook;
		
	}
	
	
	// this method must be completed with all the possible pieces
	
	public ArrayList<Action> getPossibleActions(State state,Position pos){
		ArrayList<Action> list = null;
		
		if(m_color==0) {
			list = this.getVerticalDownMoves(state,pos);
			list.addAll(this.getHorizontalLeftMoves(state,pos));
			list.addAll(this.getHorizontalRightMoves(state,pos));
			list.addAll(this.getVerticalUpMoves(state,pos));
		}
		if(m_color==1) {
			list = this.getVerticalUpMoves(state,pos);
			list.addAll(this.getHorizontalRightMoves(state,pos));
			list.addAll(this.getHorizontalLeftMoves(state,pos));
			list.addAll(this.getVerticalDownMoves(state,pos));
		}
		return list;
	}
	
	
	
	
}
