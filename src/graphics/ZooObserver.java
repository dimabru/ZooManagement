package graphics;

import java.util.Observable;
import java.util.Observer;

import animals.Animal;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * ZooObserver Class
 */
public class ZooObserver extends Thread implements Observer {

	ZooPanel panel;

	/**
	 * ctor
	 * @param pan zoo panel
	 */
	public ZooObserver(ZooPanel pan) {
		panel = pan;
	}

	/**
	 * repaints after every update of each animal state
	 */
	@Override
	public void update(Observable animal, Object arg1) {
		 panel.repaint();
	}

	/**
	 * runs all checks for edible animals
	 */
	public void run() {
		while (true) {
			if (panel.isChange())
				panel.repaint();

			boolean prey_eaten = false;
			synchronized (panel) {
				for (Animal predator : panel.getAnimals()) {
					for (Animal prey : panel.getAnimals()) {
						if (predator != prey
								&& predator.getDiet().canEat(prey)
								&& predator.getWeight() / prey.getWeight() >= 2
								&& (Math.abs(predator.getLocation().getX()
										- prey.getLocation().getX()) < prey
											.getSize())
								&& (Math.abs(predator.getLocation().getY()
										- prey.getLocation().getY()) < prey
											.getSize())) {
							panel.preyEating(predator, prey);
							System.out.print("The " + predator
									+ " cought up the " + prey + " ==> ");
							prey.interrupt();
							panel.getAnimals().remove(prey);
							panel.repaint();
							prey_eaten = true;
							break;
						}
					}
					if (prey_eaten)
						break;
				}
			}
			try {
				Thread.sleep(1000 / panel.RESOLUTION);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}
