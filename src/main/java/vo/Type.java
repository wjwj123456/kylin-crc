package vo;

import java.io.Serializable;

public enum Type implements Serializable {
	code, document;

	public static Type getType(String type) {
		if (type.equals("代码评审")) {
			return code;
		} else {
			return document;
		}
	}
}
