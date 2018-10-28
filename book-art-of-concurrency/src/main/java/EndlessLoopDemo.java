import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EndlessLoopDemo {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");

        t.start();
        t.join();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.print("key = " + entry.getKey() + "    ");
//            System.out.println("value = " + entry.getValue());
//        }
    }
}
