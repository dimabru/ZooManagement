package graphics;

import animals.Animal;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * ColoredAnimalDecorator Class
 */
public class ColoredAnimalDecorator implements ColoredAnimal {

	Animal animal;
	
	/**
	 * ctor
	 * @param an
	 */
	public ColoredAnimalDecorator(Animal an){
		animal = an;
	}
	
	/**
	 * paints animal depending on color and type of animal
	 */
	public void paintAnimal(String color) {
		animal.paintAnimal(color);
		animal.setColor(color);
		String nm=null;
		switch(animal.getName()){
		case "Bear":
			nm="bea";
			break;
		case "Elephant":
			nm = "elf";
			break;
		case "Giraffe":
			nm="grf";
			break;
		case "Lion":
			nm="lio";
			break;
		case "Turtle":
			nm = "trt";
			break;
		}
		animal.loadImages(nm);;
	}

}
