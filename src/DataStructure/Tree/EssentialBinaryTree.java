package DataStructure.Tree;

public class EssentialBinaryTree<T extends Comparable<T>> implements Cloneable {
  protected Node<T> root;

  public EssentialBinaryTree() { root = null; }

  public EssentialBinaryTree(T data) {
    if(data == null) throw new IllegalArgumentException("the data cannot be null");
    root = new Node<T>(data);
  }

  public boolean isEmpty() { return root == null; }

  public void clear() { root = null; }

  public int size() {
    if(root == null) return 0;
    return size(root);
  }

  private int size(Node<T> current) {
      if(current == null) return 0;
      if(current.getLeft() == null && current.getRight() == null) return 1;
      if(current.getLeft() == null && current.getRight() != null) return 1 + size(current.getRight());
      if(current.getLeft() != null && current.getRight() == null) return 1 + size(current.getLeft());

      return 1 + size(current.getLeft()) + size(current.getRight());
  }

  public boolean isMirror(EssentialBinaryTree<T> tree) {
    if(tree == null) throw new IllegalArgumentException("the tree cannot be null");
    if(tree.root == null || root == null) return false;

    return isMirror(root, tree.root);
  }

  private boolean isMirror(Node<T> current1, Node<T> current2) {
    if(current1 == null && current2 == null) return true;
    if(current1.getLeft() == null && current2.getRight() == null) return true;
    if(current1.getLeft() == null && current2.getRight() == null) return true;
    
    if(!current1.equals(current2)) return false;
    if(current1.getLeft() == null && current2.getRight() != null) return false;
    if(current1.getRight() == null && current2.getLeft() != null) return false;

    return isMirror(current1.getLeft(), current2.getRight()) && isMirror(current1.getRight(), current2.getLeft());
  }

  protected void insertSubTreeIn(Node<T> subTree, Node<T> root) {
    Node<T> current = root;
    while(true) {
        int compare = subTree.getData().compareTo(current.getData());

        if(compare > 0) {
            if(current.getLeft() == null) {
                current.setLeft(subTree);
                return;
            }
            current = current.getLeft();
        } else {
            if(current.getRight() == null) {
                current.setRight(subTree);
                return;
            }
            current = current.getRight();
        }
    }
}

  @Override
  public int hashCode() {
      if(root == null) return 0;
      int hash = hashCode(root, 10);
      if(hash < 0)
        return hash * -1;
      return hash;
  }

  private int hashCode(Node<T> current, int hash) {
      if(current == null) return 0;
      if(current.getLeft() == null && current.getRight() == null) return 3 * hash + current.getData().hashCode();
      if(current.getLeft() == null && current.getRight() != null) return (hash = 3 * hash + current.getData().hashCode()) + hashCode(current.getRight(), hash);
      if(current.getLeft() != null && current.getRight() == null) return (hash = 3 * hash + current.getData().hashCode()) + hashCode(current.getLeft(), hash);

      return (hash = 3 * hash + current.getData().hashCode()) + hashCode(current.getLeft(), hash) + hashCode(current.getRight(), hash);
  }

  @Override
  public String toString() {
      if(root == null) return "{ }";
      return getClass().getName()+"@"+size()+"..."+toString(1,root, "Root: ");
  }

  private String toString(int level, Node<T> current, String Prefix) {
    
    String spacer = "";
    for(int i = 0; i < level * 2; i++) spacer += " ";
    
    if(current == null) return  "\n"+spacer+Prefix+"[ ]";

    return "\n"+spacer+Prefix+"["+current.getData()+"]"+
      toString(level+1, current.getLeft(), "L--- ")+
      toString(level+1, current.getRight(), "R--- ");
  }

  protected boolean equals(Node<T> currentThis, Node<T> currentTree) {
    if(currentThis == null && currentTree != null) return false;
    if(currentThis != null && currentTree == null) return false;
    if(currentThis == null && currentTree == null) return true;

    if(currentThis.getData().compareTo(currentTree.getData()) != 0) return false;

    return equals(currentThis.getLeft(), currentTree.getLeft()) && equals(currentThis.getRight(), currentTree.getRight());
}
}
