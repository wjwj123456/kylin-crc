package bl;

import org.json.JSONArray;
import org.junit.Test;
import vo.FileVO;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by song on 16-9-26.
 */
public class FileBlImplTest {
    @Test
    public void getFileList() throws Exception {
        List<FileVO> fileList = new FileBlImpl().getFileList("test/mr");

        JSONArray array = new JSONArray(fileList);
        System.out.println(array.toString());
    }
}