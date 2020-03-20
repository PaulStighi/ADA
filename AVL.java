public class Node { // Tree node model
    public int key;
    public int height;
    public Node left, right;

    public Node(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVL {
    private Node root;   // root of AVL

    public AVL() {
        root = null;
    }

    /**
     * Getter for the Node parameter's height in the tree
     * @param n
     * @return
     */
    public int height(Node n) {
        if(n == null)
            return 0;

        return n.height;
    }

    /**
     * For a Node n, return the balance coefficient 
     * (difference of height between the children subtrees)
     * @param n
     * @return
     */
    public int balance(Node n) {
        if(n == null)
            return 0;

        return (height(n.left) - height(n.right));
    }

    public Node leftRotate(Node x) {
        Node y = x.right;
        Node z = y.left;

        y.left = x;
        x.right = z;

        /**
         * x          y
         *  \        /
         *   y  =>  x
         *  /        \
         * z          z
         */

        // redetermine x and y height (bottom - top)
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // x => y
    }

    public Node rightRotate(Node y) {
        Node x = y.left;
        Node z = x.right;

        x.right = y;
        y.left = z;

        /**
         *   y      x  
         *  /        \ 
         * x    =>    y  
         *  \        / 
         *   z      z  
         */

        // redetermine x and y height (bottom top)
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // y => x
    }

    public Node insert(Node n, int k) {
        // if we reached a null position, that's where we place the new key
        if(n == null) {
            return (new Node(k));
        }

        // insert value less then crt key => left, => right otherwisw
        if(n.key > k) {
            n.left = insert(n.left, k);
        }
        else {
            n.right = insert(n.right, k);
        }

        // redetermine height for Node n
        n.height = Math.max(height(n.right), height(n.left)) + 1;

        int bal = balance(n);

        // if the balance is outside the [-1,1] interval => unbalanced (4 cases)
        // Right Right
        if(bal > 1 && n.left.key > k) {
            return rightRotate(n);
        }

        // Left Left
        if(bal < -1 && n.right.key < k) {
            return leftRotate(n);
        }

        // Left Right
        if(bal > 1 && n.left.key < k) {
            n.left = leftRotate(n.left);
            return rightRotate(n);
        }

        // Right Left
        if(bal < -1 && n.right.key > k) {
            n.right = rightRotate(n.right);
            return leftRotate(n);
        }

        return n;
    }

    /**
     * Inorder AVL traversal
     * Left, root, right
     */
    public void inorder(Node node) {
        if(node == null) return;

        this.inorder(node.left);
        System.out.print(node.key + " ");
        this.inorder(node.right);
    }

    /**
     * Preorder AVL traversal
     * Root, left, right
     */
    public void preorder(Node node) {
        if(node == null) return;

        System.out.print(node.key + " ");
        this.preorder(node.left);
        this.preorder(node.right);
    }

    /**
     * Postorder AVL traversal
     * Left, right, root
     */
    public void postorder(Node node) {
        if(node == null) return;

        this.postorder(node.left);
        this.postorder(node.right);
        System.out.print(node.key + " ");
    }

    public static void main(String[] args) {
        AVL tree = new AVL();
        // 4, 23, 11, 89, 34, 2, 7, 14, 75, 69, 99, 80
        tree.root = tree.insert(tree.root, Integer.valueOf(4));
        tree.root = tree.insert(tree.root, Integer.valueOf(23));
        tree.root = tree.insert(tree.root, Integer.valueOf(11));
        tree.root = tree.insert(tree.root, Integer.valueOf(89));
        tree.root = tree.insert(tree.root, Integer.valueOf(34));
        tree.root = tree.insert(tree.root, Integer.valueOf(2));
        tree.root = tree.insert(tree.root, Integer.valueOf(7));
        tree.root = tree.insert(tree.root, Integer.valueOf(14));
        tree.root = tree.insert(tree.root, Integer.valueOf(75));
        tree.root = tree.insert(tree.root, Integer.valueOf(69));
        tree.root = tree.insert(tree.root, Integer.valueOf(99));
        tree.root = tree.insert(tree.root, Integer.valueOf(80));

        System.out.println("\n******\nInorder:");
        tree.inorder(tree.root);                    // 2 4 7 11 14 23 34 69 75 80 89 99 
        System.out.println("\n******\nPreorder:");
        tree.preorder(tree.root);                   // 34 11 4 2 7 23 14 75 69 89 80 99
        
        /**
         * =====>>>>>
         *             34       
         *         /         \
         *       11           75
         *     /    \       /    \
         *   4       23   69      89
         *  / \     /            /  \ 
         * 2   7  14           80    99
         */
    }
}