import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeHashMap extends Thread {
    static ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("Child Thread updating Map");
        map.put(103, "C");
    }

    public static void main(String arg[])
            throws InterruptedException {
        map.put(101, "A");
        map.put(102, "B");

        ThreadSafeHashMap t = new ThreadSafeHashMap();

        t.start();

        Set<Integer> s1 = map.keySet();

        Iterator<Integer> itr = s1.iterator();

        while (itr.hasNext()) {
            Integer I1 = itr.next();
            System.out.println(
                    "Main Thread Iterating Map and Current Entry is:" + I1 + "..." + map.get(I1));
            Thread.sleep(3000);
        }
        System.out.println(map);
    }
}