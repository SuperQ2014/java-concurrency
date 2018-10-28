import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        Map<String, String> myMap = new ConcurrentHashMap<String, String>();
        myMap.put("key1", "value");
        myMap.put("key2", "value2");
        myMap.put("key3", "value3");
        myMap.get("key2");
        myMap.remove("key3");
        int size = myMap.size();
        System.out.println(size);
        myMap.clear();
        System.out.println();
    }
}
