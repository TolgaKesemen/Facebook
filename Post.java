

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Contains the info of a post.
 * @author Onur Tolga KESEMEN
 * 
 */
public abstract class Post implements PostInterface{
	
	private UUID ID;
	private Date date;
	private String text;
	Location location;
	String[] tagged = null;
	ArrayList<User> taggedFriends = new ArrayList<User>();
	
	/**
	 * Class constructor specifying the info of the post.
	 * @param text	Text of the post
	 * @param latitude	Latitude of location of post
	 * @param longitude	Longitude of location of post
	 * @param tag	Array of type string that holds user names of tagged friends of the post
	 * @param u	User object
	 */
	public Post(String text, double latitude, double longitude, String tag, User u) {
		super();
		this.ID = UUID.randomUUID();
		this.date = new Date();
		this.text = text;
		location = new Location(latitude, longitude);
		tagged = tag.split(":");
		if(tagged!=null){
			for(String i : tagged){
				for(int k = 0; k<u.friend.size(); k++){
					if(u.friend.get(k).getUserName().equalsIgnoreCase(i)){
						taggedFriends.add(u.friend.get(k));
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Returns id of the post.
	 * @return id of the post
	 */
	public UUID getID() {
		return ID;
	}
	/**
	 * Returns date of creation of post.
	 * @return date of creation of post
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Returns text of the post.
	 * @return text of the post
	 */
	public String getText() {
		return text;
	}
	/**
	 * Determines text of the post.
	 * @param text text of the post
	 */
	public void setText(String text) {
		this.text = text;
	}

	abstract String showTaggedUsers();
	abstract void showPostLocation();

	
}
