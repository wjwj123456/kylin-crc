package tools;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by song on 16-9-28.
 * <p>
 * 文件类型工具类,使用apache.tika 判断
 */
public class FileTypeUtil {

    /**
     * 文本文件类型映射
     */
    private static final Map<String, String> TEXT_TYPE_MAPPING;

    /**
     * 图片文件类型映射
     */
    private static final Map<String, String> IMAGE_TYPE_MAPPING;

    /**
     * 视频文件类型映射
     */
    private static final Map<String, String> VIDEO_TYPE_MAPPING;

    /**
     * 应用文件类型映射
     */
    private static final Map<String, String> APPLICATION_TYPE_MAPPING;

    /**
     * 压缩文件映射
     */
    private static final Map<String, String> COMPRESSED_TYPE_MAPPING;

    static {
        TEXT_TYPE_MAPPING = new HashMap<>();

        TEXT_TYPE_MAPPING.put("text/css", "css");
        TEXT_TYPE_MAPPING.put("text/html", "html");
        TEXT_TYPE_MAPPING.put("text/x-java-source", "java");
        TEXT_TYPE_MAPPING.put("text/x-csrc", "c");
        TEXT_TYPE_MAPPING.put("text/x-c++src", "cpp");
        TEXT_TYPE_MAPPING.put("text/x-php", "php");
        TEXT_TYPE_MAPPING.put("text/x-python", "python");
        TEXT_TYPE_MAPPING.put("text/x-jsp", "jsp");
        TEXT_TYPE_MAPPING.put("text/x-matlab", "matlab");
        TEXT_TYPE_MAPPING.put("text/x-sql", "sql");
        TEXT_TYPE_MAPPING.put("text/x-web-markdown", "markdown");
        TEXT_TYPE_MAPPING.put("text/plain", "txt");
    }

    static {
        IMAGE_TYPE_MAPPING = new HashMap<>();

        IMAGE_TYPE_MAPPING.put("image/png", "png");
        IMAGE_TYPE_MAPPING.put("image/jpeg", "jpg/jpeg");
        IMAGE_TYPE_MAPPING.put("image/gif", "gif");
    }

    static {
        VIDEO_TYPE_MAPPING = new HashMap<>();

        VIDEO_TYPE_MAPPING.put("video/mp4", "mp4");
        VIDEO_TYPE_MAPPING.put("video/x-flv", "flv");
        VIDEO_TYPE_MAPPING.put("video/x-matroska", "mkv");
        VIDEO_TYPE_MAPPING.put("video/x-msvideo", "avi");
        VIDEO_TYPE_MAPPING.put("video/quicktime", "mov");
    }

    static {
        APPLICATION_TYPE_MAPPING = new HashMap<>();

        APPLICATION_TYPE_MAPPING.put("application/javascript", "javascript");
        APPLICATION_TYPE_MAPPING.put("application/xml", "xml");
        APPLICATION_TYPE_MAPPING.put("application/java-vm", "java-vm");
        APPLICATION_TYPE_MAPPING.put("application/x-sh", "shell");
        APPLICATION_TYPE_MAPPING.put("application/pdf", "pdf");
        APPLICATION_TYPE_MAPPING.put("application/vnd.android.package-archive", "apk");
        APPLICATION_TYPE_MAPPING.put("application/x-executable", "exe");
        APPLICATION_TYPE_MAPPING.put("application/vnd.ms-htmlhelp", "chm");
        // .doc
        APPLICATION_TYPE_MAPPING.put("application/msword", "word");
        // .ppt
        APPLICATION_TYPE_MAPPING.put("application/vnd.ms-powerpoint", "powerpoint");
        // .xls
        APPLICATION_TYPE_MAPPING.put("application/vnd.ms-excel", "excel");
        // .docx
        APPLICATION_TYPE_MAPPING.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "word");
        // .pptx
        APPLICATION_TYPE_MAPPING.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", "powerpoint");
        // .xlsx
        APPLICATION_TYPE_MAPPING.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "excel");
        APPLICATION_TYPE_MAPPING.put("application/octet-steam", "other");
    }

    static {
        COMPRESSED_TYPE_MAPPING = new HashMap<>();

        COMPRESSED_TYPE_MAPPING.put("application/java-archive", "jar");
        COMPRESSED_TYPE_MAPPING.put("application/x-rar-compressed", "rar");
        COMPRESSED_TYPE_MAPPING.put("application/x-7z-compressed", "7z");
        COMPRESSED_TYPE_MAPPING.put("application/zip", "zip");
        COMPRESSED_TYPE_MAPPING.put("application/gzip", "gzip");
    }

    private FileTypeUtil() {
    }

    /**
     * 获取文件类型
     * @param file 文件
     * @return
     * 1. 文本文件:
     *      ['txt', 'css', 'html', 'java', 'c', 'cpp', 'php', 'python', 'jsp', 'matlab', 'sql', 'markdown']
     * 2. 图片文件: 'image'
     * 3. 视频文件: 'video'
     * 4. 压缩文件: 'compressed'
     * 5. 应用文件:
     *      ['javascript', 'xml', 'java-vm', 'shell', 'pdf', 'apk', 'exe', 'chm', 'word', 'powerpoint', 'excel', 'other']
     * 6. 文件夹: 'dir'
     */
    static String getFileType(File file) {
        if (file.isDirectory()) {
            return "dir";
        }
        String mimeType = getMimeType(file);

        if (mimeType.startsWith("text")) {
            return TEXT_TYPE_MAPPING.get(mimeType);
        } else if (mimeType.startsWith("image")) {
            return "image";
        } else if (mimeType.startsWith("video")) {
            return "video";
        } else if (mimeType.startsWith("application")) {
            // 压缩文件单独处理
            if (COMPRESSED_TYPE_MAPPING.containsKey(mimeType)) {
                return COMPRESSED_TYPE_MAPPING.get(mimeType);
            }

            return APPLICATION_TYPE_MAPPING.get(mimeType);
        }

        return "other";
    }

    /**
     * 获取文件的Mime类型
     * @param file 文件对象
     * @return 文件Mime类型
     */
    private static String getMimeType(File file) {
        AutoDetectParser parser = new AutoDetectParser();
        parser.setParsers(new HashMap<MediaType, Parser>());

        Metadata metadata = new Metadata();
        metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());

        InputStream stream;
        try {
            stream = new FileInputStream(file);
            parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
            stream.close();
        } catch (TikaException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return metadata.get(HttpHeaders.CONTENT_TYPE);
    }
}