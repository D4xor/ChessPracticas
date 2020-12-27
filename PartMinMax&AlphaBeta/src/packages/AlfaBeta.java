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
		double alpha = Integer.MIN_VALUE;
		double beta = Integer.MAX_VALUE;
		double value;
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
						}
						if(value > alpha) {
							alpha = value;
							bestAction = possibleActions.get(i);
						}
					}else if ((colorPlayer == 1)){
						if(value <= alpha) {
							value = alpha;
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
	
	public double alphaBetaMax(State board,int profundidad, Position pos, double alpha, double beta) {
		if(profundidad == 0) {
			return (m_eval.evaluatePosition2(board,0));
		}
		for(Position position : board.getPositionPieceColor(0)) {
			Piece piece = board.createPiece(position.row, position.col);
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if (possibleActions.size() != 0){
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					double value = alphaBetaMin(actual,profundidad-1,possibleActions.get(i).m_finalPos,alpha,beta);
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
	
	public double alphaBetaMin(State board,int profundidad, Position pos, double alpha, double beta) {
		if(profundidad == 0) {
			return (m_eval.evaluatePosition2(board,0));
		}
		for(Position position : board.getPositionPieceColor(1)) {
			Piece piece = board.createPiece(position.row, position.col);
			ArrayList<Action> possibleActions = piece.getPossibleActions(board,position);
			if (possibleActions.size() != 0){
				for(int i=0;i<possibleActions.size();i++) {
					actual = board.applyAction(possibleActions.get(i), piece);
					double value = alphaBetaMax(actual,profundidad-1,possibleActions.get(i).m_finalPos,alpha,beta);
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
	
	public double minOrMax(State board,int colorPlayer, Position pos, double alpha, double beta) {
		int prof = m_profundidad; 
		double val;
		if(colorPlayer == 0) {
			val = alphaBetaMin(board,prof-1,pos,alpha,beta);
		}else {
			val = alphaBetaMax(board,prof-1,pos,alpha,beta);
		}
		return val;
	}
}
