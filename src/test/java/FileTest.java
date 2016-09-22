import org.json.JSONArray;

import java.io.File;

/**
 * Created by song on 16-9-18.
 *
 * 测试文件写入
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("/home/song/opt");
        String[] list = file.list();

        assert list != null;
        JSONArray array = new JSONArray(list);
        System.out.println(array.toString());
    }
}
