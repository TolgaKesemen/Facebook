

import java.text.SimpleDateFormat;

/**
 * Contains the info of a image post.
 * @author Onur Tolga KESEMEN
 * 
 */
public class ImagePost extends TextPost{
	
	private String filepath;
	private int width;
	private int height;
	
	/**
	 * Class constructor specifying info of image post.
	 * @param text	Text of the post
	 * @param latitude	Latitude of location of post
	 * @param longitude	Longitude of location of post
	 * @param tag	Array of type string that holds user names of tagged friends of the post
	 * @param filepath	File path of the image post
	 * @param width	width of resolution of post
	 * @param height	height of resolution of post
	 * @param u	User object
	 */
	public ImagePost(String text, double latitude, double longitude, String tag, String filepath, int width,
			int height, User u) {
		super(text, latitude, longitude, tag, u);
		this.filepath = filepath;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the file path of image post.
	 * @return file path of image post
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * Returns width of resolution of image post.
	 * @return width of resolution of image post
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * Returns height of resolution of image post.
	 * @return height of resolution of image post
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns info of image post.
	 * @return info of image post
	 */
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		String Date = f.format(getDate());
		return getText() + "\n" + "Date: " + Date + "\n" + "Location: " + location.getLongitude() + ", " + location.getLatitude() + 
				"\n" + "Image: " + getFilepath() + "\n" + "Image resolution: " + getWidth() + "x" + getHeight() + "\n";
	}

	
}
