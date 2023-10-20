package DataStructure.LinkedList;

import DataStructure.Exceptions.LinkedListException;

public class Node<X> implements Cloneable {
  private X data;
  private Node<X> next;

  public Node(X data, Node<X> next) throws LinkedListException {
      if(data == null)
          throw new LinkedListException("the data cannot be null");
      this.data = data;
      this.next = next;
  }
  public Node(X data) throws LinkedListException { this(data, null); }

  public X getData() { return this.data; }
  public void setData(X data) throws LinkedListException {
      if(data == null)
          throw new LinkedListException("the data cannot be null");
      this.data = data;
  }
  public Node<X> getNext() { return this.next; }
  public void setNext(Node<X> data) { this.next = data; }

  @Override public Node<X> clone() throws CloneNotSupportedException {
      try { return new Node<X>(this.data, this.next); }
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
      Node<?> node = (Node<?>) obj;
      
      return this.data == node.data && this.next == node.next;
  }

  @Override
  public int hashCode() {
      int hash = 12;

      hash = 3 * hash + this.data.hashCode();
      hash = 5 * hash + this.next.hashCode();

      if(hash < 0) hash *= -1;

      return hash;
  }
}