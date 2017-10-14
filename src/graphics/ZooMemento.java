package graphics;

import java.util.ArrayList;

import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import food.EFoodType;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * ZooMemento Class
 */
public class ZooMemento {

	ArrayList<Animal> animals;
	int totalCount;
	EFoodType food;

	/**
	 * ctor
	 * @param panel zoo panel
	 */
	public ZooMemento(ZooPanel panel) {

		Animal animal = null;
		animals = new ArrayList<Animal>();

		for (int i = 0; i < panel.getAnimals().size(); i++) {
			animal = panel.getAnimal(i);
			if (animal instanceof Lion) {
				animals.add(new Lion());
				animals.get(i).initInstance((int) (animal.getSize() / 0.745),
						(int) (animal.getWeight() / 0.8), animal.getHorSpeed(),
						animal.getVerSpeed(), animal.getColor(), panel);
				animals.get(i).setLocation(animal.getLocation());
			} else if (animal instanceof Bear) {
				animals.add(new Bear());
				animals.get(i).initInstance((int) (animal.getSize() / 0.7),
						(int) (animal.getWeight()), animal.getHorSpeed(),
						animal.getVerSpeed(), animal.getColor(), panel);
				animals.get(i).setLocation(animal.getLocation());
			} else if (animal instanceof Elephant) {
				animals.add(new Elephant());
				animals.get(i).initInstance((int) (animal.getSize() / 1.4),
						(int) (animal.getWeight() / 20 + 45), animal.getHorSpeed(),
						animal.getVerSpeed(), animal.getColor(), panel);
				animals.get(i).setLocation(animal.getLocation());
			} else if (animal instanceof Giraffe) {
				animals.add(new Giraffe());
				animals.get(i).initInstance((int) (animal.getSize() / (4/3)),
						(int) (animal.getWeight() / 2), animal.getHorSpeed(),
						animal.getVerSpeed(), animal.getColor(), panel);
				animals.get(i).setLocation(animal.getLocation());
			} else if (animal instanceof Turtle) {
				animals.add(new Turtle());
				animals.get(i).initInstance((int) (animal.getSize() / 0.5),
						(int) (animal.getWeight() / 0.5), animal.getHorSpeed(),
						animal.getVerSpeed(), animal.getColor(), panel);
				animals.get(i).setLocation(animal.getLocation());
			}
		}
		totalCount = panel.getTotalCount();
		food = panel.getFood();

	}

	/**
	 * 
	 * @return array of animals
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}

	/**
	 * 
	 * @return count of eaten food by animals
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * @return food type if exists
	 */
	public EFoodType getFood() {
		return food;
	}
}
