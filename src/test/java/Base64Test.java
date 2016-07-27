import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created by song on 16-7-27.
 */
public class Base64Test {

    private String imageURL = "/home/song/program/crc/data/test.png";
    @Test
    public void testBase64Encoder(){
        BASE64Encoder encoder = new BASE64Encoder();

        try {
            StringBuilder pictureBuffer = new StringBuilder();
            InputStream input = new FileInputStream(new File(imageURL));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            for(int len = input.read(temp); len != -1;len = input.read(temp)){
                out.write(temp, 0, len);
                pictureBuffer.append(encoder.encode(out.toByteArray()));
                //out(pictureBuffer.toString());
                out.reset();
            }

            out(pictureBuffer.toString());
            out("Encoding the picture Success");

            BASE64Decoder decoder = new BASE64Decoder();
            FileOutputStream write = new FileOutputStream(new File("/home/song/program/crc/data/test_1.jpg"));
            byte[] decoderBytes = decoder.decodeBuffer(pictureBuffer.toString());
            write.write(decoderBytes);
            out("Decoding the picture Success");

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void out(Object o){
        System.out.println(o.toString());
    }
}
