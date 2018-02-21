

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Reading commands, creating, adding and removing users,
 * showing posts and allowing users to sign in.
 * @author Onur Tolga KESEMEN
 * 
 */
public class UserCollection {
	
	static ArrayList<User> user = new ArrayList<User>();
	static ArrayList<User> signedInUsers = new ArrayList<User>();
	SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
	Date date;
	
	/**
	 * Class constructor creating users with respect to info that comes 
	 * from the input file.
	 * @param words	Array of type string that holds info of user.
	 * @throws ParseException
	 */
	public UserCollection(String[] words) throws ParseException{
		date = f.parse(words[3]);
		user.add(new User(words[0], words[1], words[2], date, words[4]));
	}
	
	/**
	 * Creating a user and then adding that user to the system, 
	 * print the output file the process is successful or not.
	 * @param words	Array of type string that holds info of user.
	 * @param buffer	Prints the output file.
	 * @throws ParseException
	 * @throws IOException
	 */
	public void addUser(String[] words, BufferedWriter buffer) throws ParseException, IOException{
		date = f.parse(words[4]);
		boolean x = user.add(new User(words[1], words[2], words[3], date, words[5]));
		if(x){	buffer.write(words[1] + " has been successfully added.");	}
		else{	buffer.write(words[1] + " could not added.");	}
	}
	
	/**
	 * Removing an existing user, if the user not exist giving an error message.
	 * @param words	Array of type string that holds id of user.
	 * @param buffer	Prints the output file.
	 * @throws IOException
	 */
	public void removeUser(String[] words, BufferedWriter buffer) throws IOException{
		boolean x = false;
		for(int i = 0; i<user.size(); i++){
			if(user.get(i).getId() == Integer.parseInt(words[1])){
				user.remove(i);
				buffer.write("User has been successfully removed.");
				x = true;
				break;
			}
		}
		if(!x){	buffer.write("No such user!");	}
	}
	
	/**
	 * Allows user to sign in by taking password, if the password is wrong 
	 * giving an error message, if the user not exist giving an error message.
	 * @param words	Array of type string that holds user name and password.
	 * @param buffer	Prints the output file.
	 * @throws IOException
	 */
	public void signIn(String[] words, BufferedWriter buffer) throws IOException{
		boolean x = false;
		for(int i = 0; i<user.size(); i++){
			if(user.get(i).getUserName().equalsIgnoreCase(words[1])){
				if(user.get(i).getPassword().equalsIgnoreCase(words[2])){
					signedInUsers.add(user.get(i));
					buffer.write("You have successfully signed in.");
					x = true;
					break;
				}
				else{	x = true;	buffer.write("Invalid username or password! Please try again.");	}
			}
		}
		if(!x){	buffer.write("No such user!");	}
	}
	
	/**
	 * Showing the given user's posts with their info, if there isn't any posts then 
	 * giving an error message, if the user not exist, giving an error message.
	 * @param words	Array of type string that holds user name.
	 * @param buffer	Prints the output file.
	 * @throws IOException
	 */
	public void showPosts(String[] words, BufferedWriter buffer) throws IOException{
		boolean x = false;
		for(int i = 0; i<user.size(); i++){
			if(user.get(i).getUserName().equalsIgnoreCase(words[1])){
				if(!user.get(i).post.isEmpty()){
					buffer.write("**************\n");
					buffer.write(words[1] + "'s Posts\n");
					buffer.write("**************\n");
					for(int j= 0; j<user.get(i).post.size(); j++){
						buffer.write(user.get(i).post.get(j).toString());
						String taggedFriendName = user.get(i).post.get(j).showTaggedUsers();
						if(taggedFriendName != ""){
							buffer.write("Friends tagged in this post: "+ taggedFriendName + "\n");
						}
						buffer.write("----------------------\n");
					}
					x = true;
				}
				else{	x = true;	buffer.write(words[1] + " does not have any posts yet.");	}
			}
		}
		if(!x){	buffer.write("No such user!");	}
	}
	
	/**
	 * With respect to command calling the appropriate methods,
	 * if there is a constraint for the method like sign in and the user did not sign in,
	 * then giving an error message.
	 * @param words	Array of type string that holds command.
	 * @param buffer	Prints the output file.
	 * @throws Exception
	 */
	public void Write(String[] words, BufferedWriter buffer)throws Exception {
		switch(words[0]){
		case "ADDUSER" :
			addUser(words, buffer);
			break;
		case "REMOVEUSER" :
			removeUser(words, buffer);
			break;
		case "SIGNIN" :
			signIn(words, buffer);
			break;
		case "LISTUSERS" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).listUsers(user, buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "UPDATEPROFILE" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).updateProfile(words, signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "CHPASS" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).changePassword(words, signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "ADDFRIEND" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).addFriend(words, signedInUsers.get(0), user, buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "REMOVEFRIEND" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).removeFriend(words, signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "LISTFRIENDS" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).listFriends(signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "ADDPOST-TEXT" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).addPostText(words, signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "ADDPOST-IMAGE" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).addPostImage(words, signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "ADDPOST-VIDEO" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).addPostVideo(words, signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "REMOVELASTPOST" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).removeLastPost(signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "SHOWPOSTS" :
			showPosts(words, buffer);
			break;
		case "BLOCK" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).block(words, signedInUsers.get(0), user, buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "SHOWBLOCKEDFRIENDS" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).showBlockedFriends(signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "UNBLOCK" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).unblock(words, signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "SHOWBLOCKEDUSERS" :
			if(!signedInUsers.isEmpty()){	signedInUsers.get(0).showBlockedUsers(signedInUsers.get(0), buffer);	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		case "SIGNOUT" :
			if(!signedInUsers.isEmpty()){	signedInUsers.clear();	buffer.write("You have successfully signed out.");	}
			else{	buffer.write("Error: Please sign in and try again.");	}
			break;
		}
	}

	
}
