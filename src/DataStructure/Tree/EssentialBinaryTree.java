package DataStructure.Tree;

public class EssentialBinaryTree<T extends Comparable<T>> implements Cloneable {
  protected NodeBilateral<T> root;

  public EssentialBinaryTree() { root = null; }

  public EssentialBinaryTree(T data) {
    if(data == null) throw new IllegalArgumentException("the data cannot be null");
    root = new NodeBilateral<T>(data);
  }

  public boolean isEmpty() { return root == null; }

  public void clear() { root = null; }

  public int size() {
    if(root == null) return 0;
    return size(root);
  }

  private int size(NodeBilateral<T> current) {
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

  private boolean isMirror(NodeBilateral<T> current1, NodeBilateral<T> current2) {
    if(current1 == null && current2 == null) return true;
    if(current1.getLeft() == null && current2.getRight() == null) return true;
    if(current1.getLeft() == null && current2.getRight() == null) return true;
    
    if(!current1.equals(current2)) return false;
    if(current1.getLeft() == null && current2.getRight() != null) return false;
    if(current1.getRight() == null && current2.getLeft() != null) return false;

    return isMirror(current1.getLeft(), current2.getRight()) && isMirror(current1.getRight(), current2.getLeft());
  }

  protected void insertSubTreeIn(NodeBilateral<T> subTree, NodeBilateral<T> root) {
    NodeBilateral<T> current = root;
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
      return hashCode(root);
  }
  private int hashCode(NodeBilateral<T> current) {
      if(current == null) return 2;
      if(current.getLeft() == null && current.getRight() == null) return current.hashCode();
      return 3 * hashCode(current.getLeft()) + hashCode(current.getRight()) + current.getData().hashCode();
  }

  @Override
  public String toString() {
      if(root == null) return "{ }";
      return getClass().getName()+"@"+size()+"..."+toString(1,root, "Root: ");
  }

  private String toString(int level, NodeBilateral<T> current, String Prefix) {
    
    String spacer = "";
    for(int i = 0; i < level * 2; i++) spacer += " ";
    
    if(current == null) return  "\n"+spacer+Prefix+"[ ]";

    return "\n"+spacer+Prefix+"["+current.getData()+"]"+
      toString(level+1, current.getLeft(), "L--- ")+
      toString(level+1, current.getRight(), "R--- ");
  }

  protected boolean equals(NodeBilateral<T> currentThis, NodeBilateral<T> currentTree) {
    if(currentThis == null && currentTree != null) return false;
    if(currentThis != null && currentTree == null) return false;
    if(currentThis == null && currentTree == null) return true;

    if(currentThis.getData().compareTo(currentTree.getData()) != 0) return false;

    return equals(currentThis.getLeft(), currentTree.getLeft()) && equals(currentThis.getRight(), currentTree.getRight());
}
}
