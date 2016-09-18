import java.io.File;

/**
 * Created by song on 16-9-18.
 *
 * 测试文件写入
 */
public class FileTest {
    public static void main(String[] args) {
        System.out.println(new File("/opt/test").mkdir());
        System.out.println(new File("/home/song/opt/test").mkdir());
        System.out.println(new File("/home").exists());
    }
}
