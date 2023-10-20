package DataStructure.LinkedList;

import DataStructure.ShallowOrDeepCopy;
import DataStructure.Exceptions.LinkedListException;

public class LinkedList<X> implements IEssentialLinkedList<X> {
    private Node<X> first;
    private Node<X> last;

    public LinkedList() {
        this.first = this.last = null;
    }

    protected LinkedList(LinkedList<X> linkedList) throws LinkedListException {
        if(linkedList == null)
            throw new LinkedListException("the linkedList cannot be null");
        for(Node<X> current = linkedList.first; current != null; current = current.getNext())
            this.addIntoLast((X) ShallowOrDeepCopy.verifyAndCopy(current.getData()));
    }

    public void addIntoLast(X data) throws LinkedListException {
        if(data == null)
            throw new LinkedListException("the data cannot be null");

        if(this.last == null)
            this.first = this.last = new Node((X) ShallowOrDeepCopy.verifyAndCopy(data));

        else {
            this.last.setNext(new Node((X) ShallowOrDeepCopy.verifyAndCopy(data)));
            this.last = this.last.getNext();
        }
    }

    public void addIntoFirst(X data) throws LinkedListException {
        if(data == null)
            throw new LinkedListException("the data cannot be null");
            
            this.first = new Node((X) ShallowOrDeepCopy.verifyAndCopy(data), this.first);

            if(this.last == null)
                this.last = this.first;
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


    public void setElementOfIndex(X data, int index) throws LinkedListException {
        int count = -1;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            if((count++) == index) {
                current.setData(data);
                return;
            }

        throw new LinkedListException("the index is not exist");
    }
    
    public void setFirstElement(X data) throws LinkedListException {
        if(data == null)
            throw new LinkedListException("the data cannot be null");
        this.first.setData(data);
    }
    
    public void setLastElement(X data) throws LinkedListException {
        if(data == null)
            throw new LinkedListException("the data cannot be null");
        this.last.setData(data);
    }

    public int indexOf(X data) {
        int count = 0;
        if(data == null)
            return -1;
        for(Node<X> current = this.first; current != null; current = current.getNext()) {
            if(current.getData().equals(data)) return count;
            count++;
        }
        
        return -1;        
    }

    public void incrementTheList(LinkedList<X> list) {
        for(Node<X> current = list.first; current != null; current = current.getNext())
            try { addIntoLast(current.getData()); }
            catch (LinkedListException e) { }
    }

    public LinkedList<X> getIncrementedList(LinkedList<X> list) throws LinkedListException {
        try {
            LinkedList<X> newList = (LinkedList<X>) this.clone();
            newList.incrementTheList(list);
            return newList;
        } catch (CloneNotSupportedException e) { return null; }
    }

    public LinkedList<X> getInvertedList() {
        LinkedList<X> list = new LinkedList<>();
        for(Node<X> current = this.first; current != null; current = current.getNext())
            try { list.addIntoFirst(current.getData()); }
            catch (LinkedListException e) { }
        
        return list;
    }
    
    public void reverse() {
        LinkedList<X> listInverted = this.getInvertedList();
        
        this.first = listInverted.first;
        this.last = listInverted.last;
    }

    public LinkedList<X> getTheIntersectionWithTheList(LinkedList<X> list) {
        LinkedList<X> intersectionList = new LinkedList<>();
        for(Node<X> current = this.first; current != null; current = current.getNext())
            if(list.isExist(current.getData()))
                if(!intersectionList.isExist(current.getData()))
                    try { intersectionList.addIntoLast(current.getData()); }
                    catch (LinkedListException e) { }
        
        return intersectionList;
    }

    @Override
    public X getLastElement() { return (X) this.last.getData(); }
    @Override
    public X getFirstElement() { return (X) this.first.getData(); }

    @Override
    public boolean isEmpty() { return this.first == null; }

    @Override
    public boolean isExist(X data) {
        for(Node<X> current = this.first; current != null; current = current.getNext())
            if(current.getData().equals(data)) return true;

        return false;
    }

    @Override
    public int getSize() {
        int count = 0;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            count++;
        
        return count;
    }
    
    @Override
    public X getElementAt(int index) throws LinkedListException {
        int count = -1;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            if((++count) == index) return current.getData();
        
        throw new LinkedListException("the index is not exist");
    }
    
    @Override
    public int getIndexOfElement(X data) throws LinkedListException {
        int count = -1;
        for(Node<X> current = this.first; current != null; current = current.getNext()) {
            count++;
            if(current.getData().equals(data)) return count;
        }

        throw new LinkedListException("the data is not exist");
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

    @Override
    public void clear() { this.first = this.last = null; }

    @Override
    public Object[] toArray() {
        int size = this.getSize();
        if(size == 0)
            return new Object[] { };

        Object[] array = new Object[size];
        int count = -1;
        for(Node<X> current = this.first; current != null; current = current.getNext())
            array[++count] = current.getData();

        return array;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[ ");
        for(Node<X> current = this.first; current != null; current = current.getNext()) {
            builder.append(current.getData().toString());
            if(current.getNext() != null) builder.append(" -> ");
        }
        builder.append(" ]");

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof LinkedList)) return false;

        LinkedList<?> list = (LinkedList<?>) obj;

        if(this.getSize() != list.getSize()) return false;
        
        int count = -1;
        for(Node<?> current = list.first; current != null; current = current.getNext())
            try { if(!this.getElementAt(++count).equals(current.getData())) return false; }
            catch (LinkedListException e) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 4;
        hash = 3 * hash + (this.first != null ? this.first.hashCode() : 0);
        hash = 7 * hash + (this.last != null ? this.last.hashCode() : 0);

        if(hash < 0) hash *= -1;
        
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new LinkedList<X>(this);}
        catch (LinkedListException e) { throw new CloneNotSupportedException(e.getMessage()); }
    }
}
