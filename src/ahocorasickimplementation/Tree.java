package ahocorasickimplementation;

import java.util.LinkedList;
import tree.Node;

/**
 *
 * @author Yann
 */
public class Tree {

    // Method which addWord in the tree
    public static void addWord(String word, Node root) {
        if (word.length() != 0) {
            Node n = root.getChild(word.charAt(0));
            if (n == null) {
                Node child = new Node(word.charAt(0), root);
                if (word.length() == 1) {
                    child.setIsEnd(true);
                }
                root.addChild(child);
                addWord(word.substring(1), child);
            } else {
                addWord(word.substring(1), n);
            }
        }
    }

    // Display tree
    public static void displayTree(Node actualNode) {
        actualNode.toString(0);
    }
    
    // Add fall edges in the tree
    public static void addFallEdges(Node root){
        LinkedList<Node> queue = new LinkedList<Node>();
        // Set fall root on root
        root.setFall(root);
        // We put root in the queue
        queue.add(root);
        // While not empty queue
        while(queue.size() != 0){
            // Dequeue the first node
            Node dequeued = (Node) queue.poll();
            // If the dequeued node contains childs we add them in queue
            if(dequeued.getChilds() != null){
                for(Node child : dequeued.getChilds()){
                    queue.add(child);
                }
            }
            // If the dequeued node is not the root which already contain fall
            if(dequeued != root){
                // We get the fall of the parent's dequeued node
                Node fall = dequeued.getParentNode().getFall();
                // We move fall by fall to find the longest suffix
                // Or the root
                while(fall.isEnd() && fall != root){
                    fall = fall.getFall();
                }
                // If we found a suffix, we set it to the fall of the dequeued node
                dequeued.setFall(fall.getChild(dequeued.getValue()));
                
                // We test the fall's value of the dequeued node to check the integrity
                if(dequeued.getFall() == null){
                    dequeued.setFall(root);
                }
                if(dequeued.getFall() == dequeued){
                    dequeued.setFall(root);
                }
            }
        }
    }
    
    // Search in tree method
    public static void search(String searchedText, Node root){
        Node currentState = root;
        Node n, no;
        char currentValue;
        // Linear complexity so we pass the text length one time
        for(int i = 0; i < searchedText.length(); i++){
            n = currentState;
            currentValue = searchedText.charAt(i);
            // We search if current's node childs contain the 
            // current searched char or if we reached the root,
            // then we move on fall and loop
            while(n.getChild(currentValue) == null && n != root){
                n = n.getFall();
            }
            // At the end of the previous while, we are in the node which
            // contains the current searched char, or in the root
            // If we are in the root we search in his childs
            if(n == root){
                n = n.getChild(currentValue);
                // If n doesn't contain the current searched char
                // Then the word isn't in the tree, we place on root and
                // continue the rest of the String
                if(n == null){
                    n = root;
                }
            }
            // We are in the node which contains the current searched char
            else {
                n = n.getChild(currentValue);
            }
            
            no = n;
            // We check if we have found a dictionnary's occurence
            while(no != root){
                if(no.isEnd()){
                    System.out.println("Tu as trouvé le mot dans le texte!");
                }
                no = no.getFall();
            }
            // We change the currentState by the new one and we loop
            currentState = n;
        }        
    }
    
}
