package DataStructure.LinkedList;

import DataStructure.Exceptions.LinkedListException;

public class NodeBilateral<X> implements Cloneable {
  private X data;
  private NodeBilateral<X> next;
  private NodeBilateral<X> previous;

  public NodeBilateral(X data, NodeBilateral<X> next, NodeBilateral<X> previous) throws LinkedListException {
      if(data == null)
          throw new LinkedListException("the data cannot be null");
      this.data = data;
      this.next = next;
      this.previous = previous;
  }
  public NodeBilateral(X data) throws LinkedListException { this(data, null, null); }

  public X getData() { return this.data; }
  public void setData(X data) throws LinkedListException {
      if(data == null)
          throw new LinkedListException("the data cannot be null");
      this.data = data;
  }
  public NodeBilateral<X> getNext() { return this.next; }
  public void setNext(NodeBilateral<X> data) { this.next = data; }

  public NodeBilateral<X> getPrevious() { return this.previous; }
  public void setPrevious(NodeBilateral<X> data) { this.previous = data; }

  @Override
    public NodeBilateral<X> clone() throws CloneNotSupportedException {
        try { return new NodeBilateral<X>(this.data, this.next, this.previous); }
        catch (LinkedListException e) { throw new CloneNotSupportedException(e.getMessage()); }
    }

  @Override
  public String toString() {
      StringBuilder builder = new StringBuilder();

      builder.append(this.data.toString());

      return builder.toString();
  }

  @Override
  public boolean equals(Object obj) {
      if(obj == null) return false;
      if(obj.getClass() != this.getClass()) return false;
      NodeBilateral<?> node = (NodeBilateral<?>) obj;
      
      return this.data == node.data && this.next == node.next;
  }

  @Override
  public int hashCode() {
      int hash = 12;

      hash = 3 * hash + this.data.hashCode();
      hash = 5 * hash + this.next.hashCode();
      hash = 7 * hash + this.previous.hashCode();
      
      if(hash < 0) hash *= -1;

      return hash;
  }
}