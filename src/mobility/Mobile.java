package mobility;

/**
 * @author baroh
 *
 */
public abstract class Mobile implements ILocatable {
	protected Point location;

	/**
	 * ctor
	 * @param location to update
	 */
	public Mobile(Point location) {
		this.setLocation(location);
	}
	/**
	 * get location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * set location
	 */
	public boolean setLocation(Point newLocation) {
		this.location = newLocation;
		return true;

	}
}
