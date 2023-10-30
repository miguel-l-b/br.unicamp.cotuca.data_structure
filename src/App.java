import java.util.Random;

import DataStructure.Tree.BinarySearchTree;

public class App {
    public static void main(String[] args) {
        try {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(10);
            
            tree.insert(6);
            tree.insert(7);
            tree.insert(3);
            tree.insert(4);
            tree.insert(5);
            tree.insert(2);
            tree.insert(1);
            tree.insert(9);
            tree.insert(8);

            System.out.println(tree);
            tree.remove(7);
            System.out.println("===============");
            System.out.println(tree);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
