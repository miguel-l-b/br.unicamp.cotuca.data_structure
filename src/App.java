import java.util.Random;

import DataStructure.Tree.BinarySearchTree;

public class App {
    public static void main(String[] args) {
        try {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>();
            Random randomGenerate = new Random();

            System.out.println("incluindo...");
            for(int i = 0; i < 100000; i++) tree.insert(randomGenerate.nextInt());

            System.out.println("incluindo!");
            long init = System.currentTimeMillis();
            System.out.println(tree);
            System.out.println("ToString() demorou: "+(System.currentTimeMillis()-init)+"ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
