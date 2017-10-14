package animals;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * OmnivoreFactory Class
 */
public class OmnivoreFactory implements AbstractZooFactory {

	/**
	 * implemented method that produces omnivore animal
	 */
	@Override
	public Animal produceAnimal(String type) {
		if (type.equals("Bear")) return new Bear();
		else return null;
	}

}
