class Node<T extends Comparable> {
    public T key;

    public Node left, right, p;

    public Node(T key) {
        this.key = key;
    }

    /**
     * Used for printing nodes
     */
    public String toString() {
        return ("Key: " + key);
    }
}


public class BST <T extends Comparable>{
    private Node<T> root;   // root of BST

    public BST() {
        root=null;
    }
    
    /**
     * Inserting nodes depending on their key
     * and the current structure of the BST
     */
    public void insert(T k) {
        Node x, y;
        Node z= new Node(k);
        y=null;
        x=root;
        while (x!=null) {
            y=x;
            if (x.key.compareTo(z.key)<0)
                x=x.right;
            else
                x=x.left;
        }
        z.p=y;
        if (y==null)
            root=z;
        else
            if (y.key.compareTo(z.key)<0)
                y.right=z;
            else
                y.left=z;

    }

    /**
     * Inorder BST traversal
     * Left, root, right
     */
    public void inorder(Node node) {
        if(node == null) return;

        this.inorder(node.left);
        System.out.print(node.key + " ");
        this.inorder(node.right);
    }

    /**
     * Preorder BST traversal
     * Root, left, right
     */
    public void preorder(Node node) {
        if(node == null) return;

        System.out.print(node.key + " ");
        this.preorder(node.left);
        this.preorder(node.right);
    }

    /**
     * Postorder BST traversal
     * Left, right, root
     */
    public void postorder(Node node) {
        if(node == null) return;

        this.postorder(node.left);
        this.postorder(node.right);
        System.out.print(node.key + " ");
    }

    /**
     * The height of the BST is the maximum
     * length of a path from the root node
     * to one of the leaves 
     */
    public int height(Node node) {
        if(node == null) return 0;
        else {
            int heightR = height(node.right);
            int heightL = height(node.left);

            return (Math.max(heightL,heightR) + 1);
        }
    }

    /**
     * Searching for a node with the given key
     * in the BST, return null if not found
     */
    public Node search(T key, Node node) {
        // base case, empty BST
        if(node == null)
            return null;

        // if the keys match, the node is found
        if(node.key.compareTo(key) == 0)
            return node;

        // if node key is greater than given key we need to search in the left subtree of node, otherwise in the right one
        if(node.key.compareTo(key) > 0)
            return search(key, node.left);
        else
            return search(key, node.right);
    }

    /**
     * Used for returning the leftmost
     * node from a subtree (with root node)
     * that also has the minimum key value
     * of that subtree
     */
    private Node minVal(Node node) {
        while(node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * Searching for the successor node of the given key
     */
    public Node successor(Node root, Node node, int key) {
        // empty tree, base case
        if (root == null) {
			return null;
        }
        
        // if the node with the given key was found and has a right child, then the succesor is that child's subtree minimum value
		if (root.key.compareTo(key) == 0 && root.right != null) {
				return this.minVal(root.right);
		}
        
        // same algorithm as the serching one, but also saving the node with greatest key value smaller then the given one
        if (root.key.compareTo(key) > 0)
		{
			node = root;
			return successor(root.left, node, key);
        }
        
        if (root.key.compareTo(key) < 0) {
			return successor(root.right, node, key);
        }
        
		return node;
	}

    public static void main(String[] args) {
        BST<Integer> st1=new BST<Integer>();
        st1.insert(5);
        st1.insert(2);
        st1.insert(10);
        st1.insert(8);
        st1.insert(15);

        System.out.println("\n>>>>>>>>>>>\nInorder:");
        st1.inorder(st1.root);                                                  // 2 5 8 10 15
        System.out.println("\n>>>>>>>>>>>\nPreorder:");
        st1.preorder(st1.root);                                                 // 5 2 10 8 15
        System.out.println("\n>>>>>>>>>>>\nPostorder:");
        st1.postorder(st1.root);                                                // 2 8 15 10 5
        System.out.println("\n>>>>>>>>>>>");
        System.out.println("Height: " + st1.height(st1.root));                  // Height: 3
        System.out.println("\n>>>>>>>>>>>");
        System.out.println(st1.search(Integer.valueOf(2),st1.root));                // Key: 2
        System.out.println(st1.search(Integer.valueOf(4),st1.root));                // null
        System.out.println("\n>>>>>>>>>>>");
        System.out.println(st1.successor(st1.root,null,Integer.valueOf(8)));        // Key: 10
        System.out.println(st1.successor(st1.root,null,Integer.valueOf(15)));       // null
        System.out.println("\n>>>>>>>>>>>");

        // BST<String> st2=new BST<String>();
        // st2.insert("dog");
        // st2.insert("bear");
        // st2.insert("cat");
        // st2.insert("fish");
        // st2.insert("wolf");
        // st2.inorder(st2.root);
    }
}