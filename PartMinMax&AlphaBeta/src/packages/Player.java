package packages;

public class Player {

	int m_type, m_color;
	Player(int typePlayer,int color){
		m_type = typePlayer;
		m_color = color;
	}
	public int getM_type() {
		return m_type;
	}
	public void setM_type(int m_type) {
		this.m_type = m_type;
	}
	public int getM_color() {
		return m_color;
	}
	public void setM_color(int m_color) {
		this.m_color = m_color;
	}
}
