/**
* @author       lpt14
* @version      V1.0
*/
package vo;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: 锛堢被鎻忚堪锛�
 *
 * @author lpt14
 * @since 2016骞�7鏈�19鏃�
 * @see
 */
public enum Language {
	c, java, Objective_C, cpp, Ruby, csharp, JavaScript, pointnet, PHP, Swift, Groovy, MATLAB, D, R, Perl, python, other, none;

	private static Map<String, Language> mapping;

	static {
		mapping = new HashMap<>();
		mapping.put("c", c);
		mapping.put("java", java);
		mapping.put("objective_c", Objective_C);
		mapping.put("c++", cpp);
		mapping.put("ruby", Ruby);
		mapping.put("c#", csharp);
		mapping.put("javascript", JavaScript);
		mapping.put(".net", pointnet);
		mapping.put("php", PHP);
		mapping.put("swift", Swift);
		mapping.put("groovy", Groovy);
		mapping.put("matlab", MATLAB);
		mapping.put("d", D);
		mapping.put("r", R);
		mapping.put("perl", Perl);
		mapping.put("python", python);
		mapping.put("other", other);
		mapping.put("none", none);
	}

	/**
	 * transfer string into language object
	 * 
	 * @param language
	 * @return
	 */
	public static Language transfer(String language) {
		return mapping.get(language.toLowerCase());
	}
}
