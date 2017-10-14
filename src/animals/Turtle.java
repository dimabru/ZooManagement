package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Turtle Class
 */
public class Turtle extends Animal {

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
	public Turtle(int s, int x, int y, int h, int v, String c, ZooPanel p) {
		super("Turtle", s / 2, s / 2, h, v, c, p);
		setLocation(new Point(x, y));
		setDiet(new Herbivore());
		loadImages("trt");
		cor_x1 = size / 4;
		cor_x2 = (int) (-size / 4);
		cor_x3 = (int) (-size * 0.25);
		cor_x4 = (int) (size * 0.25);
		cor_y1 = (int) (-30 - size * 0.125);
		cor_y3 = size / 8;
		cor_x5 = -size;
		cor_y5 = cor_y6 = -size / 4;
		cor_h = (int) (size * 0.68);
	}

	/**
	 * ctor
	 */
	public Turtle() {
		setDiet(new Herbivore());
		name = new String("Turtle");
	}

	/**
	 * set size
	 */
	@Override
	public void setSize(int sz) {
		size = (int) (sz / 2);
		cor_x1 = size / 4;
		cor_x2 = (int) (-size / 4);
		cor_x3 = (int) (-size * 0.25);
		cor_x4 = (int) (size * 0.25);
		cor_y1 = (int) (-30 - size * 0.125);
		cor_y3 = size / 8;
		cor_x5 = -size;
		cor_y5 = cor_y6 = -size / 4;
		cor_h = (int) (size * 0.68);
	}

	/**
	 * set weight
	 */
	@Override
	public void setWeight(int w) {
		weight = w / 2;
	}

	/**
	 * set color
	 */
	@Override
	public void setColor(String c) {
		col = c;
		loadImages("trt");
	}

}
