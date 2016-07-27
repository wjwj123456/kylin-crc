/**
* @author       lpt14
* @version      V1.0
*/
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月9日
 * @see
 */
public class Tools {
	public static Date stringToDate(String date) {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date2 = null;
		try {
			date2 = dFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date2;
	}

	public static String dateToString(Date date) {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String result = dFormat.format(date);
		return result;
	}

	// try {
	// //通过Base64解密，将图片数据解密成字节数组
	//
	//
	// byte[] bytes = decoder.decodeBuffer(fileData);
	// //构造字节数组输入流
	//
	//
	// ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	// //读取输入流的数据
	//
	//
	// BufferedImage bi = ImageIO.read(bais);
	// //将数据信息写进图片文件中
	//
	//
	// ImageIO.write(bi, "jpg", f);// 不管输出什么格式图片，此处不需改动
	//
	// bais.close();
	// } catch (IOException e) {
	// System.out.println(e);
	// }

}
