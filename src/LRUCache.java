import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;


public class LRUCache {

    Deque<Integer> cache;
    int cachSize;
    HashSet<Integer> hashSet;

    public LRUCache(int cachSize) {

        this.cachSize = cachSize;
        cache = new LinkedList<>();
        hashSet = new HashSet<>(cachSize);
    }


    private void put(int num) {
        if (hashSet.contains(num)) {
            cache.removeFirstOccurrence(num);
        } else {
            if (cache.size() == cachSize) {
                cache.removeLast();
            }
        }
        cache.addFirst(num);
        hashSet.add(num);

    }

    private void print() {
        Iterator iterator = cache.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + "->");
        System.out.println();
    }


    public static void main(String[] args) {
        LRUCache lru = new LRUCache(4);
        lru.put(1);
        lru.put(2);
        lru.put(1);
        lru.put(3);
        lru.put(4);
        lru.print();
        lru.put(5);
        lru.print();
    }
}
