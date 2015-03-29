package ahocorasickimplementation;

import tree.Node;

/**
 *
 * @author Yann
 */
public class AhoCorasickImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Example Aho Corasick string matching algorithm Wikipédia
        Node root = new Node('_');
        Tree.addWord("a", root);
        Tree.addWord("ab", root);
        Tree.addWord("bab", root);
        Tree.addWord("bc", root);
        Tree.addWord("bca", root);
        Tree.addWord("c", root);
        Tree.addWord("caa", root);
        Tree.addFallEdges(root);
        Tree.search("caa", root);
        Tree.displayTree(root);
    }
}
