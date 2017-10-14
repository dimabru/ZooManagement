package graphics;

import java.awt.Graphics;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * IDrawable Interface
 */
public interface IDrawable {
	 public final static String PICTURE_PATH = "src/pictures/";
	 public void loadImages(String nm);
	 public void drawObject(Graphics g);
	 public String getColor();	 
}

