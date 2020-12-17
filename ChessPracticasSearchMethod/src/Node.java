import java.util.ArrayList;

public class Node implements Comparable<Node>{

	ArrayList<Action> m_road;
	Position m_pos;
	double m_cost;
	int m_node, m_heuristic;
	State m_state;
	String m_type = "";
	
	Node(Position Pos, ArrayList<Action> road, double cost, int nodeValue,State state){
		m_pos=Pos;
		m_road=road;
		m_cost=cost;
		m_node=nodeValue;
		m_state=state;
		m_heuristic=-1;
	}
	
	Node(Position Pos, ArrayList<Action> road, double cost,int heuristic,String type, int nodeValue,State state){
		m_pos=Pos;
		m_road=road;
		m_cost=cost;
		m_node=nodeValue;
		m_state=state;
		m_heuristic=heuristic;
		m_type=type;
	}

	public ArrayList<Action> getM_road() {
		return m_road;
	}

	public void setM_road(ArrayList<Action> m_road) {
		this.m_road = m_road;
	}

	public Position getM_pos() {
		return m_pos;
	}

	public void setM_pos(Position m_pos) {
		this.m_pos = m_pos;
	}

	public double getM_cost() {
		return m_cost;
	}

	public void setM_cost(double m_cost) {
		this.m_cost = m_cost;
	}

	public int getM_node() {
		return m_node;
	}

	public void setM_node(int m_node) {
		this.m_node = m_node;
	}

	public State getM_state() {
		return m_state;
	}

	public void setM_state(State m_state) {
		this.m_state = m_state;
	}
	

	public int getM_heuristic() {
		return m_heuristic;
	}

	public void setM_heuristic(int m_heuristic) {
		this.m_heuristic = m_heuristic;
	}

	@Override
	public int compareTo(Node node) {
		if(m_heuristic==-1) {
			if(this.getM_cost() > node.getM_cost()) {
	            return 1;
	        } else if (this.getM_cost() < node.getM_cost()) {
	            return -1;
	        } else {
	        	if(this.getM_node()<node.getM_node()) {
	        		return -1;
	        	}else if(this.getM_node()>node.getM_node()) {
	        		return 1;
	        	}
	            return 0;
	        }
		}
		else if(m_heuristic != -1 && m_type.equals("best-first")) {
			if(this.getM_heuristic() > node.getM_heuristic()) {
	            return 1;
	        } else if (this.getM_heuristic() < node.getM_heuristic()) {
	            return -1;
	        } else {
	        	if(this.getM_node()<node.getM_node()) {
	        		return -1;
	        	}else if(this.getM_node()>node.getM_node()) {
	        		return 1;
	        	}
	            return 0;
	        }
		}
		else if(m_heuristic != -1 && m_type.equals("a-star")) {
			if((this.getM_cost()+this.getM_heuristic()) > (node.getM_cost()+node.getM_heuristic())) {
	            return 1;
	        } else if ((this.getM_cost()+this.getM_heuristic()) < (node.getM_cost()+node.getM_heuristic())) {
	            return -1;
	        } else {
	        	if(this.getM_node()<node.getM_node()) {
	        		return -1;
	        	}else if(this.getM_node()>node.getM_node()) {
	        		return 1;
	        	}
	            return 0;
	        }			
		}
		else {
			return 0;
		}
	}
	
	
}
