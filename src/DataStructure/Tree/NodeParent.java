package DataStructure.Tree;

public class NodeParent<T extends Comparable<T>> implements Cloneable, Comparable<T> {
  private T data;

  private NodeParent<T> left;
  private NodeParent<T> right;

  private NodeParent<T> parent;

  public NodeParent(T data) {
    this(null, data, null, null);
  }

  public NodeParent(NodeParent<T> parent, T data) {
    this(parent, data, null, null);
  }

  public NodeParent(T data, NodeParent<T> left, NodeParent<T> right) {
    this(null, data, left, right);
  }

  public NodeParent(NodeParent<T> parent, T data, NodeParent<T> left, NodeParent<T> right) {
    if(data == null) throw new IllegalArgumentException("the data cannot be null");

    this.parent = parent;
    this.data = data;
    this.left = left;
    this.right = right;
  }

  protected NodeParent(NodeParent<T> model) {
    if(model == null) throw new IllegalArgumentException("NodeBilateral model is null");
    this.data = model.data;
    this.left = model.left;
    this.right = model.right;
  }

  public T getData() {
    return this.data;
  }

  public NodeParent<T> getParent() {
    return this.parent;
  }

  public NodeParent<T> getLeft() {
    return this.left;
  }
  public NodeParent<T> getRight() {
    return this.right;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setParent(NodeParent<T> parent) {
    this.parent = parent;
  }

  public void setLeft(NodeParent<T> left) {
    this.left = left;
  }
  public void setRight(NodeParent<T> right) {
    this.right = right;
  }
  
  @Override
  public int compareTo(T o) {
    return this.data.compareTo(o);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    try { return new NodeParent<T>(this); }
    catch (Exception e) { throw new CloneNotSupportedException(); }
  }
}
