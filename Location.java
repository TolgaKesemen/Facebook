
/**
	 * Contains Latitude and longitude attributes of location.
	 * @author Onur Tolga KESEMEN
	 * 
	 */
public class Location {
	
	private double latitude;
	private double longitude;
	
	/**
	 * Class constructor specifying info of location.
	 * @param latitude	Latitude of location
	 * @param longitude	Longitude of location
	 */
	public Location(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Returns Latitude of location.
	 * @return Latitude of location
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * Returns longitude of location.
	 * @return longitude of location
	 */
	public double getLongitude() {
		return longitude;
	}

}
