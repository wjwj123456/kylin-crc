package vo;

import java.io.Serializable;

/**
 * Created by song on 16-9-26.
 *
 * 文件(夹)对象
 */
public class FileVO implements Serializable {

    private String name;

    private String size;

    public FileVO(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
