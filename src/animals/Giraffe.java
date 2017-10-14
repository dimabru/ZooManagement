package animals;

import diet.Herbivore;
import diet.Omnivore;
import graphics.ZooPanel;
import mobility.Point;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Giraffe Class
 */
public class Giraffe extends Animal {

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
	public Giraffe(int s, int x, int y, int h, int v, String c, ZooPanel p) {
		super("Giraffe", s * 4 / 3, s * 2, h, v, c, p);
		setLocation(new Point(0, 0));
		setDiet(new Herbivore());
		loadImages("grf");
		cor_x1 = size / 4;
		cor_x2 = (-size / 4);
		cor_x3 = (int) (-size * 0.25);
		cor_x4 = (int) (size * 0.25);
		cor_y1 = (int) (-30 - size * 9 / 10);
		cor_y3 = size / 10;
		cor_x5 = -size / 2;
		cor_y5 = cor_y6 = -size / 10;
		cor_w = (int) (size * 0.7);
	}

	/**
	 * ctor
	 */
	public Giraffe() {
		setDiet(new Herbivore());
		name = new String("Giraffe");
	}

	/**
	 * set size
	 */
	@Override
	public void setSize(int sz) {
		size = (int) (sz * 4 / 3);
		cor_x1 = size / 4;
		cor_x2 = (-size / 4);
		cor_x3 = (int) (-size * 0.25);
		cor_x4 = (int) (size * 0.25);
		cor_y1 = (int) (-30 - size * 9 / 10);
		cor_y3 = size / 10;
		cor_x5 = -size / 2;
		cor_y5 = cor_y6 = -size / 10;
		cor_w = (int) (size * 0.7);
	}

	/**
	 * set weight
	 */
	@Override
	public void setWeight(int w) {
		weight = w * 2;
	}

	/**
	 * set color
	 */
	@Override
	public void setColor(String c) {
		col = c;
		loadImages("grf");
	}
}
