package animals;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * HerbivoreFactory Class
 */
public class HerbivoreFactory implements AbstractZooFactory {

	/**
	 * implemented method that produces herbivore animal
	 */
	@Override
	public Animal produceAnimal(String type) {
		if (type.equals("Giraffe")) return new Giraffe();
		else if (type.equals("Turtle")) return new Turtle();
		else if (type.equals("Elephant")) return new Elephant();
		return null;
	}

}
