

import java.util.Date;
import java.util.UUID;

/**
 * Contains abstract classes that represent post behaviors.
 * @author Onur Tolga KESEMEN
 */
public interface PostInterface {
	
	
	/**
	 * @param text
	 */
	void setText(String text);
	String getText();
	UUID getID();
	Date getDate();
}
