import DataStructure.Tree.BinarySearchTree;

public class App {
    public static void main(String[] args) {
        try {
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(10);
            tree.insert(6);
            tree.insert(18);
            tree.insert(17);
            tree.insert(19);
            tree.insert(8);
            tree.insert(9);
            tree.insert(6);
            tree.insert(5);
            tree.insert(7);

            System.out.println(tree.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
