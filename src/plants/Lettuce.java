package plants;

import graphics.ZooPanel;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Lettuce Class
 */
public class Lettuce extends Plant {
	
	private static Lettuce lettuce;
	
	/**
	 * private ctor
	 * @param pan zoo panel
	 */
	private Lettuce(ZooPanel pan) {super(pan); }
	
	/**
	 * get instance of lettuce
	 * @param pan zoo panel
	 * @return current instance
	 */
	public static Lettuce getIntsance(ZooPanel pan){
		if (lettuce==null){
			lettuce = new Lettuce(pan);
		}
		lettuce.loadImages("lettuce");
		return lettuce;
	}
}
