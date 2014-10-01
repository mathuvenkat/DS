package three.cross.chess.domain;


public class SquareLocation implements Cloneable{

	private static final String COMMA_DELIMITTER = " , ";
	private int x;
	private int y;


	public SquareLocation(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}

	//Might be needed for GUI implementation 
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString(){
		return String.valueOf(x) + COMMA_DELIMITTER + String.valueOf(y);
	}

	@Override
	public boolean equals(Object obj) {
		SquareLocation loc = (SquareLocation) obj;
		if (this.getX() == loc.getX() && this.getY() == loc.getY()){
			return true;
		}
		return false;
	}

}
