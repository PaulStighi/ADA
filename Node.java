public class Node { // Tree node model
    public int key;
    public int height, depth, weight;
    public Node left, right, p;

    public Node(int key) {
        this.key = key;
        this.height = 1;
    }

    /**
     * Used for printing nodes
     */
    public String toString() {
        return ("Key: " + key);
    }
}