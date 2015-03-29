package tree;

import java.util.ArrayList;


/**
*
* @author Yann
*
*/

/*
* Node
* It has a value which is only one character
* It has zero, one or more childs
*/
public class Node {
    // The parent node
    private Node parentNode;
    // Fallback
    private Node fall;
    // The value = one character
    private char value;
    // The node's tab
    private ArrayList<Node> childs;
     // Whether there is the end of word
    private boolean isEnd;
    
    // Getters and setters
    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
    
    public ArrayList<Node> getChilds() {
        return childs;
    }
        
    public Node getParentNode() {
        return parentNode;
    }
    
    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }
    
    public void setFall(Node n){
        this.fall = n;
    }
    
    public Node getFall(){
        return fall;
    }
    public boolean isEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
    
    // Constructors
    public Node(char value){
        this.value = value;
        this.childs = null;
        this.isEnd = false;
        this.parentNode = null;
        this.fall = null;
    }
    
    public Node(char value, Node parent) {
        this.value = value;
        this.childs = null;
        this.isEnd = false;
        this.parentNode = parent;
        this.fall = null;
    }
    
    // Add a child
    public void addChild(Node child) {
        if(childs == null){
            childs = new ArrayList<Node>();
            
        } 
        childs.add(child);
    }
    
    // Search recursively a word in the tree
    public boolean contains(String word) {
        if(word.length() == 0){
            return isEnd;
        } 
        Node potentialChild = this.getChild(word.charAt(0));
        if(potentialChild != null){
            return potentialChild.contains(word.substring(1));
        } else {
            return false;
        }
    }

    // Return the child which contains the searching char
    public Node getChild(char c){
        if(childs != null){
            for(Node child : childs){
                if(child.value == c){
                    return child;
                }
            }
        }
        return null;
    } 
        
    public void toString(int nbTab){
        for(int i = 0; i < nbTab; i++){
            System.out.print(" ");
        }
        String fallString = "";
        if(fall != null){
            fallString += fall.getValue();
        }
        System.out.println(value + " FALL : " + fallString + " isEnd : " + isEnd);
        if(childs != null){        
            for(Node n : childs){
                n.toString(nbTab + 1);
            }
        }
    }
}


