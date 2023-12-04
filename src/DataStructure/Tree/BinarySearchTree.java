package DataStructure.Tree;

import DataStructure.ShallowOrDeepCopy;
import DataStructure.Exceptions.BinaryTreeException;

public class BinarySearchTree<T extends Comparable<T>> extends EssentialBinaryTree<T> {
    public BinarySearchTree() { super(); }
    
    public BinarySearchTree(T data) {
        super(data);
    }

    private BinarySearchTree(BinarySearchTree<T> model) { 
        if(model == null) throw new IllegalArgumentException("BinarySearchTree model is null");
        this.root = insertTree(model.root);
    }

    private Node<T> insertTree(Node<T> current) {
        if(current == null) return null;
        Node<T> node = new Node<T>(current.getData());
        node.setLeft(insertTree(current.getLeft()));
        node.setRight(insertTree(current.getRight()));
        return node;
    }

    public void insert(T data) {
        if(data == null) throw new IllegalArgumentException("the data cannot be null");

        if(root == null) {
            root = new Node<T>((T) ShallowOrDeepCopy.verifyAndCopy(data));
            return;
        }
        Node<T> current = root;
        
        while(true) {
            if(current.getData().compareTo(data) > 0) {
                if(current.getLeft() == null) {
                    current.setLeft(new Node<T>((T) ShallowOrDeepCopy.verifyAndCopy(data)));
                    return;
                }
                current = current.getLeft();
            } else {
                if(current.getRight() == null) {
                    current.setRight(new Node<T>((T) ShallowOrDeepCopy.verifyAndCopy(data)));
                    return;
                }
                current = current.getRight();
            }
        }
    }

    public T search(T data) {
        if(data == null) throw new IllegalArgumentException("the data cannot be null");
        if(root == null) return null;

        Node<T> current = root;

        while(true) {
            int compare = current.getData().compareTo(data);

            if(compare == 0) return current.getData();
            if(compare > 0) {
                if(current.getLeft() == null) return null;
                current = current.getLeft();
            }
            else {
                if(current.getRight() == null) return null;
                current = current.getRight();
            }
        }
    }

    public void remove(T data) throws BinaryTreeException {
        if(data == null) throw new IllegalArgumentException("the data cannot be null");
        if(root == null) throw new BinaryTreeException("the tree is empty");
        
        Node<T> previous = root;
        Node<T> current = root;

        while(true) {
            int compare = current.getData().compareTo(data);
            
            if(compare == 0) {
                if(previous.getLeft().getData().compareTo(data) == 0) {
                    if(current.getLeft() == null)
                        previous.setLeft(current.getRight());
                    else if(current.getRight() == null)
                        previous.setLeft(current.getLeft());
                    else {
                        previous.setLeft(current.getLeft());
                        insertSubTreeIn(current.getRight(), previous);
                    }
                }
                else if(previous.getRight().getData().compareTo(data) == 0) {
                    if(current.getLeft() == null)
                        previous.setRight(current.getRight());
                    else if(current.getRight() == null)
                        previous.setRight(current.getLeft());
                    else {
                        previous.setRight(current.getRight());
                        insertSubTreeIn(current.getLeft(), previous);
                    }
                }

                return;
            } else {
                previous = current;
                if(compare > 0) {
                    if(current.getLeft() == null) throw new BinaryTreeException("the data is not in the tree");
                    current = current.getLeft();
                } else {
                    if(current.getRight() == null) throw new BinaryTreeException("the data is not in the tree");
                    current = current.getRight();
                }
            }
        }
    }

    public void insertAll(Node<T> current) {
        if(current == null) return;

        if(current.getLeft() == null && current.getRight() == null) {
            insert(current.getData());
            return;
        }
        if(current.getLeft() == null && current.getRight() != null) {
            insert(current.getData());
            insertAll(current.getRight());
            return;
        }
        if(current.getLeft() != null && current.getRight() == null) {
            insert(current.getData());
            insertAll(current.getLeft());
            return;
        }

        insert(current.getData());
        insertAll(current.getLeft());
        insertAll(current.getRight());
    }

    public boolean exist(T data) {
        if(data == null) throw new IllegalArgumentException("the data cannot be null");
        if(root == null) return false;

        Node<T> current = root;
       while(true) {
            int compare = current.compareTo(data);
            if(compare == 0) return true;
            
            if(compare > 0) {
                if(current.getLeft() == null) return false;
                current = current.getLeft();
            }
            else {
                if(current.getRight() == null) return false;
                current = current.getRight();
            }
       }                                
    }

    @Override
    public BinarySearchTree<T> clone() throws CloneNotSupportedException {
        try {
            return new BinarySearchTree<T>(this);
        } catch (Exception e) {
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(!(obj instanceof BinarySearchTree)) return false;
        
        BinarySearchTree<T> tree = (BinarySearchTree<T>) obj;

        if(this.root == null && tree.root == null) return true;
        
        Node<T> currentThis = this.root;
        Node<T> currentTree = tree.root;
        return equals(currentThis, currentTree);
    }
}
