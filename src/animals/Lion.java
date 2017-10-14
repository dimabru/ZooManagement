package animals;

import graphics.ZooPanel;
import mobility.Point;
import diet.Carnivore;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Lion Class
 */
public class Lion extends Animal {

	/**
	 * ctor
	 * @param s size
	 * @param x location.x
	 * @param y location.y
	 * @param h horizontal speed
	 * @param v vertical speed
	 * @param c color 
 	 * @param p panel
	 */
	public Lion(int s, int x, int y, int h, int v, String c, ZooPanel p) {
		super("Lion", (int) (s * 0.745), (int) (s * 0.8), h, v, c, p);
		setLocation(new Point(x, y));
		setDiet(new Carnivore());
		loadImages("lio");
		cor_x4 = 0;
		cor_y1 = (int) (-30 - size / 3);
		cor_y3 = (int) (size * 0.25);
		cor_x5 = cor_x6 = -size / 2;
		cor_y5 = cor_y6 = -size / 3;
		cor_h = (int) (size * 0.73);
	}

	/**
	 * ctor
	 */
	public Lion() {
		setDiet(new Carnivore());
		name = new String("Lion");
	}

	/**
	 * set size
	 */
	@Override
	public void setSize(int sz) {
		size = (int) (sz * 0.745);
		cor_x4 = 0;
		cor_y1 = (int) (-30 - size / 3);
		cor_y3 = (int) (size * 0.25);
		cor_x5 = cor_x6 = -size / 2;
		cor_y5 = cor_y6 = -size / 3;
		cor_h = (int) (size * 0.73);
	}

	/**
	 * set weight
	 */
	@Override
	public void setWeight(int w) {
		weight = (int) (w * 0.8);
	}

	/**
	 * set color
	 */
	@Override
	public void setColor(String c) {
		col = c;
		loadImages("lio");
	}

}
