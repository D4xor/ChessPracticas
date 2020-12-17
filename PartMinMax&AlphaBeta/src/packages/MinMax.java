package packages;

import java.util.ArrayList;

public class MinMax {

	int m_profundidad;
	Evaluate m_eval;
	State actual = null;
	
	
	MinMax(int profundidad, Evaluate eval){
		m_profundidad = profundidad;
		m_eval = eval;
	}
	
	public Action searchAction(State board, int colorPlayer) {
		int maxValue = Integer.MIN_VALUE;
		int minValue = Integer.MAX_VALUE;
		int value;
		Action bestAction = null;
		for(Position position : board.getPositionPieceColor(colorPlayer)) {
			Piece piece = board.createPiece(position.row, position.col);
			//System.out.println("piece : "+	piece.m_type );
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if(possibleActions.size()!=0) {
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					value = minOrMax(actual,colorPlayer,possibleActions.get(i).m_finalPos);
					//System.out.println(value);
					if((colorPlayer == 0) && (value > maxValue)) {
						maxValue = value;
						bestAction = possibleActions.get(i);
					}else if ((colorPlayer == 1) && (value < minValue)){
						minValue = value;
						bestAction = possibleActions.get(i);
					}
				}
			}
		}
		return bestAction;
	}
	
	public int max(State board,int profundidad, Position pos) {
		if((profundidad == 0) || board.isNoKing(0) || board.isNoKing(1)) {
			return (m_eval.evaluatePosition(board,pos));
		}
		int maxValue = Integer.MIN_VALUE;
		for(Position position : board.getPositionPieceColor(0)) {
			Piece piece = board.createPiece(position.row, position.col);
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if (possibleActions.size() != 0){
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					int value = min(actual,profundidad-1,possibleActions.get(i).m_finalPos);
					if(value > maxValue) {
						maxValue = value;
					}
				}
			}
		}
		return maxValue;
	}
	
	public int min(State board,int profundidad, Position pos) {
		if((profundidad == 0) || board.isNoKing(0) || board.isNoKing(1)) {
			return (m_eval.evaluatePosition(board,pos));
		}
		int minValue = Integer.MAX_VALUE;
		for(Position position : board.getPositionPieceColor(1)) {
			Piece piece = board.createPiece(position.row, position.col);
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if (possibleActions.size() != 0){
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					int value = max(actual,profundidad-1,possibleActions.get(i).m_finalPos);
					if(value < minValue) {
						minValue = value;
					}
				}
			}
		}
		return minValue;
	}
	
	public int minOrMax(State board,int colorPlayer, Position pos) {
		int prof = m_profundidad; 
		int val;
		if(colorPlayer == 0) {
			val = min(board,prof-1,pos);
		}else {
			val = max(board,prof-1,pos);
		}
		return val;
	}
}
