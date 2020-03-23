import java.util.*;

public class BST {
    private Node root;   // root of BST

    public BST() {
        root=null;
    }
    
    /**
     * Inserting nodes depending on their key
     * and the current structure of the BST
     */
    public void insert(int k) {
        Node x, y;
        Node z= new Node(k);
        y=null;
        x=root;
        while (x!=null) {
            y=x;
            if (x.key < z.key)
                x=x.right;
            else
                x=x.left;
        }
        z.p=y;
        if (y==null)
            root=z;
        else
            if (y.key < z.key)
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

    public int weightPrecalc(Node node) {
        if(node == null)    return 0;
        else {
            int weightR = weightPrecalc(node.right);
            int weightL = weightPrecalc(node.left);

            node.weight = weightL + weightR + 1;

            return node.weight;
        }
    }

    /**
     * Searching for a node with the given key
     * in the BST, return null if not found
     */
    public Node search(int key, Node node) {
        // base case, empty BST
        if(node == null)
            return null;

        // if the keys match, the node is found
        if(node.key == key)
            return node;

        // if node key is greater than given key we need to search in the left subtree of node, otherwise in the right one
        if(node.key > key)
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
		if (root.key == key && root.right != null) {
				return this.minVal(root.right);
		}
        
        // same algorithm as the serching one, but also saving the node with greatest key value smaller then the given one
        if (root.key > key)
		{
			node = root;
			return successor(root.left, node, key);
        }
        
        if (root.key < key) {
			return successor(root.right, node, key);
        }
        
		return node;
    }
    
    public boolean isPerfectlyBalanced(Node node) {
        if(node.right == null && node.left == null) return true;
        else {
            boolean balL = (node.left != null) ? isPerfectlyBalanced(node.left) : true;
            boolean balR = (node.right != null) ? isPerfectlyBalanced(node.right) : true;
            
            int balance = 0;
            balance += (node.left != null) ? node.left.weight : 0;
            balance -= (node.right != null) ? node.right.weight : 0;
            boolean ok = (balance >= -1 && balance <= 1) ? true : false;

            return (balL && balR && ok);
        }
    }

    public Node searchClosest(int k, Node node, int min_dif) {
        if(node == null)    return null;
        if(k == node.key)   return node;

        Node closest = null;

        if(Math.abs(node.key - k) < min_dif) {
            min_dif = Math.abs(node.key - k);
            closest = node;
        }

        if(k < node.key) {
            Node nL = searchClosest(k, node.left, min_dif);
            if((nL != null) && Math.abs(nL.key - k) < min_dif) {
                min_dif = Math.abs(nL.key - k);
                closest = nL;
            }
        }
        else {
            Node nR = searchClosest(k, node.right, min_dif);
            if((nR != null) && Math.abs(nR.key - k) < min_dif) {
                min_dif = Math.abs(nR.key - k);
                closest = nR;
            }
        }

        return closest;
    }

    public boolean checkExistTwoNodesWithSum(int sum, Node node, HashSet<Integer> s) {
        if(node == null)    return false;
        
        int dif = sum - node.key;
        boolean ok = false;
        
        if(s.contains(dif)) {
            System.out.println("Pair with given sum " + sum + " is (" + node.key + ", " + dif + ")");
            ok = true;
        }

        s.add(node.key);

        ok |= checkExistTwoNodesWithSum(sum, node.left, s);
        ok |= checkExistTwoNodesWithSum(sum, node.right, s);

        return ok;
    }

    public static void main(String[] args) {
        BST st1 = new BST();
        HashSet<Integer> s = new HashSet<Integer>(); 
        st1.insert(5);
        st1.insert(2);
        st1.insert(10);
        st1.insert(8);
        st1.insert(15);

        /**
         *   5
         *  / \
         * 2   10
         *    /  \
         *   8    15
         */

        // System.out.println("\n>>>>>>>>>>>\nInorder:");
        // st1.inorder(st1.root);                                                  // 2 5 8 10 15
        // System.out.println("\n>>>>>>>>>>>\nPreorder:");
        // st1.preorder(st1.root);                                                 // 5 2 10 8 15
        // System.out.println("\n>>>>>>>>>>>\nPostorder:");
        // st1.postorder(st1.root);                                                // 2 8 15 10 5
        // System.out.println("\n>>>>>>>>>>>");
        // System.out.println("Height: " + st1.height(st1.root));                  // Height: 3
        // System.out.println("\n>>>>>>>>>>>");
        // System.out.println(st1.search(Integer.valueOf(2),st1.root));                // Key: 2
        // System.out.println(st1.search(Integer.valueOf(4),st1.root));                // null
        // System.out.println("\n>>>>>>>>>>>");
        // System.out.println(st1.successor(st1.root,null,Integer.valueOf(8)));        // Key: 10
        // System.out.println(st1.successor(st1.root,null,Integer.valueOf(15)));       // null
        // System.out.println("\n>>>>>>>>>>>");

        st1.weightPrecalc(st1.root);
        System.out.println(st1.isPerfectlyBalanced(st1.root));
        System.out.println(st1.searchClosest(16, st1.root, Integer.MAX_VALUE));
        System.out.println(st1.checkExistTwoNodesWithSum(12, st1.root, s));
    }
}