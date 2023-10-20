package DataStructure.LinkedList;

import DataStructure.Exceptions.LinkedListException;

public class LinkedListBilateral<X> implements IEssentialLinkedList<X> {
  private NodeBilateral<X> first;
  private NodeBilateral<X> last;

  public LinkedListBilateral() { this.first = this.last = null; }
  protected LinkedListBilateral(LinkedListBilateral<X> linkedList) {
    for(NodeBilateral<X> current = linkedList.first; current != null; current = current.getNext())
      try { this.addIntoLast(current.getData()); }
      catch (LinkedListException e) { }
  }

  public void addIntoLast(X data) throws LinkedListException {
    if(data == null)
      throw new LinkedListException("the data cannot be null");

    if(this.last == null)
      this.first = this.last = new NodeBilateral<>(data);
    else {
    for(NodeBilateral<X> current = this.first; current != null; current = current.getNext())
      if(current.getNext() == null) {
        current.setNext(new NodeBilateral<>(data, null, current));
        this.last = current.getNext();
      }
    }
  }

  public void addIntoFirst(X data) throws LinkedListException {
    if(data == null)
      throw new LinkedListException("the data cannot be null");

    if(this.first == null)
      this.first = this.last = new NodeBilateral<>(data);
    else {
      this.first.setPrevious(new NodeBilateral<>(data, this.first, null));
      this.first = this.first.getPrevious();
    }
  }

  public X removeIntoLast() throws LinkedListException {
    if(this.last == null)
      throw new LinkedListException(null);

    X data = this.last.getData();
    this.last = this.last.getPrevious();
    this.last.setNext(null);
    return data;
  }

  public X removeIntoFirst() throws LinkedListException {
    if(this.first == null)
      throw new LinkedListException(null);

    X data = this.first.getData();
    this.first = this.first.getNext();
    this.first.setPrevious(null);
    return data;
  }  

  @Override
  public void clear() { this.first = this.last = null; }
  @Override
  public boolean isEmpty() { return this.first == null; }
  @Override
  public int getSize() {
    int size = 0;
    for(NodeBilateral<X> current = this.first; current != null; current = current.getNext())
      size++;
    return size;
  }
  @Override
  public X getLastElement() { return this.last.getData(); }
  @Override
  public X getFirstElement() { return this.first.getData(); }
  @Override
  public boolean isExist(X data) {
    for(NodeBilateral<X> current = this.first; current != null; current = current.getNext())
      if(current.getData().equals(data))
        return true;
    return false;
  }
  @Override
  public X removeInto(int index) throws LinkedListException {
    if(index == 0) return removeIntoFirst();
    int count = -1;
    for(NodeBilateral<X> current = this.first; current != null; current = current.getNext()) {
      if(current.getNext() == null) return removeIntoLast();
      if((++count) == index-1) {
        X data = current.getNext().getData();

        current.setNext(current.getNext().getNext());
        if(current.getNext() != null)
          current.getNext().setPrevious(current);

        return data;
      }
    }
    throw new LinkedListException("the index is not exist");
  }
  @Override
  public int getIndexOfElement(X data) throws LinkedListException {
    int count = -1;
    
    for(NodeBilateral<X> current = this.first; current != null; current = current.getNext()) {
      count++;
      if(current.getData().equals(data))
        return count;
    }

    throw new LinkedListException("the data is not exist");
  }
  @Override
  public X getElementAt(int index) throws LinkedListException {
    if(index < 0)
      throw new LinkedListException("the index is not exist");

    if(index == 0) return getFirstElement();
    int count = -1;
    for(NodeBilateral<X> current = this.first; current != null; current = current.getNext())
      if((++count) == index)
        return current.getData();
    throw new LinkedListException("the index is not exist");
  }
  @Override
  public Object[] toArray() {
    Object[] array = new Object[this.getSize()];
    int index = 0;
    for(NodeBilateral<X> current = this.first; current != null; current = current.getNext())
      array[index++] = current.getData();
    return array;
  }
}
