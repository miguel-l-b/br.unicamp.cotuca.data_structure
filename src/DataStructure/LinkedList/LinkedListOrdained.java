package DataStructure.LinkedList;

import DataStructure.ShallowOrDeepCopy;
import DataStructure.Exceptions.LinkedListException;

public class LinkedListOrdained<X extends Comparable<X>> implements IEssentialLinkedList<X> {
    private Node<X> first, last;

    public LinkedListOrdained() {
        this.first = this.last = null;
  }

    public LinkedListOrdained(LinkedListOrdained<X> linkedList) throws LinkedListException {
        LinkedListOrdained<X> list = new LinkedListOrdained<>();
        Node<X> current = linkedList.first;

        while(current != null) {
            list.add(current.getData());
            current = current.getNext();
        }

        this.first = list.first;
        this.last = list.last;
    }

    public void add(X data) throws LinkedListException {
        if(data == null)
            throw new LinkedListException("the data cannot be null");

        if(this.first == null) {
            this.first = new Node((X) ShallowOrDeepCopy.verifyAndCopy(data), this.first);
            return;
        }

        if(this.first.getData().compareTo(data) < 0) {
            this.first = new Node((X) ShallowOrDeepCopy.verifyAndCopy(data), this.first);
            return;
        }

        for(Node<X> current = this.first; current != null; current = current.getNext()) {
            if(current.getNext() == null) {
                current.setNext(new Node((X) ShallowOrDeepCopy.verifyAndCopy(data)));
                this.last = current.getNext();
                return;
            }
            if(current.getNext().getData().compareTo(data) < 0) {            
                current.setNext(new Node((X) ShallowOrDeepCopy.verifyAndCopy(data), current.getNext()));
                return;
            }
        }
    }

    @Override
    public X getElementAt(int index) throws LinkedListException {
        int count = 0;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            if((count++) == index) return current.getData();

        throw new LinkedListException("the index is out of range");
    }

    @Override
    public X getFirstElement() { return this.first.getData(); }

    @Override
    public X getLastElement() { return this.last.getData(); }

    @Override
    public int getIndexOfElement(X data) throws LinkedListException {
        int count = 0;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            if((count++) > 0 && data.equals(current.getData())) return count;

        throw new LinkedListException("the data is not in the list");
    }


    @Override
    public int getSize() {
        int count = 0;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            count++;

        return count;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public boolean isExist(X data) {
        for(Node<X> current = this.first; current != null; current = current.getNext())
            if(data.equals(current.getData())) return true;

        return false;
    }

    @Override
    public X removeInto(int index) throws LinkedListException {
        if(index == 0) return removeIntoFirst();
        int count = -1;
        for(Node<X> current = this.first; current != null; current = current.getNext()) {
            if(current.getNext() == null) return removeIntoLast();
            if((++count) == index-1) {
                X data = current.getNext().getData();
                current.setNext(current.getNext().getNext());
                return data;
            }
        }

        throw new LinkedListException("the index is not exist");
    }

    public X removeIntoLast() throws LinkedListException {
        if(this.last == null)
            throw new LinkedListException(null);
        X data = this.last.getData();
        Node<X> current;
        for(current = this.first; current.getNext() != this.last; current = current.getNext());
        current.setNext(null);
        this.last = current;
        return data;
    }

    public X removeIntoFirst() throws LinkedListException {
        if(this.first == null)
            throw new LinkedListException(null);
        X data = this.first.getData();
        this.first = this.first.getNext();
        return data;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[ ");
        for(Node<X> current = this.first; current != null; current = current.getNext())
        builder.append(current.getData().toString()).append(" -> ");
        builder.append(" ]");

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof LinkedListOrdained)) return false;

        LinkedListOrdained<?> list = (LinkedListOrdained<?>) obj;

        if(this.getSize() != list.getSize()) return false;
        
        Node<?> current = this.first;
        Node<?> current2 = list.first;

        while((current = current.getNext()) != null || (current2 = current2.getNext()) != null) {
            if(current == null || current2 == null) return false;
            if(!current.getData().equals(current2.getData())) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 3 * hash + (this.first != null ? this.first.hashCode() : 0);
        hash = 7 * hash + (this.last != null ? this.last.hashCode() : 0);
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new LinkedListOrdained<X>(this);}
        catch (LinkedListException e) { throw new CloneNotSupportedException(e.getMessage()); }
    }

    @Override
    public void clear() { this.first = this.last = null; }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.getSize()];
        int count = 0;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            array[count++] = current.getData();

        return array;
    }
}
