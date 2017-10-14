package plants;

import graphics.ZooPanel;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Cabbage Class
 */
public class Cabbage extends Plant {
	
	private static Cabbage cabbage;
	
	/**
	 * private ctor
	 * @param pan zoo panel
	 */
	private Cabbage(ZooPanel pan) {super(pan); }
	
	/**
	 * get instance of cabbage
	 * @param pan zoo panel
 	 * @return current instance
	 */
	public static Cabbage getIntsance(ZooPanel pan){
		if (cabbage==null){
			cabbage = new Cabbage(pan);
		}
		cabbage.loadImages("cabbage");
		return cabbage;
	}
}
