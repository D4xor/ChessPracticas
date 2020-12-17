package packages;

import java.util.ArrayList;

public class AlfaBeta {
	int m_profundidad;
	Evaluate m_eval;
	State actual = null;
	
	
	AlfaBeta(int profundidad, Evaluate eval){
		m_profundidad = profundidad;
		m_eval = eval;
	}
	
	public Action searchAction(State board, int colorPlayer) {
		int maxValue = Integer.MIN_VALUE;
		int minValue = Integer.MAX_VALUE;
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int value;
		Action bestAction = null;
		for(Position position : board.getPositionPieceColor(colorPlayer)) {
			Piece piece = board.createPiece(position.row, position.col);
			//System.out.println("piece : "+	piece.m_type );
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if(possibleActions.size()!=0) {
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					value = minOrMax(actual,colorPlayer,possibleActions.get(i).m_finalPos,alpha,beta);
					//System.out.println(value);
					if((colorPlayer == 0)) {
						if(value >= beta) {
							value = beta;
							System.out.println("test");
						}
						if(value > alpha) {
							alpha = value;
							bestAction = possibleActions.get(i);
						}
					}else if ((colorPlayer == 1)){
						if(value <= alpha) {
							value = alpha;
							System.out.println("test");
						}
						if(value < beta) {
							beta = value;
							bestAction = possibleActions.get(i);
						}
					}
				}
			}
		}
		return bestAction;
	}
	
	public int alphaBetaMax(State board,int profundidad, Position pos, int alpha, int beta) {
		if(profundidad == 0) {
			return (m_eval.evaluatePosition(board,pos));
		}
		int maxValue = Integer.MIN_VALUE;
		for(Position position : board.getPositionPieceColor(0)) {
			Piece piece = board.createPiece(position.row, position.col);
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if (possibleActions.size() != 0){
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					int value = alphaBetaMin(actual,profundidad-1,possibleActions.get(i).m_finalPos,alpha,beta);
					if(value >= beta) {
						return beta;
					}
					if(value > alpha) {
						alpha = value;
					}
				}
			}
		}
		return alpha;
	}
	
	public int alphaBetaMin(State board,int profundidad, Position pos, int alpha, int beta) {
		if(profundidad == 0) {
			return (m_eval.evaluatePosition(board,pos));
		}
		int minValue = Integer.MAX_VALUE;
		for(Position position : board.getPositionPieceColor(1)) {
			Piece piece = board.createPiece(position.row, position.col);
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if (possibleActions.size() != 0){
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					int value = alphaBetaMax(actual,profundidad-1,possibleActions.get(i).m_finalPos,alpha,beta);
					if(value <= alpha) {
						return alpha;
					}
					if(value < beta) {
						beta = value;
					}
				}
			}
		}
		return beta;
	}
	
	public int minOrMax(State board,int colorPlayer, Position pos, int alpha, int beta) {
		int prof = m_profundidad; 
		int val;
		if(colorPlayer == 0) {
			val = alphaBetaMin(board,prof-1,pos,alpha,beta);
		}else {
			val = alphaBetaMax(board,prof-1,pos,alpha,beta);
		}
		return val;
	}
}
