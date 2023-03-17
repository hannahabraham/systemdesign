import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private TreeMap<Integer, String> ring = new TreeMap<>();
    private final int numberOfReplicas;

    public ConsistentHashing(int numberOfReplicas, List<String> nodes) {
        this.numberOfReplicas = numberOfReplicas;
        for (String node : nodes) {
            addNodeToRing(node);
        }
    }

    private void addNodeToRing(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            String virtualNode = node + ":" + i;
            int hash = virtualNode.hashCode();
            ring.put(hash, node);
        }
    }

    private void removeNodeFromRing(String node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            String virtualNode = node + ":" + i;
            int hash = virtualNode.hashCode();
            ring.remove(hash);
        }
    }

    private String getNode(String key) {
        if (ring.isEmpty())
            return null;
        int hash = key.hashCode();
        if (!ring.containsKey(hash)) {
            SortedMap<Integer, String> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }
        return ring.get(hash);
    }

    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("N1", "N2", "N3", "N4", "N5", "N6", "N7", "N8", "N9");
        ConsistentHashing consistentHashing = new ConsistentHashing(4, nodes);
        System.out.println(consistentHashing.getNode("First"));
        System.out.println(consistentHashing.getNode("Second"));
        System.out.println(consistentHashing.getNode("Second123"));
        System.out.println(consistentHashing.getNode("First123"));
        System.out.println(consistentHashing.getNode("Five"));
        consistentHashing.addNodeToRing("N11");
        System.out.println(consistentHashing.getNode("First"));
        System.out.println(consistentHashing.getNode("Second"));
    }
}
