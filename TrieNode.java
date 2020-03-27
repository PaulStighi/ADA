import java.util.ArrayList;

public class TrieNode {
    private static final int SIZE = 26;                                     // length of the alphabet
    public ArrayList<TrieNode> children = new ArrayList<TrieNode>(SIZE);    // an array for linking the nodes that continue the word
    public boolean isLastChar;                                              // if the node represents the end of a word

    public TrieNode() {                                                     // initialisation of the TrieNode
        this.isLastChar = false;
        for(int i = 0 ; i < SIZE ; ++i) {
            this.children.add(null);
        }
    }
}