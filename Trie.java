public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Given the argument for inserting, its length
     * will determine the depth of the lowest node in 
     * its representation. If the node for the needed
     * path is not yet used in any other word, create
     * it, then continue with it the path. When the
     * end of the word is hit, mark the final node 
     * as being the end of the word path
     * @param arg
     * @param root
     */
    public void insert(String arg, TrieNode root) {
        int depth = 0;

        while(depth < arg.length()) {
            int ind = arg.charAt(depth) - 'a';

            if(root.children.get(ind) == null) {
                root.children.set(ind, new TrieNode());
            }

            root = root.children.get(ind);

            depth++;
        }

        root.isLastChar = true;
    }

    /**
     * Visit the entire list of nodes in the tree
     * in a preorder traversal fashion, and an end
     * of word is found, display it
     * @param node
     * @param res
     */
    public void preorder(TrieNode node, String res) {
        if(node.isLastChar == true) {
            res += "\0";
            System.out.println(res);
        }


        for(int i = 0 ; i < node.children.size() ; ++i) {
            if(node.children.get(i) != null) {
                res += (char)(i + 97);

                preorder(node.children.get(i), res);

                if(res.length() > 0) 
                    res = res.substring(0, res.length() - 1);
            }
        }

    }
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("watch", trie.root);
        trie.insert("water", trie.root);
        trie.insert("war", trie.root);
        trie.insert("winter", trie.root);
        trie.insert("winner", trie.root);
        trie.insert("bar", trie.root);
        trie.insert("bank", trie.root);
        trie.insert("ball", trie.root);
        trie.insert("border", trie.root);

        trie.preorder(trie.root, "");
    }
}