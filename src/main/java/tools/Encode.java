package tools;

import java.io.IOException;

/**
 * transfer characterEncoding from iso-8859-1 to utf-8
 * 
 * @author song
 *
 */
public class Encode {
	public static String transfer(String source) throws IOException {
		return new String(source.getBytes("iso-8859-1"), "utf-8");
	}
}
