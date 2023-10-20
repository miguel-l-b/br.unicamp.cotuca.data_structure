package DataStructure.Tree;

import DataStructure.Exceptions.BinaryTreeException;

public class NodeBilateral<T extends Comparable<T>> implements Cloneable, Comparable<T> {
    private T data;

    private NodeBilateral<T> left;
    private NodeBilateral<T> right;

    public NodeBilateral(T data) throws BinaryTreeException {
        if(data == null) throw new BinaryTreeException("the data cannot be null");
        this.data = data;
        left = null;
        right = null;
    }

    public NodeBilateral(NodeBilateral<T> left, T data, NodeBilateral<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    protected NodeBilateral(NodeBilateral<T> model) throws BinaryTreeException {
        if(model == null) throw new BinaryTreeException("NodeBilateral model is null");
        this.data = model.data;
        this.left = model.left;
        this.right = model.right;
    }

    @Override
    public int compareTo(T data) {
        return this.data.compareTo(data);
    }

    public T getData() { return this.data; }
    public NodeBilateral<T> getLeft() { return left; }
    public NodeBilateral<T> getRight() { return right; }
    
    public void setData(T data) { this.data = data; }
    public void setLeft(NodeBilateral<T> left) { this.left = left; }
    public void setRight(NodeBilateral<T> right) { this.right = right; }



    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;
        if(obj.getClass() != obj.getClass()) return false;

        if(!this.data.equals(((NodeBilateral<?>) obj).data)) return false;
        if(!this.left.equals(((NodeBilateral<?>) obj).left)) return false;
        if(!this.right.equals(((NodeBilateral<?>) obj).right)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 23;

        hash = 3 * hash + this.data.hashCode();
        hash = 7 * hash + this.left.hashCode();
        hash = 11 * hash + this.right.hashCode();

        if(hash < 0) hash *= -1;

        return hash;
    }

    @Override
    public String toString() {
        return String.format("{ %s } L { %s } R { %s }", this.left, this.data, this.right);
    }
}