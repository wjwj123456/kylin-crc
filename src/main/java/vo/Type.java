package vo;

import java.io.Serializable;

public enum Type  implements Serializable {
	code,
	document;
	
	public static Type getType(String type) {
		return Type.code;
	}
}
