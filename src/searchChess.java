import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;


public class searchChess {

	// member variables
    State m_initialState = null;
	ArrayList<Action> m_finalSolution = null;
	Piece m_piece = null;
	String m_method = "";
	State m_finalState = null;
	double m_cost=0.0;
	int m_explore=0;
	int m_generate=0;
	
	searchChess(String method,int size, double density, int agent, int seed){
		m_initialState=Utils.getProblemInstance(size, density, seed, agent);
		switch(agent){
		case Utils.wRook: m_piece = new Rook(0); 
 			break;
		case Utils.bRook: m_piece = new Rook(1);
			break;
		case Utils.wPawn: m_piece = new Pawn(0);
		  	break;
		case Utils.bPawn: m_piece = new Pawn(1);
			break;
		case Utils.wBishop: m_piece = new Bishop(0);
			break;	
		case Utils.bBishop: m_piece = new Bishop(1);
			break;
		case Utils.wKnight: m_piece = new Knight(0);
			break;	
		case Utils.bKnight: m_piece = new Knight(1);
			break;	
		case Utils.wQueen: m_piece = new Queen(0);
			break;	
		case Utils.bQueen: m_piece = new Queen(1);
			break;	
		case Utils.wKing: m_piece = new King(0);
			break;	
		case Utils.bKing: m_piece = new King(1);
			break;	
		default: break;
		}
		m_method=method;
	}
	
	public void breadthFirst() {
		ArrayList<Action> m_solution = new ArrayList<Action>();
		ArrayList<Position> visited = new ArrayList<Position>();
		Queue<Node> open = new LinkedList<>();
		Boolean solutionFound = false;
		State actual=null;
		Node node = null;
		double cost=0.0;
		int value=1;
		
		open.add(new Node(m_initialState.m_agentPos,m_solution,cost,value,m_initialState));
		while(!solutionFound && !open.isEmpty()) {
			node = open.remove();
			
			System.out.println("");
			System.out.println("Value : "+node.getM_node());
			System.out.println("Cost : "+node.getM_cost());
			System.out.println("Pos row : "+node.getM_pos().row + " | "+ "Pos col : "+node.getM_pos().col);		
			System.out.println("------------------------");
			
			ArrayList<Action> possibleActions = m_piece.getPossibleActions(node.getM_state());
			if (node.getM_state().isFinal()) { // first we check if the state is final
				solutionFound = true;
				m_finalState = node.getM_state();
				m_cost=node.getM_cost();
				m_finalSolution=node.getM_road();
			}
			else if(!isVisited(visited,node.getM_pos())){
				visited.add(node.getM_pos());
				m_explore +=1;
				if (possibleActions.size() != 0){
					for(int i=0;i<possibleActions.size();i++) {
						ArrayList<Action> newList = new ArrayList<Action>();
						for(Action act : node.getM_road()) {
							newList.add(act);
						}
						cost=node.getM_cost();
						actual=node.getM_state();
						value+=1;
						newList.add(possibleActions.get(i));
						cost += possibleActions.get(i).getCost();
						actual = actual.applyAction(possibleActions.get(i));
						open.add(new Node(actual.m_agentPos,newList,cost,value,actual));
						System.out.println("Pos row : "+actual.m_agentPos.row + " | "+ "Pos col : "+actual.m_agentPos.col);		
					}
				}
			}
		}
		m_generate=value;
	} 
	
	public void depthFirst() {
		Stack<Node> stk = new Stack<Node>();  
		ArrayList<Action> m_solution = new ArrayList<Action>();
		ArrayList<Position> visited = new ArrayList<Position>();
		Boolean solutionFound = false;
		State actual=null;
		Node node = null;
		double cost=0.0;
		int value=1;
		stk.push(new Node(m_initialState.m_agentPos,m_solution,cost,value,m_initialState));
		while(!solutionFound && !stk.isEmpty()) {
			node = stk.pop();
			
			System.out.println("");
			System.out.println("Value : "+node.getM_node());
			System.out.println("Cost : "+node.getM_cost());
			System.out.println("Pos row : "+node.getM_pos().row + " | "+ "Pos col : "+node.getM_pos().col);		
			System.out.println("------------------------");
			
			ArrayList<Action> possibleActions = m_piece.getPossibleActions(node.getM_state());
			if (node.getM_state().isFinal()) { // first we check if the state is final
				solutionFound = true;
				m_finalState = node.getM_state();
				m_cost=node.getM_cost();
				m_finalSolution=node.getM_road();
			}
			else if(!isVisited(visited,node.getM_pos())){
				visited.add(node.getM_pos());
				m_explore +=1;
				if (possibleActions.size() != 0){
					for(int i=possibleActions.size()-1;i>=0;i--) {
						ArrayList<Action> newList = new ArrayList<Action>();
						for(Action act : node.getM_road()) {
							newList.add(act);
						}
						cost=node.getM_cost();
						actual=node.getM_state();
						value+=1;
						newList.add(possibleActions.get(i));
						cost += possibleActions.get(i).getCost();
						actual = actual.applyAction(possibleActions.get(i));
						stk.push(new Node(actual.m_agentPos,newList,cost,value,actual));
						System.out.println("Pos row : "+actual.m_agentPos.row + " | "+ "Pos col : "+actual.m_agentPos.col);		
					}
				}
			}
		}
		m_generate=value;	
	}
	
	public void uniformCost() {
		PriorityQueue<Node> nodePriority = new PriorityQueue<Node>();
		ArrayList<Action> m_solution = new ArrayList<Action>();
		ArrayList<Position> visited = new ArrayList<Position>();
		Boolean solutionFound = false;
		State actual=null;
		Node node = null;
		double cost=0.0;
		int value=1;
		nodePriority.add(new Node(m_initialState.m_agentPos,m_solution,cost,value,m_initialState));
		while(!solutionFound && !nodePriority.isEmpty()) {
			node = nodePriority.remove();
			
			System.out.println("");
			System.out.println("Value : "+node.getM_node());
			System.out.println("Cost : "+node.getM_cost());
			System.out.println("Pos row : "+node.getM_pos().row + " | "+ "Pos col : "+node.getM_pos().col);		
			System.out.println("------------------------");
			
			ArrayList<Action> possibleActions = m_piece.getPossibleActions(node.getM_state());
			if (node.getM_state().isFinal()) { // first we check if the state is final
				solutionFound = true;
				m_finalState = node.getM_state();
				m_cost=node.getM_cost();
				m_finalSolution=node.getM_road();
			}
			else if(!isVisited(visited,node.getM_pos())){
				visited.add(node.getM_pos());
				m_explore +=1;
				if (possibleActions.size() != 0){
					for(int i=possibleActions.size()-1;i>=0;i--) {
						ArrayList<Action> newList = new ArrayList<Action>();
						for(Action act : node.getM_road()) {
							newList.add(act);
						}
						cost=node.getM_cost();
						actual=node.getM_state();
						value+=1;
						newList.add(possibleActions.get(i));
						cost += possibleActions.get(i).getCost();
						actual = actual.applyAction(possibleActions.get(i));
						nodePriority.add(new Node(actual.m_agentPos,newList,cost,value,actual));
						System.out.println("Pos row : "+actual.m_agentPos.row + " | "+ "Pos col : "+actual.m_agentPos.col);		
					}
				}
			}
		}
		m_generate=value;
	}
	
	public void depthLimited() {
		int limit = 4;
		Stack<Node> stk = new Stack<Node>();  
		ArrayList<Action> m_solution = new ArrayList<Action>();
		ArrayList<Position> visited = new ArrayList<Position>();
		Boolean solutionFound = false;
		State actual=null;
		Node node = null;
		double cost=0.0;
		int value=1;
		stk.push(new Node(m_initialState.m_agentPos,m_solution,cost,value,m_initialState));
		while(!solutionFound && !stk.isEmpty()) {
			node = stk.pop();
			
			System.out.println("");
			System.out.println("Value : "+node.getM_node());
			System.out.println("Cost : "+node.getM_cost());
			System.out.println("Pos row : "+node.getM_pos().row + " | "+ "Pos col : "+node.getM_pos().col);		
			System.out.println("------------------------");
			
			ArrayList<Action> possibleActions = m_piece.getPossibleActions(node.getM_state());
			if (node.getM_state().isFinal()) { // first we check if the state is final
				solutionFound = true;
				m_finalState = node.getM_state();
				m_cost=node.getM_cost();
				m_finalSolution=node.getM_road();
			}
			else if(!isVisited(visited,node.getM_pos())){
				visited.add(node.getM_pos());
				m_explore +=1;
				if ((possibleActions.size() != 0) && (node.getM_road().size()<limit)){
					for(int i=possibleActions.size()-1;i>=0;i--) {
						ArrayList<Action> newList = new ArrayList<Action>();
						for(Action act : node.getM_road()) {
							newList.add(act);
						}
						cost=node.getM_cost();
						actual=node.getM_state();
						value+=1;
						newList.add(possibleActions.get(i));
						cost += possibleActions.get(i).getCost();
						actual = actual.applyAction(possibleActions.get(i));
						stk.push(new Node(actual.m_agentPos,newList,cost,value,actual));
						System.out.println("Pos row : "+actual.m_agentPos.row + " | "+ "Pos col : "+actual.m_agentPos.col);		
					}
				}
			}
		}
		m_generate=value;
	}
	
	public void iterativeDeepening() {
		
	}
	
	public void bestFirst() {
		
	}
	
	public void aStar() {
		
	}

	public Boolean isVisited(ArrayList<Position> visited, Position arrayToTest) {
		for(Position pos : visited) {
			if(pos.equals(arrayToTest)) {
				return true;
			}
		}
		return false;
	}
	
	public void execute() {
		switch(m_method) {
		case "breadth-first":
			breadthFirst();
			break;
		case "depth-first":
			depthFirst();
			break;
		case "uniform-cost":
			uniformCost();
			break;	
		case "depth-limited":
			depthLimited();
			break;	
		case "iterative-deepening":
			iterativeDeepening();
			break;
		case "best-first":
			bestFirst();
			break;
		case "a-star":
			aStar();
			break;
		default:
			break;
		}
	}
	
	
public static void main(String[] args) {
		
		String method = "uniform-cost";
		int size = 8;
		double density = 1.0;
		int agent = 2;
		int seed1 = 1;
		
		searchChess srch = new searchChess(method,size,density,agent,seed1);
		Utils.printBoard(srch.m_initialState);
		srch.execute();
		
		System.out.println("Number of generated nodes: "+ srch.m_generate);
		System.out.println("Number of expanded nodes: "+ srch.m_explore);
		if (srch.m_finalState==null) {
			System.out.println("\nSorry, no solution found ....");
		}else {
			System.out.println("\nSolution length: " + srch.m_finalSolution.size());
			System.out.println("Solution cost:   " + srch.m_cost);
			
			System.out.println("\nSolution:");
			for(int i=0;i<srch.m_finalSolution.size();i++)
				System.out.println((i+1) + ": " + srch.m_finalSolution.get(i));
			System.out.println("");
			Utils.printBoard(srch.m_finalState);
		}
		System.out.println();
		

	}
	
}
