package DataStructure.Tree;

import DataStructure.Exceptions.BinaryTreeException;

public class BinarySearchTree<T extends Comparable<T>> implements Cloneable {

    private NodeBilateral<T> root;

    public BinarySearchTree() { root = null; }

    public BinarySearchTree(T data) throws BinaryTreeException {
        insert(data);
    }

    protected BinarySearchTree(BinarySearchTree<T> model) throws BinaryTreeException {
        if(model == null) throw new BinaryTreeException("BinarySearchTree model is null");
        this.root = model.root;
    }

    public void insert(T data) throws BinaryTreeException {
        if(data == null) throw new BinaryTreeException("the data cannot be null");

        if(root == null) {
            root = new NodeBilateral<T>(data);
            return;
        }
        NodeBilateral<T> current = root;
        
        while(true) {
            if(current.compareTo(data) < 0) {
                if(current.getLeft() == null) {
                    current.setLeft(new NodeBilateral<T>(data));
                    return;
                }
                current = current.getLeft();
            } else {
                if(current.getRight() == null) {
                    current.setRight(new NodeBilateral<T>(data));
                    return;
                }
                current = current.getRight();
            }
        }
    }

    public boolean exist(T data) throws BinaryTreeException {
        if(data == null) throw new BinaryTreeException("the data cannot be null");
        if(root == null) return false;

        NodeBilateral<T> current = root;
       while(true) {
            int compare = current.compareTo(data);
            if(compare == 0) return true;
            
            if(compare < 0) {
                if(current.getLeft() == null) return false;
                current = current.getLeft();
            }
            else {
                if(current.getRight() == null) return false;
                current = current.getRight();
            }
       }                                
    }

    public int size() {
        if(root == null) return 0;
        return size(root);
    }
    private int size(NodeBilateral<T> current) {
        if(current == null) return 0;
        if(current.getLeft() == null && current.getRight() == null) return 1;
        return 1 + size(current.getLeft()) + size(current.getRight());
    }

    @Override
    public int hashCode() {
        if(root == null) return 0;
        return hashCode(root);
    }
    private int hashCode(NodeBilateral<T> current) {
        if(current == null) return 2;
        if(current.getLeft() == null && current.getRight() == null) return current.hashCode();
        return 3 * hashCode(current.getLeft()) + hashCode(current.getRight()) + current.getData().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(!(obj instanceof BinarySearchTree)) return false;
        
        BinarySearchTree<T> tree = (BinarySearchTree<T>) obj;

        if(this.root == null && tree.root == null) return true;
        
        NodeBilateral<T> currentThis = this.root;
        NodeBilateral<T> currentTree = tree.root;
        return equals(currentThis, currentTree);
    }
    private boolean equals(NodeBilateral<T> currentThis, NodeBilateral<T> currentTree) {
        if(currentThis == null && currentTree != null) return false;
        if(currentThis != null && currentTree == null) return false;
        if(currentThis == null && currentTree == null) return true;

        if(currentThis.getData().compareTo(currentTree.getData()) != 0) return false;

        return equals(currentThis.getLeft(), currentTree.getRight()) && equals(currentThis.getRight(), currentTree.getRight());
    }

    @Override
    public String toString() {
        if(root == null) return "{ }";
        return root.toString();
    }
}
