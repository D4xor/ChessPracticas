package packages;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Chess {
	static final int verif = 15;

	State m_state;
	String m_method;
	String m_color;
	Player p1=null,p2=null;
	int m_profund,m_maxjugadas,m_actverif;
	Evaluate m_eval = new Evaluate();
	Random rd = new Random();
	ArrayList<Action> wAction = new ArrayList<Action>();
	ArrayList<Action> bAction = new ArrayList<Action>();
	
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
		m_actverif=verif;
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
		m_actverif=verif;
		definePlayer();
	}
	
	public void execute() {
		for(int i = 0;i<m_maxjugadas;i++) { //Represent number of moves possible
			if(m_state.isNoKing(0)) { //check if black wins
				System.out.println("Black win");
				break;
			}
			if(checkSameAction(m_actverif,bAction)){
				System.out.println("3 times, a similar action");
				break;
			}
			System.out.println("\n||White - Turn " + i +"||" );
			playTurn(p1); //White plays
			if(m_state.isNoKing(1)) { // Check if white wins
				System.out.println("White win");
				break;
			}
			if(checkSameAction(m_actverif,wAction)){
				System.out.println("3 times, a similar action");
				break;
			}
			System.out.println("\n||Black - Turn " + i +"||");
			playTurn(p2); //Black plays
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
		if(color==0){ wAction.add(act);}
		else if(color ==1){ bAction.add(act);}
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
		if(color==0){ wAction.add(act);}
		else if(color ==1){ bAction.add(act);}
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

	public Boolean checkSameAction(int sizeVerif, ArrayList<Action> acts){
		if(sizeVerif > acts.size()){ return false; }
		for(int i = acts.size()-sizeVerif;i<acts.size();i++){
			int cnt=0;
			for(int j = acts.size()-sizeVerif;j<acts.size();j++){
				if((i != j) && (acts.get(i).m_initPos.equals(acts.get(j).m_initPos)) && (acts.get(i).m_finalPos.equals(acts.get(j).m_finalPos))){
					cnt+=1;
				}
			}
			if(cnt>=3){ return true;}
		}
		return false;
	}

	public static void displayTime(long t1, long t2) {
		long res = (int) ((t2-t1)/1000);
		int hours = (int)(res / 3600);
		int minutes = (int)((res - 3600*hours)/60);
		int seconds = (int) (res - hours * 3600 - minutes*60);
		int milli = (int) ((t2-t1) - hours*3600*1000 - minutes*60*1000 - seconds*1000);
		System.out.println("Time of execution - " + hours + "h:" + minutes + "min:" + seconds + "sec:" + milli + "ms" );
	}

	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		Chess chess = new Chess("alfabeta",true,4,"todo",-1,0.95,84);
		//Chess chess = new Chess("alfabeta",false,6,"black",25);
		System.out.println("|||Chess Board|||");
		Utils.printBoard(chess.m_state);
		Evaluate ev = new Evaluate();
		chess.execute();
		long time2 = System.currentTimeMillis();
		displayTime(time1,time2); //Calculate time to execute
	}	
}
