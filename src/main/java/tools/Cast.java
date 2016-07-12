package tools;

/**
 * Created by song on 16-6-1.
 * <p>
 * 转换工具类 将Object类型转换为List/Map类型
 */
public class Cast {
	private Cast() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
		T result = null;

		try {
			result = (T) obj;
		} catch (ClassCastException e) {
			e.printStackTrace();
		}

		return result;
	}
}
