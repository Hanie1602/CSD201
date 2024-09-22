/* Class for a BST node containing an aquarium fish */
public class BST_Node {
    AquariumFish fish;  //Basic node structure in a BST
    BST_Node left, right;
    
    public BST_Node (AquariumFish fish) {
        this.fish = fish;
        left = right = null;
    }
}   //BST_Node class
