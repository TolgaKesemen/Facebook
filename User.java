

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Contains info of user and methods that a user can use
 * if he/she is signed in.
 * @author Onur Tolga KESEMEN
 * 
 */
public class User {
	
	public static int userNo = 1;
	private int Id;
	private String name;
	private String userName;
	private String password;
	private String graduate;
	private Date dateOfBirth; 
	private Date lastlogin;
	ArrayList<User> friend = new ArrayList<User>();
	ArrayList<User> blockedFriend = new ArrayList<User>();
	ArrayList<User> blockedUser = new ArrayList<User>();
	ArrayList<Post> post = new ArrayList<Post>();
	SimpleDateFormat f;
	
	/**
	 * Class constructor specifying info of user.
	 * @param name	Name of user
	 * @param userName	User name of user
	 * @param password	Password of user
	 * @param dateOfBirth	Birth date of user
	 * @param graduate	School that user has graduated.
	 */
	public User(String name, String userName, String password, Date dateOfBirth,  String graduate) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.graduate = graduate;
		this.dateOfBirth = dateOfBirth;
		this.Id = userNo;
		userNo++;
	}
	
	/**
	 * Returns id of the user.
	 * @return id of the user
	 */
	public int getId() {
		return Id;
	}
	/**
	 * Returns name of the user.
	 * @return	name of the user
	 */
	public String getName() {
		return name;
	}
	/**
	 * Determines name of the user.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns user name of the user.
	 * @return	user name of the user
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Determines user name of the user.
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Returns password of the user.
	 * @return	password of the user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Determines password of the user.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Returns the school that user has graduated.
	 * @return	school that user has graduated.
	 */
	public String getGraduate() {
		return graduate;
	}
	/**
	 * Determines the school that user has graduated.
	 * @param graduate
	 */
	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
	/**
	 * Returns birth day of the user in the form 'dd/MM/yyyy'.
	 * @return	birth day of the user
	 */
	public String getDateOfBirth() {
		f = new SimpleDateFormat("dd/MM/yyyy");
		return f.format(dateOfBirth);
	}
	/**
	 * Determines birth day of the user.
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * Returns the time of last login of the user
	 * @return time of last login
	 */
	public String getLastlogin() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return f.format(lastlogin);
	}
	
	/**
	 * Returns the info of the user.
	 */
	@Override
	public String toString() {
		return "Name: " + getName() + "\n" + "Username: " + getUserName() + "\n" + "Date of Birth: " + getDateOfBirth() + 
				"\n" + "School: " + getGraduate();
	}
	
	/**
	 * Prints the list of users at the output file.
	 * @param user	List of users
	 * @param buffer	Prints the output file
	 * @throws IOException
	 */
	public void listUsers(ArrayList<User> user, BufferedWriter buffer) throws IOException{
		for(int i = 0; i<user.size(); i++){
			buffer.write(user.get(i) + "\n");
			buffer.write("---------------------------\n");;
		}
	}
	
	/**
	 * Updates the info of the user.
	 * @param words	Array of type string that holds info of the user
	 * @param u	User object
	 * @param buffer
	 * @throws ParseException
	 * @throws IOException
	 */
	public void updateProfile(String[] words, User u, BufferedWriter buffer) throws ParseException, IOException{
		f = new SimpleDateFormat("MM/dd/yyyy");
		Date date;
		date = f.parse(words[2]);
		u.setName(words[1]);
		u.setDateOfBirth(date);
		u.setGraduate(words[3]);
		buffer.write("Your user profile has been successfully updated.");
	}
	
	/**
	 * Changes password of the user if old password entered correctly,
	 * otherwise giving an error message.
	 * @param words	Array of type string that holds new and old password
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void changePassword(String[] words, User u, BufferedWriter buffer) throws IOException{
		if(u.getPassword().equalsIgnoreCase(words[1])){
			u.setPassword(words[2]);
			buffer.write("Your password has been successfully changed.");
		}
		else{	buffer.write("Password mismatch! Please, try again.");	}
	}
	
	/**
	 * Adding new friend in the friend list of the user if he/she is already in the list
	 * then prints a message for info, if user who wanted to be added does not exist
	 * giving an error message.
	 * @param words	Array of type string that holds the user name of the user who wanted to be added
	 * @param u	User object
	 * @param user	List of users
	 * @param buffer
	 * @throws IOException
	 */
	public void addFriend(String[] words, User u, ArrayList<User> user, BufferedWriter buffer) throws IOException{
		boolean x = false;
		for(int i = 0; i<user.size(); i++){
			if(user.get(i).getUserName().equalsIgnoreCase(words[1])){
				if(u.friend.contains(user.get(i))){	
					buffer.write("This user is already in your friend list!");	
					x = true; 
					break;
				}
				else{ 
					u.friend.add(user.get(i));
					buffer.write(words[1] + " has been successfully added to your friend list.");
					x = true; 
					break;
				}
			}
		}
		if(!x){	buffer.write("No such user!");	}
	}
	
	/**
	 * Removing a friend from the friend list of the user if he/she is in the list,
	 * otherwise giving an error message.
	 * @param words	Array of type string that holds user name of user who wanted to be removed
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void removeFriend(String[] words, User u, BufferedWriter buffer) throws IOException{
		boolean x = false;
		for(int i = 0; i<u.friend.size(); i++){
			if(u.friend.get(i).getUserName().equalsIgnoreCase(words[1])){
				u.friend.remove(i);
				buffer.write(words[1] + " has been successfully removed from your friend list.");
				x = true; 
				break;
			}
		}
		if(!x){	buffer.write("No such friend!");	}
	}
	
	/**
	 * Prints the list of friends of a specific user at the output file.
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void listFriends(User u, BufferedWriter buffer) throws IOException{
		for(int i = 0; i<u.friend.size(); i++){
			buffer.write(u.friend.get(i) + "\n");
			buffer.write("---------------------------\n");;
		}
	}
	
	/**
	 * Creating a text post and then add it to post list of the user.
	 * If tagged friends of the post aren't in the friend list of the user
	 * then giving message that those users can not be tagged to the post.
	 * @param words	Array of type string that holds the info of text post
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void addPostText(String[] words, User u, BufferedWriter buffer) throws IOException{
		String[] tagged = null;
		tagged = words[4].split(":");
		boolean condition = false;
		if(tagged!=null){
			for(String i : tagged){
				for(int k = 0; k<u.friend.size(); k++){
					if(u.friend.get(k).getUserName().equalsIgnoreCase(i)){
						condition = true;
						break;
					}
				}
				if(!condition){
					buffer.write(i + " is not your friend, and will not be tagged!\n");
				}
				condition = false;
			}
		}
		u.post.add(new TextPost(words[1], Double.parseDouble(words[3]), Double.parseDouble(words[2]), words[4], u));
		buffer.write("The post has been successfully added.");
	}
	
	/**
	 * Creating a image post and then add it to post list of the user.
	 * If tagged friends of the post aren't in the friend list of the user
	 * then giving message that those users can not be tagged to the post.
	 * @param words	Array of type string that holds the info of image post
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void addPostImage(String[] words, User u, BufferedWriter buffer) throws IOException{
		String[] tagged = null;
		tagged = words[4].split(":");
		boolean condition = false;
		if(tagged!=null){
			for(String i : tagged){
				for(int k = 0; k<u.friend.size(); k++){
					if(u.friend.get(k).getUserName().equalsIgnoreCase(i)){
						condition = true;
						break;
					}
				}
				if(!condition){
					buffer.write(i + " is not your friend, and will not be tagged!\n");
				}
				condition = false;
			}
		}
		String[] resolution = words[6].split("x");
		u.post.add(new ImagePost(words[1], Double.parseDouble(words[3]), Double.parseDouble(words[2]), words[4], 
				words[5], Integer.parseInt(resolution[0]), Integer.parseInt(resolution[1]), u));
		buffer.write("The post has been successfully added.");
	}
	
	/**
	 * Creating a video post and then add it to post list of the user.
	 * If tagged friends of the post aren't in the friend list of the user
	 * then giving message that those users can not be tagged to the post.
	 * If length of the video exceeds maximum video duration (10 minutes) 
	 * then giving an error message.
	 * @param words	Array of type string that holds the info of video post
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void addPostVideo(String[] words, User u, BufferedWriter buffer) throws IOException{
		if(Integer.parseInt(words[6])>10){
			buffer.write("Error: Your video exceeds maximum allowed duration of 10 minutes.");
		}
		else{
			String[] tagged = null;
			tagged = words[4].split(":");
			boolean condition = false;
			if(tagged!=null){
				for(String i : tagged){
					for(int k = 0; k<u.friend.size(); k++){
						if(u.friend.get(k).getUserName().equalsIgnoreCase(i)){
							condition = true;
							break;
						}
					}
					if(!condition){
						buffer.write(i + " is not your friend, and will not be tagged!\n");
					}
					condition = false;
				}
			}
			u.post.add(new VideoPost(words[1], Double.parseDouble(words[3]), Double.parseDouble(words[2]), words[4], 
					words[5], Integer.parseInt(words[6]), u));
			buffer.write("The post has been successfully added.");
		}
	}
	
	/**
	 * Removes the last post in the post list of the user if there is any,
	 * otherwise giving an error message.
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void removeLastPost(User u, BufferedWriter buffer) throws IOException{
		if(!u.post.isEmpty()){
			int x = u.post.size();
			u.post.remove(x-1);
			buffer.write("Your last post has been successfully removed.");
		}
		else{
			buffer.write("Error: You don't have any posts.");
		}
	}
	
	/**
	 * Blocking users that who wanted to be blocked. If he/she is a friend, adding him/her
	 * to blocked friend list and removing from friend list. If user who wanted to be blocked
	 * does not	exist then giving an error message.
	 * @param words	Array of type string that holds user name of who wanted to be blocked
	 * @param u	User object
	 * @param user	List of users
	 * @param buffer
	 * @throws IOException
	 */
	public void block(String[] words, User u, ArrayList<User> user, BufferedWriter buffer) throws IOException{
		boolean x = false;
		for(int i = 0; i<user.size(); i++){
			if(user.get(i).getUserName().equalsIgnoreCase(words[1])){
				if(u.friend.contains(user.get(i))){	
					u.blockedFriend.add(user.get(i));
					u.blockedUser.add(user.get(i));
					u.friend.remove(user.get(i));
					buffer.write(words[1] + " has been successfully blocked.");	
					x = true; 
					break;
				}
				else{ 
					u.blockedUser.add(user.get(i));
					buffer.write(words[1] + " has been successfully blocked.");
					x = true; 
					break;
				}
			}
		}
		if(!x){	buffer.write("No such user!");	}
	}
	
	/**
	 * Unblocking users that who wanted to be unblocked in the list of blocked friend or
	 * blocked user. If he/she is a friend, adding him/her back to friend list and
	 * removing from the blocked friend list. If user who wanted to be unblocked isn't in 
	 * the list of blocked users then giving an error message.
	 * @param words	Array of type string that holds user name of who wanted to be unblocked
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void unblock(String[] words, User u, BufferedWriter buffer) throws IOException{
		boolean x = false;
		for(int i = 0; i<u.blockedUser.size(); i++){
			if(u.blockedUser.get(i).getUserName().equalsIgnoreCase(words[1])){
				if(u.blockedFriend.contains(u.blockedUser.get(i))){	
					u.friend.add(u.blockedUser.get(i));
					u.blockedFriend.remove(u.blockedUser.get(i));
					u.blockedUser.remove(i);
					buffer.write(words[1] + " has been successfully unblocked.");	
					x = true; 
					break;
				}
				else{ 
					u.blockedUser.remove(i);
					buffer.write(words[1] + " has been successfully unblocked.");
					x = true; 
					break;
				}
			}
		}
		if(!x){	buffer.write("No such user in your blocked users list!");	}
	}
	
	/**
	 * Prints the blocked friend list of a specific user at the output file if the list
	 * isn't empty.
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void showBlockedFriends(User u, BufferedWriter buffer) throws IOException{
		if(u.blockedUser.isEmpty()){
			buffer.write("You haven’t blocked any users yet!");
		}
		else{
			if(u.blockedFriend.isEmpty()){
				buffer.write("You haven’t blocked any friends yet!");
			}
			else{
				for(int i = 0; i<u.blockedFriend.size(); i++){
					buffer.write(u.blockedFriend.get(i) + "\n");
					buffer.write("---------------------------\n");;
				}
			}
		}
	}
	
	/**
	 * Prints the blocked user list of a specific user at the output file if the list
	 * isn't empty.
	 * @param u	User object
	 * @param buffer
	 * @throws IOException
	 */
	public void showBlockedUsers(User u, BufferedWriter buffer) throws IOException{
		if(u.blockedUser.isEmpty()){
			buffer.write("You haven’t blocked any users yet!");
		}
		else{
			for(int i = 0; i<u.blockedUser.size(); i++){
				buffer.write(u.blockedUser.get(i) + "\n");
				buffer.write("---------------------------\n");;
			}
		}
	}
	
	
}
