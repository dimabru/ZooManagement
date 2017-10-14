package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;
import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;
import food.EFoodType;
import food.IEdible;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * ZooPanel Class
 */
public class ZooPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int MAX_ANIMAL_NUMBER = 10;
	private final static String BACKGROUND_PATH = Animal.PICTURE_PATH
			+ "savanna.jpg";
	private final static String MEAT_PATH = Animal.PICTURE_PATH + "meat.gif";
	public final int RESOLUTION = 25;
	private static ZooFrame frame;
	private static EFoodType Food;
	private static JPanel p1;
	private static JButton[] b_num;
	private static String[] names = { "Add Animal", "Sleep", "Wake up",
			"Clear", "Food", "Info" };
	private static ArrayList<Animal> animals;
	private Plant forFood = null;
	private JScrollPane scrollPane;
	private static boolean isTableVisible;
	private static int totalCount;
	private static BufferedImage img;
	private static BufferedImage img_m;
	private static boolean bgr;

	private static Executor threadPool;
	private static ZooPanel zooPanel = new ZooPanel();
	private static String[] addedNames = { "Decorate", "Duplicate",
			"Save State", "Restore State", "Exit" };
	private static JButton[] a_num;
	private static JPanel p2;
	private static ZooObserver controller;
	private static List<ZooMemento> memento;

	/**
	 * 
	 * @param f zoo frame
	 * @return instance of ZooPanel
	 */
	public static ZooPanel getInstance(ZooFrame f) {
		initPanel(f);
		return zooPanel;
	}

	/**
	 * private ctor
	 */
	private ZooPanel() {
	}

	/**
	 * initialize panel
	 * @param f zoo frame
	 */
	protected static void initPanel(ZooFrame f) {
		threadPool = Executors.newFixedThreadPool(5);
		frame = f;
		Food = EFoodType.NOTFOOD;
		totalCount = 0;
		isTableVisible = false;

		animals = new ArrayList<Animal>();

		controller = new ZooObserver(zooPanel);

		zooPanel.setBackground(new Color(255, 255, 255));

		p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 7, 0, 0));
		p1.setBackground(new Color(0, 150, 255));

		b_num = new JButton[names.length];
		for (int i = 0; i < names.length; i++) {
			b_num[i] = new JButton(names[i]);
			b_num[i].addActionListener(zooPanel);
			b_num[i].setBackground(Color.lightGray);
			p1.add(b_num[i]);
		}

		p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 6, 0, 0));
		p2.setBackground(new Color(0, 150, 255));

		a_num = new JButton[addedNames.length];
		for (int i = 0; i < addedNames.length; i++) {
			a_num[i] = new JButton(addedNames[i]);
			a_num[i].addActionListener(zooPanel);
			a_num[i].setBackground(Color.lightGray);
			p2.add(a_num[i]);
		}

		zooPanel.setLayout(new BorderLayout());
		JPanel p = new JPanel();
		p.add(p1);
		p.add(p2);
		p.setLayout(new GridLayout(2, 6, 0, 0));
		zooPanel.add(p, BorderLayout.SOUTH);

		img = img_m = null;
		bgr = false;
		try {
			img = ImageIO.read(new File(BACKGROUND_PATH));
		} catch (IOException e) {
			System.out.println("Cannot load background");
		}
		try {
			img_m = ImageIO.read(new File(MEAT_PATH));
		} catch (IOException e) {
			System.out.println("Cannot load meat");
		}

		memento = new ArrayList<ZooMemento>();
	}

	/**
	 * paints all components in panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (bgr && (img != null))
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		if (Food == EFoodType.MEAT)
			g.drawImage(img_m, getWidth() / 2 - 20, getHeight() / 2 - 20, 40,
					40, this);

		if ((Food == EFoodType.VEGETABLE) && (forFood != null))
			forFood.drawObject(g);

		synchronized (this) {
			for (Animal an : animals)
				if (an.isRunning())
					an.drawObject(g);
		}
	}

	/**
	 * set background
	 * @param num users selection of background type
	 */
	public void setBackgr(int num) {
		switch (num) {
		case 0:
			setBackground(new Color(255, 255, 255));
			bgr = false;
			break;
		case 1:
			setBackground(new Color(0, 155, 0));
			bgr = false;
			break;
		default:
			bgr = true;
		}
		repaint();
	}

	/**
	 * CallBack function
	 * 
	 * @param f
	 */
	synchronized public void eatFood(Animal an) {
		if (Food != EFoodType.NOTFOOD) {
			if (Food == EFoodType.VEGETABLE)
				forFood = null;
			Food = EFoodType.NOTFOOD;
			an.eatInc();
			totalCount++;
			System.out.println("The " + an.getName() + " with " + an.getColor()
					+ " color and size " + an.getSize() + " ate food.");
		} else {
			System.out.println("The " + an.getName() + " with " + an.getColor()
					+ " color and size " + an.getSize() + " missed food.");
		}
	}

	/**
	 * adds dialog to panel
	 */
	public void addDialog() {
		if (animals.size() == MAX_ANIMAL_NUMBER) {
			JOptionPane.showMessageDialog(this, "You cannot add more than "
					+ MAX_ANIMAL_NUMBER + " animals");
		} else {
			Object[] options = { "Herbivore", "Omnivore", "Carnivore" };
			int answer = -1;

			answer = JOptionPane.showOptionDialog(frame,
					"Please choose animal factory", "Animal factory",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, null);

			if (answer == -1)
				return;

			String type;
			if (answer == 0)
				type = "Herbivore";
			else if (answer == 1)
				type = "Omnivore";
			else
				type = "Carnivore";

			AddAnimalDialog dial = new AddAnimalDialog(this,
					"Add an animal to aquarium", type);
			dial.setVisible(true);
		}
	}

	/**
	 * adds animal
	 * @param an animal to add
	 */
	public void addAnimal(Animal an) {
		animals.add(an);
		an.addObserver(controller);
		an.setTask((ExecutorService) threadPool);
	}

	/**
	 * starts all threads
	 */
	public void start() {
		for (Animal an : animals)
			an.setResume();
	}

	/**
	 * stops all threads
	 */
	public void stop() {
		for (Animal an : animals)
			an.setSuspend();
	}

	/**
	 * clears screen of animals
	 */
	synchronized public void clear() {
		List<Animal> toRemove = new ArrayList<Animal>();

		for (Animal an : animals)
			if (an.isRunning()) {
				an.interrupt();
				toRemove.add(an);
				totalCount -= an.getEatCount();
			}

		for (Animal animal : toRemove) {
			animals.remove(animal);
		}

		Food = EFoodType.NOTFOOD;
		forFood = null;
		totalCount = 0;
		repaint();
	}

	/**
	 * animal eating another animal 
	 * @param predator the eating animal
	 * @param prey the animal to be eaten
	 */
	synchronized public void preyEating(Animal predator, Animal prey) {
		predator.eatInc();
		totalCount -= (prey.getEatCount() - 1);
	}

	/**
	 * adds food to panel
	 */
	synchronized public void addFood() {
		if (Food == EFoodType.NOTFOOD) {
			Object[] options = { "Meat", "Cabbage", "Lettuce" };
			int n = JOptionPane.showOptionDialog(frame, "Please choose food",
					"Food for animals", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			switch (n) {
			case 0: // Meat
				Food = EFoodType.MEAT;
				break;
			case 1: // Cabbage
				Food = EFoodType.VEGETABLE;
				forFood = Cabbage.getIntsance(this);
				break;
			default: // Lettuce
				Food = EFoodType.VEGETABLE;
				forFood = Lettuce.getIntsance(this);
				break;
			}
		} else {
			Food = EFoodType.NOTFOOD;
			forFood = null;
		}
		repaint();
	}

	/**
	 * displays information on all animals
	 */
	public void info() {
		if (isTableVisible == false) {
			int i = 0;
			int sz = animals.size();

			int runCount = 0;

			String[] columnNames = { "Animal", "State", "Color", "Weight",
					"Hor. speed", "Ver. speed", "Eat counter" };
			String[][] data = new String[sz + 1][columnNames.length];
			for (Animal an : animals) {
				String state;
				if (an.isRunning()) {
					state = "running";
					runCount++;
				} else
					state = "blocked";

				data[i][0] = an.getName();
				data[i][1] = state;
				data[i][2] = an.getColor();
				data[i][3] = new Integer((int) (an.getWeight())).toString();
				data[i][4] = new Integer(an.getHorSpeed()).toString();
				data[i][5] = new Integer(an.getVerSpeed()).toString();
				data[i][6] = new Integer(an.getEatCount()).toString();
				i++;
			}
			data[i][0] = "Total";
			data[i][1] = "run=" + runCount + ",blc="
					+ (animals.size() - runCount);
			data[i][6] = new Integer(totalCount).toString();

			JTable table = new JTable(data, columnNames);
			scrollPane = new JScrollPane(table);
			scrollPane.setSize(450, table.getRowHeight() * (sz + 1) + 24);
			add(scrollPane, BorderLayout.CENTER);
			isTableVisible = true;
		} else {
			isTableVisible = false;
		}
		scrollPane.setVisible(isTableVisible);
		repaint();
	}

	/**
	 * stops all threads
	 */
	public void destroy() {
		for (Animal an : animals)
			an.interrupt();
		controller.interrupt();
		System.exit(0);
	}

	/**
	 * decorate an animal
	 */
	public void decorate() {
		boolean toDecorate = false;

		for (Animal animal : animals) {
			if (animal.getColor().equals("Natural")) {
				toDecorate = true;
				break;
			}
		}

		if (!toDecorate) {
			JOptionPane.showMessageDialog(this,
					"You don't have animals for decoration");
			return;
		}

		new DecorateDialog(this).setVisible(true);
	}

	/**
	 * 
	 * @param index of animal to return
	 * @return animal according to index
	 */
	public Animal getAnimal(int index) {
		if (index >= animals.size())
			return null;
		return animals.get(index);
	}

	/**
	 * fills a combo box
	 * @param list to fill
	 * @param which sorting rule 
	 */
	public void fillComboBox(JComboBox<String> list, String which) {

		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).getColor().equals(which)) {

				list.addItem((i + 1) + ".[" + animals.get(i).getName()
						+ ": running=" + animals.get(i).isRunning()
						+ ", weight=" + animals.get(i).getWeight() + ", color="
						+ which + "]");
			} else if (which.equals("All")) {
				list.addItem(animals.get(i).toString());
			}
		}
	}

	/**
	 * perform action upon approval
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b_num[0]) // "Add Animal"
			addDialog();
		else if (e.getSource() == b_num[1]) // "Sleep"
			stop();
		else if (e.getSource() == b_num[2]) // "Wake up"
			start();
		else if (e.getSource() == b_num[3]) // "Clear"
			clear();
		else if (e.getSource() == b_num[4]) // "Food"
			addFood();
		else if (e.getSource() == b_num[5]) // "Info"
			info();
		else if (e.getSource() == a_num[0]) // "Decorate"
			decorate();
		else if (e.getSource() == a_num[1]) // "Duplicate"
			duplicate();
		else if (e.getSource() == a_num[2]) // "Save State"
			saveState();
		else if (e.getSource() == a_num[3]) // "Restore State"
			restoreState();
		else if (e.getSource() == a_num[4]) // "Exit"
			destroy();
	}

	/**
	 * runs thread controller
	 */
	public void run() {
		controller.start();
	}
	
	/**
	 * saves corrent state
	 */
	public void saveState(){
		if (memento.size()>=3){
			JOptionPane.showMessageDialog(null, "There already are three saved states\nUnable to save");
			return;
		}
		memento.add(new ZooMemento(zooPanel));
		JOptionPane.showMessageDialog(null, "State saved succesfully");
	}
	
	/**
	 * loads a state
	 */
	public void restoreState(){
		if (memento.size()==0) {
			JOptionPane.showMessageDialog(null, "You have not saved states");
			return;
		}
		
		Object[] options = {"State 1", "State 2", "State 3", "Cancel"};
		
		int answer = JOptionPane.showOptionDialog(zooPanel, 
				"Please choose state for restore",
				"Saved states", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options[3]);
		
		if (answer==3) return;
		
		if (answer+1>memento.size()){
			JOptionPane.showMessageDialog(null, "There is no such state");
			return;
		}
		
		animals = memento.get(answer).getAnimals();
		totalCount = memento.get(answer).getTotalCount();
		Food = memento.get(answer).getFood();
		
		threadPool = Executors.newFixedThreadPool(5);
		
		for (Animal animal: animals){
			animal.addObserver(controller);
			animal.setTask((ExecutorService)threadPool);
		}
		
		memento.remove(answer);
	}

	/**
	 * duplicates an animal
	 */
	public void duplicate() {
		new DuplicateDialog(zooPanel).setVisible(true);
	}

	/**
	 * checks for prey
	 */
	public void preyCheck() {
		boolean prey_eaten = false;
		synchronized (this) {
			for (Animal predator : animals) {
				for (Animal prey : animals) {
					if (predator != prey
							&& predator.getDiet().canEat(prey)
							&& predator.getWeight() / prey.getWeight() >= 2
							&& (Math.abs(predator.getLocation().getX()
									- prey.getLocation().getX()) < prey
										.getSize())
							&& (Math.abs(predator.getLocation().getY()
									- prey.getLocation().getY()) < prey
										.getSize())) {
						preyEating(predator, prey);
						System.out.print("The " + predator + " cought up the "
								+ prey + " ==> ");
						prey.interrupt();
						animals.remove(prey);
						repaint();
						JOptionPane.showMessageDialog(frame, "" + prey
								+ " killed by " + predator);
						prey_eaten = true;
						break;
					}
				}
				if (prey_eaten)
					break;
			}
		}
	}

	/**
	 * check if animals are changed
	 * @return
	 */
	public boolean isChange() {
		boolean rc = false;
		for (Animal an : animals) {
			if (an.getChanges()) {
				rc = true;
				an.setChanges(false);
			}
		}
		return rc;
	}

	/**
	 * 
	 * @return array of all animals
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}

	/**
	 * 
	 * @return zoo frame
	 */
	public ZooFrame getFrame() {
		return frame;
	}

	/**
	 * 
	 * @return total count of eaten food
	 */
	synchronized public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * @return food if exists
	 */
	synchronized public EFoodType getFood(){
		return Food;
	}
} 
























