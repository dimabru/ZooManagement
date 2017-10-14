package animals;

import diet.Carnivore;
import diet.Omnivore;
import graphics.ZooPanel;
import mobility.Point;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Bear Class
 */
public class Bear extends Animal {

	/**
	 * Ctor
	 * @param s size
	 * @param x location.x
	 * @param y location.y
	 * @param h horizontal speed
	 * @param v vertical speed
	 * @param c color
	 * @param p panel
	 */
	public Bear(int s, int x, int y, int h, int v, String c, ZooPanel p) {
		super("Bear", (int) (s * 0.7), s, h, v, c, p);
		setLocation(new Point(x, y));
		setDiet(new Omnivore());
		loadImages("bea");
		cor_x3 = -size / 2;
		cor_x4 = 0;
		cor_y1 = (int) (-30 - size / 5);
		cor_y3 = (int) (size * 0.3);
		cor_x5 = -size * 6 / 7;
		cor_y5 = cor_y6 = -size / 3;
		cor_h = (int) (size * 2 / 3);
	}

	/**
	 * ctor
	 */
	public Bear() {
		setDiet(new Omnivore());
		name = new String("Bear");
	}

	/**
	 * set size
	 */
	@Override
	public void setSize(int sz) {
		size = (int) (sz * 0.7);
		cor_x3 = -size / 2;
		cor_x4 = 0;
		cor_y1 = (int) (-30 - size / 5);
		cor_y3 = (int) (size * 0.3);
		cor_x5 = -size * 6 / 7;
		cor_y5 = cor_y6 = -size / 3;
		cor_h = (int) (size * 2 / 3);
	}

	/**
	 * set weight
	 */
	@Override
	public void setWeight(int w) {
		weight = w;
	}

	/**
	 * set color
	 */
	@Override
	public void setColor(String c) {
		col = c;
		loadImages("bea");
	}
}
