package vo;

import java.io.Serializable;

/**
 * Created by song on 16-9-26.
 * <p>
 * 文件(夹)对象
 */
public class FileVO implements Serializable {

    private final String name;

    private final String size;

    private final String type;

    public FileVO(String name, String size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
