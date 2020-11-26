import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        for (Map.Entry<Object, Object> set : hashMap.entrySet()) {

            set.getKey();
            set.getValue();

        }
    }

}
