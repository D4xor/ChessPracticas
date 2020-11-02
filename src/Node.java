import java.util.ArrayList;

public class Node implements Comparable<Node>{

	ArrayList<Action> m_road = null;
	Position m_pos = null;
	double m_cost = 0;
	int m_node=0;
	State m_state = null;
	
	Node(Position Pos, ArrayList<Action> road, double cost, int nodeValue,State state){
		m_pos=Pos;
		m_road=road;
		m_cost=cost;
		m_node=nodeValue;
		m_state=state;
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

	@Override
	public int compareTo(Node node) {
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
	
	
}
