package packages;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Chess {
	
	State m_state;
	String m_method;
	String m_color;
	Player p1=null,p2=null;
	int m_profund,m_maxjugadas;
	Evaluate m_eval = new Evaluate();
	Random rd = new Random();
	
	State state = null;
	Action act = null;
	Piece piece = null;
	Position positionSelected = null,positionSelected2 = null;
	int row,column;
	Boolean positionExist=true,moveExist = true;
	
	Chess(String metodo,Boolean inicio,int profundidad,String color,int maxJugadas){
		m_method=metodo;
		if(inicio) {
			m_state = Utils.getChessInstance();
		}else {
			m_state = Utils.getChessInstancePosition(Math.random(),rd.nextInt(Integer.MAX_VALUE)); //Choose a random chessBoard
		}
		m_profund=profundidad;
		m_color=color;
		if(maxJugadas==-1) {
			m_maxjugadas=Integer.MAX_VALUE;
		}else {
			m_maxjugadas=maxJugadas;
		}
		definePlayer();
	}
	
	Chess(String metodo,Boolean inicio,int profundidad,String color,int maxJugadas,double probabilidad,int semilla){
		m_method=metodo;
		if(inicio) {
			m_state = Utils.getChessInstance();
		}else {
			m_state = Utils.getChessInstancePosition(probabilidad, semilla);
		}
		m_profund=profundidad;
		m_color=color;
		if(maxJugadas==-1) {
			m_maxjugadas=Integer.MAX_VALUE;
		}else {
			m_maxjugadas=maxJugadas;
		}
		definePlayer();
	}
	
	public void execute() {
		for(int i = 0;i<m_maxjugadas;i++) {
			if(m_state.isNoKing(0)) {
				System.out.println("Black win");
				break;
			}
			System.out.println("\n||White - Turn " + i +"||" );
			playTurn(p1);
			if(m_state.isNoKing(1)) {
				System.out.println("White win");
				break;
			}
			System.out.println("\n||Black - Turn " + i +"||");
			playTurn(p2);
		}
	}
	
	public void iaRound(int color) {
		if(m_method.equals("minimax")) {
			MinMax minmax = new MinMax(m_profund,m_eval);
			act = minmax.searchAction(m_state, color);
		}else if(m_method.equals("alfabeta")) {
			AlfaBeta alfabeta = new AlfaBeta(m_profund,m_eval);
			act = alfabeta.searchAction(m_state, color);
		}
		state = m_state.applyAction(act,m_state.createPiece(act.m_initPos.row, act.m_initPos.col));
		Utils.printBoard(state);
		m_state = state;
	}
	
	public void chooseMove(int color) {
		while(positionExist) {
			System.out.println("- Choose the piece that you want to move -");
			Scanner scanner = new Scanner( System.in );
	        System.out.print( "> Please choose a row <\n");
	        row = scanner.nextInt();    
	        System.out.print( "> Please choose a column <\n");
	        column = scanner.nextInt(); 
	        positionSelected = new Position(row,column);
	        piece = m_state.createPiece(row, column);
			ArrayList<Position> pos = m_state.getPositionPieceColor(color);
			if(m_state.positionForColor(pos, positionSelected) && (piece.getPossibleActions(m_state,positionSelected).size()!=0)) {
				positionExist =  false;
			}
			else {
				System.out.println("!! Wrong Selection !!\n");
			}
		}
		while(moveExist) {
			System.out.println("- Choose a possible move for this piece -");
			Scanner scanner = new Scanner( System.in );
	        System.out.print( "> Please choose a row <\n");
	        row = scanner.nextInt();    
	        System.out.print( "> Please choose a column <\n");
	        column = scanner.nextInt(); 
	        positionSelected2 = new Position(row,column);
			ArrayList<Action> actions = piece.getPossibleActions(m_state,positionSelected);
			act = new Action(positionSelected,positionSelected2);
			if(m_state.moveForColor(actions, positionSelected2)) {
				moveExist =  false;
			}
			else {
				System.out.println("!! Impossible Move !!\\n");
			}
		}
		state = m_state.applyAction(act, piece);
		Utils.printBoard(state);
		m_state = state;
		positionExist =  true;
		moveExist =  true;
	}
	
	public void playTurn(Player player) {
		switch(player.m_type) {
		case Utils.playerReal :
			chooseMove(player.m_color);
			break;
		case Utils.playerIA :
			iaRound(player.m_color);
			break;
		case Utils.playerDummy :
			break;
		default:
			break;
		}
	}
	
	public void definePlayer() {
		switch(m_color) {
		case "white":
			p1 = new Player(Utils.playerIA,0);
			p2 = new Player(Utils.playerReal,1);
			break;
		case "black":
			p1 = new Player(Utils.playerReal,0);
			p2 = new Player(Utils.playerIA,1);
			break;
		case "todo":
			p1 = new Player(Utils.playerIA,0);
			p2 = new Player(Utils.playerIA,1);
			break;
		case "dummy":
			p1 = new Player(Utils.playerReal,0);
			p2 = new Player(Utils.playerDummy,1);
			break;
		default:
			break;
		}
	}
	
	

	public static void main(String[] args) {
		Chess chess = new Chess("alfabeta",true,6,"black",-1);
		//Chess chess = new Chess("alfabeta",false,6,"black",25);
		System.out.println("|||Chess Board|||");
		Utils.printBoard(chess.m_state);
		chess.execute();
	}	
}
