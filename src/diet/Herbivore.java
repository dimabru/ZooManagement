package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * Herbivore Class
 */
public class Herbivore implements IDiet {

	/**
	 * check if animal can eat food
	 */
	@Override
	public boolean canEat(IEdible food) {
		return (food.getFoodtype() == EFoodType.VEGETABLE);
	}

	/**
	 * check if animal can eat food
	 */
	public boolean canEat(EFoodType food_type) {
		return food_type == EFoodType.VEGETABLE;
	}
	
	/**
	 * animal tries to eat food
	 */
	@Override
	public boolean eat(Animal animal, IEdible food) {
		boolean isSuccess = canEat(food);
		if (isSuccess) {
			animal.setWeight(animal.getWeight() * 1.07);
		}
		return isSuccess;
	}

	/**
	 * to string
	 */
	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "]";
	}
}
