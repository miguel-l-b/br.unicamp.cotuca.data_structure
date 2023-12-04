package DataStructure.LinkedList;

import DataStructure.Exceptions.LinkedListException;

public interface IEssentialLinkedList<X> {
  void clear();
  boolean isEmpty();
  int getSize();
  X getLastElement();
  X getFirstElement();
  boolean isExist(X data);
  void add(X data) throws LinkedListException;
  X removeInto(int index) throws LinkedListException;
  int getIndexOfElement(X data) throws LinkedListException;
  X getElementAt(int index) throws LinkedListException;
  Object[] toArray();
}
