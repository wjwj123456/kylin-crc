import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 16-9-23.
 */
public class JsonTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("123");
        list.add("123");
        list.add("123");
        list.add("12234");

        JSONArray array = new JSONArray(list);
        System.out.println(array.toString());

    }
}
