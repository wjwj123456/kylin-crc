/**
* @author       lpt14
* @version      V1.0
*/
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: ����������
 *
 * @author lpt14
 * @since 2016��7��9��
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
	// //ͨ��Base64���ܣ���ͼƬ���ݽ��ܳ��ֽ�����
	//
	//
	// byte[] bytes = decoder.decodeBuffer(fileData);
	// //�����ֽ�����������
	//
	//
	// ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	// //��ȡ������������
	//
	//
	// BufferedImage bi = ImageIO.read(bais);
	// //��������Ϣд��ͼƬ�ļ���
	//
	//
	// ImageIO.write(bi, "jpg", f);// �������ʲô��ʽͼƬ���˴�����Ķ�
	//
	// bais.close();
	// } catch (IOException e) {
	// System.out.println(e);
	// }

}
