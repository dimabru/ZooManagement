package animals;

import diet.Carnivore;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * CarnivoreFactory Class
 */
public class CarnivoreFactory implements AbstractZooFactory {
	
	/**
	 * implemented method that produces carnivore animal
	 */
	@Override
	public Animal produceAnimal(String type) {
		if (type.equals("Lion")) return new Lion();
		else return null;
	}

}
