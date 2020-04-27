// Kimberley Trotz

import java.util.*; 

public class BTree {

    static class Node { //Base class -- inner class
        public ArrayList<?> nodes = new ArrayList<>();
        public ArrayList<Node> child;
        public Node currentRoot;

        public Node(ArrayList<?> nodes) {
            this.nodes = nodes;       
        }  
    }

    static class RootNode extends Node { //Non-Leaf nodes -- inner class
        public int min;
        public int max;
        
        public RootNode(int min, int max, ArrayList<Node> nodesList) {
            super(nodesList);
            this.min = min; 
            this.max = max;

            currentRoot = new Node(new ArrayList<>(Arrays.asList(min,max)));  
            currentRoot.child = nodesList;  
        }    
    }
                                                                                            
    static class LeafNode extends Node { //Leaf nodes -- inner class
        public LeafNode(ArrayList<Integer> values) {
            super(values);
        }      
    }

    //BTree class
    public static boolean search(Node top){
        int searchValue = 500; //The leaf node value the method is searching for      

        //Check if it's a LeafNode
        if(top.currentRoot.child.toString().contains("LeafNode")){ 
            if(top.currentRoot.child.size() == 1 && !top.currentRoot.child.get(0).nodes.contains(searchValue))
                return false;

            if(top.currentRoot.child.get(0).nodes.contains(searchValue) || top.currentRoot.child.get(1).nodes.contains(searchValue))
                return true;
        }

        //Check if it's a RootNode
        if(top.currentRoot instanceof Node || top.currentRoot instanceof RootNode){

            int currentMin = Integer.parseInt(top.currentRoot.nodes.get(0).toString());
            int currentMax = Integer.parseInt(top.currentRoot.nodes.get(1).toString());   
            
            //if search value is between those nodes move to next
            if(searchValue >= currentMin && searchValue <= currentMax){

                if(searchValue >= Integer.parseInt(top.currentRoot.child.get(0).currentRoot.nodes.get(0).toString()) && 
                    searchValue <= Integer.parseInt(top.currentRoot.child.get(0).currentRoot.nodes.get(1).toString())){

                        return search(top.currentRoot.child.get(0));   
                } 

                if(top.currentRoot.child.get(1) != null)
                    return search(top.currentRoot.child.get(1));  
            }      
        }   
       return false;
    }

    public static void main(String[] args){

        // RootNode 300 399 -----> LeafNode 300, 320, 340, 360
        ArrayList<Node> leaf3 = new ArrayList<>(Arrays.asList(new LeafNode(new ArrayList<>(Arrays.asList(300, 320, 340, 360)))));
        RootNode root3 = new RootNode(300, 399, leaf3);

        // RootNode 200 399 -----> RootNode 300, 399 & LeafNode 200, 220, 240, 260
        LeafNode leaf2 = new LeafNode(new ArrayList<>(Arrays.asList(200, 220, 240, 260)));
        ArrayList<Node> r3 = new ArrayList<>(Arrays.asList(root3, leaf2));
        RootNode root2 = new RootNode(200, 399, r3);

        // RootNode 100 199 -----> LeafNode 100, 120, 140, 160
        ArrayList<Node> leaf1 = new ArrayList<>(Arrays.asList(new LeafNode(new ArrayList<>(Arrays.asList(100, 120, 140, 160)))));
        RootNode root1 = new RootNode(100, 199, leaf1);

        // RootNode 1 1000 -----> RootNode 200, 399 & 100, 199
        ArrayList<Node> r2 = new ArrayList<>(Arrays.asList(root2, root1));
        RootNode top = new RootNode(1, 1000, r2);

        System.out.println(search(top));
    }
}


