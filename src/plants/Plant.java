package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Plant Class
 */
public abstract class Plant implements IEdible, ILocatable, IDrawable {

	private Point location;
	protected ZooPanel pan;
	protected BufferedImage img;
	
	/**
	 * ctor
	 * @param p zoo panel
	 */
	public Plant(ZooPanel p) {
		pan = p;
		this.location = new Point(pan.getWidth()/2,pan.getHeight()/2);
	}

	/**
	 * loads images of plants from pictures folder 
	 */
	public void loadImages(String nm){
			try { 
				img = ImageIO.read(new File(PICTURE_PATH + nm + ".png"));
			}
			catch (IOException e) { System.out.println("Cannot load picture"); }
	}

	/**
	 * draw the plant
	 */
	public void drawObject(Graphics g) {
		g.drawImage(img, location.getX()-20, location.getY()-20, 40, 40, pan);
	}
	
	/**
	 * getters and setters
	 */
	public EFoodType getFoodtype() { return EFoodType.VEGETABLE; }
	public String getColor() { return "Green"; }	 
	public Point getLocation() { return null; }
	public boolean setLocation(Point location) { return false; }


}
