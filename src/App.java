import java.util.Random;

import DataStructure.Tree.BinarySearchTree;

public class App {
    public static void main(String[] args) {
        try {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            System.out.println(tree.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
