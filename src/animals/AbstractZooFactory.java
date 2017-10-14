package animals;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * AbstractZooFactory Interface
 */
public interface AbstractZooFactory {
	
	/**
	 * produce an animal interface
	 * @param type which animal
	 * @return new Animal
	 */
	public Animal produceAnimal(String type);
	 
	/**
	 * Create an animal factory
	 * @param foodType type of animal
	 * @return new factory type
	 */
	public static AbstractZooFactory createAnimalFactory(String foodType){
		AbstractZooFactory factory=null;
		
		switch(foodType){
			case "Plant": //Herbivore
				factory = new HerbivoreFactory();
				break;
			case "Mix": //Omnivore
				factory = new OmnivoreFactory();
				break;
			case "Meat": //Carnivore
				factory = new CarnivoreFactory();
				break;
		}
		return factory;
	}
	
}
