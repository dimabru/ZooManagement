package mobility;

/**
 * @author baroh
 *
 */
public class Point {

	private int x; // the x value
	private int y; // the y value
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * cctor
	 * @param p point to set
	 */
	public Point(Point p){
		x=p.x;
		y=p.y;
	}
	
	/**
	 * 
	 * @return x value
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @return y value
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param x to set
	 * @return true
	 */
	public boolean setX(int x) {
		this.x = x;
		return true;
	}
	/**
	 * 
	 * @param y to set
	 * @return true
	 */
	public boolean setY(int y) {
		this.y = y;
		return true;
	}
}
