package graphics;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * IAnimalBehavior Interface
 */
public interface IAnimalBehavior {
	 abstract public String getName();
	 abstract public void setSuspend();
	 abstract public void setResume();
	 abstract public int getSize();
	 abstract public void eatInc();
	 abstract public int getEatCount();
	 abstract public boolean getChanges();
	 abstract public void setChanges(boolean state);
}

