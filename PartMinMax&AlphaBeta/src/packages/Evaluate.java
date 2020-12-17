package packages;

public class Evaluate {

	static final int[][] pawnBoard = {{0,  0,  0,  0,  0,  0,  0,  0},
								  	  {50, 50, 50, 50, 50, 50, 50, 50},
								      {10, 10, 20, 30, 30, 20, 10, 10},
								      {5,  5, 10, 25, 25, 10,  5,  5},
								      {0,  0,  0, 20, 20,  0,  0,  0},
								      {5, -5,-10,  0,  0,-10, -5,  5},
								      {5, 10, 10,-20,-20, 10, 10,  5},
								      {0,  0,  0,  0,  0,  0,  0,  0}};
	
	static final int[][] rookBoard = {{0,  0,  0,  0,  0,  0,  0,  0},
									  {5, 10, 10, 10, 10, 10, 10,  5},
									  {-5,  0,  0,  0,  0,  0,  0, -5},
									  {-5,  0,  0,  0,  0,  0,  0, -5},
									  {-5,  0,  0,  0,  0,  0,  0, -5},
									  {-5,  0,  0,  0,  0,  0,  0, -5},
									  {-5,  0,  0,  0,  0,  0,  0, -5},
									  {0,  0,  0,  5,  5,  0,  0,  0}};
	
	static final int[][] bishopBoard = {{-20,-10,-10,-10,-10,-10,-10,-20},
									   {-10,  0,  0,  0,  0,  0,  0,-10},
									   {-10,  0,  5, 10, 10,  5,  0,-10},
									   {-10,  5,  5, 10, 10,  5,  5,-10},
									   {-10,  0, 10, 10, 10, 10,  0,-10},
									   {-10, 10, 10, 10, 10, 10, 10,-10},
									   {-10,  5,  0,  0,  0,  0,  5,-10},
									   {-20,-10,-10,-10,-10,-10,-10,-20}};
			
	static final int[][] knightBoard = {{-50,-40,-30,-30,-30,-30,-40,-50},
										{-40,-20,  0,  0,  0,  0,-20,-40},
										{-30,  0, 10, 15, 15, 10,  0,-30},
										{-30,  5, 15, 20, 20, 15,  5,-30},
										{-30,  0, 15, 20, 20, 15,  0,-30},
										{-30,  5, 10, 15, 15, 10,  5,-30},
										{-40,-20,  0,  5,  5,  0,-20,-40},
										{-50,-40,-30,-30,-30,-30,-40,-50}};
	
	static final int[][] queenBoard = {{-20,-10,-10, -5, -5,-10,-10,-20},
									   {-10,  0,  0,  0,  0,  0,  0,-10},
									   {-10,  0,  5,  5,  5,  5,  0,-10},
									   {-5,  0,  5,  5,  5,  5,  0, -5},
									   {0,  0,  5,  5,  5,  5,  0, -5},
									   {-10,  5,  5,  5,  5,  5,  0,-10},
									   {-10,  0,  5,  0,  0,  0,  0,-10},
									   {-20,-10,-10, -5, -5,-10,-10,-20}};
	
	static final int[][] kingBoard = {{-30,-40,-40,-50,-50,-40,-40,-30},
									  {-30,-40,-40,-50,-50,-40,-40,-30},
									  {-30,-40,-40,-50,-50,-40,-40,-30},
									  {-30,-40,-40,-50,-50,-40,-40,-30},
									  {-20,-30,-30,-40,-40,-30,-30,-20},
									  {-10,-20,-20,-20,-20,-20,-20,-10},
									  {20, 20,  0,  0,  0,  0, 20, 20},
									  {20, 30, 10,  0,  0, 10, 30, 20}};
	
	static final int[][] kingBoardEnd = {{-50,-40,-30,-20,-20,-30,-40,-50},
										 {-30,-20,-10,  0,  0,-10,-20,-30},
										 {-30,-10, 20, 30, 30, 20,-10,-30},
										 {-30,-10, 30, 40, 40, 30,-10,-30},
										 {-30,-10, 30, 40, 40, 30,-10,-30},
										 {-30,-10, 20, 30, 30, 20,-10,-30},
										 {-30,-30,  0,  0,  0,  0,-30,-30},
										 {-50,-30,-30,-30,-30,-30,-30,-50}};
	
	static final int pawnValue = 100;
	static final int rookValue = 500;
	static final int bishopValue = 330;
	static final int knightValue = 320;
	static final int queenValue = 900;
	static final int kingValue = 20000;
	
	Evaluate(){
		
	}
	
	public int evaluatePosition(State board,Position pos) {
		int count = 0;
		int piece = board.m_board[pos.row][pos.col];
		switch(piece) {
		case Utils.wPawn:
			count = pawnValue*pawnBoard[pos.row][pos.col];
			break;
		case Utils.wRook:
			count = rookValue*rookBoard[pos.row][pos.col];
			break;
		case Utils.wBishop:
			count = bishopValue*bishopBoard[pos.row][pos.col];
			break;
		case Utils.wKnight:
			count = knightValue*knightBoard[pos.row][pos.col];
			break;
		case Utils.wQueen:
			count = queenValue*queenBoard[pos.row][pos.col];
			break;
		case Utils.wKing:
			count = kingValue*kingBoard[pos.row][pos.col];
			break;	
		case Utils.bPawn:
			count = pawnValue*pawnBoard[board.m_boardSize-1-pos.row][pos.col]*-1;
			break;
		case Utils.bRook:
			count = rookValue*rookBoard[board.m_boardSize-1-pos.row][pos.col]*-1;
			break;
		case Utils.bBishop:
			count = bishopValue*bishopBoard[board.m_boardSize-1-pos.row][pos.col]*-1;
			break;
		case Utils.bKnight:
			count = knightValue*knightBoard[board.m_boardSize-1-pos.row][pos.col]*-1;
			break;
		case Utils.bQueen:
			count = queenValue*queenBoard[board.m_boardSize-1-pos.row][pos.col]*-1;
			break;
		case Utils.bKing:
			count = kingValue*kingBoard[board.m_boardSize-1-pos.row][pos.col]*-1;
			break;
		default:
			break;
		}
		
		/*
		for(int i=0;i<board.m_boardSize;i++) {
			for(int j=0;j<board.m_boardSize;j++) {
				int piece = board.m_board[i][j];
				switch(piece) {
				case Utils.wPawn:
					count += pawnValue*pawnBoard[i][j];
					break;
				case Utils.wRook:
					count += rookValue*rookBoard[i][j];
					break;
				case Utils.wBishop:
					count += bishopValue*bishopBoard[i][j];
					break;
				case Utils.wKnight:
					count += knightValue*knightBoard[i][j];
					break;
				case Utils.wQueen:
					count += queenValue*queenBoard[i][j];
					break;
				case Utils.wKing:
					count += kingValue*kingBoard[i][j];
					break;	
				case Utils.bPawn:
					count += pawnValue*pawnBoard[board.m_boardSize-1-i][j]*-1;
					break;
				case Utils.bRook:
					count += rookValue*rookBoard[board.m_boardSize-1-i][j]*-1;
					break;
				case Utils.bBishop:
					count += bishopValue*bishopBoard[board.m_boardSize-1-i][j]*-1;
					break;
				case Utils.bKnight:
					count += knightValue*knightBoard[board.m_boardSize-1-i][j]*-1;
					break;
				case Utils.bQueen:
					count += queenValue*queenBoard[board.m_boardSize-1-i][j]*-1;
					break;
				case Utils.bKing:
					count += kingValue*kingBoard[board.m_boardSize-1-i][j]*-1;
					break;
				default:
					break;
				}
			}
		}*/
		return count;
	}
	
	
}
