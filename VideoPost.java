

import java.text.SimpleDateFormat;

/**
 * Contains the info of a image post.
 * @author Onur Tolga KESEMEN
 * 
 */
public class VideoPost extends TextPost{
	
	private String filepath;
	private int videoDuration;
	
	/**
	 * Class constructor specifying info of video post.
	 * @param text Text of the post
	 * @param latitude Latitude of location of post
	 * @param longitude Longitude of location of post
	 * @param tag Array of type string that holds user names of tagged friends of the post
	 * @param filepath File path of the video post
	 * @param videoDuration Video duration of the video post
	 * @param u User object
	 */
	public VideoPost(String text, double latitude, double longitude, String tag, String filepath,
			int videoDuration, User u) {
		super(text, latitude, longitude, tag, u);
		this.filepath = filepath;
		this.videoDuration = videoDuration;
	}
	
	/**
	 * Returns file path of video post.
	 * @return file path of video post
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * Returns video duration of video post.
	 * @return video duration of video post
	 */
	public int getVideoDuration() {
		return videoDuration;
	}
	
	/**
	 * Returns info of video post.
	 * @return info of video post
	 */
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		String Date = f.format(getDate());
		return getText() + "\n" + "Date: " + Date + "\n" + "Location: " + location.getLongitude() + ", " + location.getLatitude() + 
				"\n" + "Video: " + getFilepath() + "\n" + "Video duration: " + getVideoDuration() + " minutes" + "\n";
	}
	
	
}
