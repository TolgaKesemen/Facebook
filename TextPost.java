

import java.text.SimpleDateFormat;

/**
 * Contains the info of a text post.
 * @author Onur Tolga KESEMEN
 * 
 */
public class TextPost extends Post{
	
	
	/**
	 * Class constructor specifying info of text post.
	 * @param text	Text of the post
	 * @param latitude	Latitude of location of post
	 * @param longitude Longitude of location of post
	 * @param tag	Array of type string that holds user names of tagged friends of the post
	 * @param u	User object
	 */
	public TextPost(String text, double latitude, double longitude, String tag, User u) {
		super(text, latitude, longitude, tag, u);
	}
	
	/**
	 * Returns the list of tagged friends of the text post in type of string.
	 * If there is none then returns empty string.
	 * @return	the list of tagged friends of the post
	 */
	@Override
	String showTaggedUsers() {
		String taggedFriendsName = "";
		if(!taggedFriends.isEmpty()){
			for(int i= 0; i<taggedFriends.size(); i++){
				taggedFriendsName += taggedFriends.get(i).getName() + ", ";
			}
		}
		return taggedFriendsName;
	}
	
	/**
	 * Prints the info of the location of text post.
	 */
	@Override
	void showPostLocation() {
		System.out.println("Location of this post");
		System.out.println("Latitude: " + location.getLatitude());
		System.out.println("Longitude: " + location.getLongitude());
	}
	
	/**
	 * Returns the info of text post.
	 * @return the info of text post
	 */
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		String Date = f.format(getDate());
		return getText() + "\n" + "Date: " + Date + "\n" + "Location: " + location.getLongitude() + ", " + location.getLatitude() + "\n";
	}
	
}
