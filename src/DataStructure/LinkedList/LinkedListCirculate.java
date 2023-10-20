package DataStructure.LinkedList;

import DataStructure.ShallowOrDeepCopy;
import DataStructure.Exceptions.LinkedListException;

public class LinkedListCirculate<X> implements IEssentialLinkedList<X> {
  private Node<X> first;
  private Node<X> last;

  public LinkedListCirculate() {
    first = last = null;
  }

  @Override
  public void clear() {
    this.first = this.last = null;
  }

  @Override
  public X getElementAt(int index) throws LinkedListException {
    if(index < 0)
      throw new LinkedListException("Index out of range");
    if(this.isEmpty())
      return null;

    if(index == 0)
      return this.getFirstElement();

    int i = 1;
    for(Node<X> current = this.first.getNext(); current != first; current = current.getNext()) {
      if(i == index)
        return current.getData();
      i++;
    }

    throw new LinkedListException("Index out of range");
  }

  @Override
  public X getFirstElement() {
    if(first == null)
      return null;
    return first.getData();
  }

  @Override
  public int getIndexOfElement(X data) throws LinkedListException {
    if(this.isEmpty())
      return -1;

    if(this.first.getData().equals(data))
      return 0;

    int i = 1;
    for(Node<X> current = this.first.getNext(); current != first; current = current.getNext()) {
      if(current.getData().equals(data))
        return i;
      i++;
    }

    return -1;
  }

  @Override
  public X getLastElement() {
    if(this.isEmpty())
      return null;
    return this.last.getData();
  }

  @Override
  public int getSize() {
    if(this.isEmpty())
      return 0;

    if(this.first.equals(this.last))
      return 1;

    int count = 2;
    for(Node<X> current = this.first.getNext(); current != this.first; current = current.getNext())
      count++;

    return count;
  }

  @Override
  public boolean isEmpty() {
    return this.first == null;
  }

  @Override
  public boolean isExist(X data) {
    if(isEmpty())
      return false;

    if(this.first.getData().equals(data))
      return true;

    for(Node<X> current = this.first.getNext(); current != first; current = current.getNext()) {
      if(current.getData().equals(data))
        return true;
    }

    return false;
  }

  public X removeFirstElement() {
    if(this.isEmpty())
      return null;

    X data = this.first.getData();
    if(this.first == this.last)
      this.first = this.last = null;
    else {
      this.first = this.first.getNext();
      this.last.setNext(this.first);
    }
    return data;
  }

  public X removeLastElement() {
    if(this.isEmpty())
      return null;

    X data = this.last.getData();
    if(this.first == this.last)
      this.first = this.last = null;
    else {
      Node<X> current = this.first;
      while(current.getNext() != this.last)
        current = current.getNext();
      this.last = current;
      this.last.setNext(this.first);
    }
    return data;      
  }

  @Override
  public X removeInto(int index) throws LinkedListException {
    if(index < 0)
      throw new LinkedListException("Index out of range");

    if(this.isEmpty())
      throw new LinkedListException("List is empty");

    if(index == 0)
      return this.removeFirstElement();

    int i = 2;
    for(Node<X> current = this.first.getNext(); current != first; current = current.getNext()) {
      if(i == index) {
        X data = current.getData();
        current.setNext(current.getNext().getNext());
        return data;
      }
      i++;
    }

    throw new LinkedListException("Index out of range");
  }

  @Override
  public Object[] toArray() {
    if(this.isEmpty())
      return new Object[] { };
    int size = this.getSize();
    if(size == 0)
      return new Object[] { };

    Object[] array = new Object[size];
    int count = 0;
    array[count] = ShallowOrDeepCopy.verifyAndCopy(this.first.getData());
    for(Node<X> current = this.first.getNext(); current != first; current = current.getNext())
      array[++count] = ShallowOrDeepCopy.verifyAndCopy(current.getData());
    
    return array;
  }

}