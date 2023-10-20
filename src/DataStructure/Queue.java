package DataStructure;
import DataStructure.Exceptions.QueueException;
import DataStructure.Exceptions.VectorException;

public class Queue<T> implements Cloneable {
    private Vector<T> vector;
    private int first = 0;
    private int last = -1;
    private int size = 0;

    /**
     * Constructor without limit of queued elements
     */
    public Queue() {
        this.vector = new Vector<T>();
    }

    /**
     * Constructor with queued element limit
     * @param limit Element quantity limit
     * @throws VectorException If the limit is negative
     */
    public Queue(int limit) throws VectorException {
        this.vector = new Vector<T>(limit);
    }

    /**
     * Copy builder
     * @param vector
     * @param first
     * @param last
     * @param size
     * @throws QueueException
     */
    protected Queue(Vector<T> vector, int first, int last, int size) throws QueueException {
        if(vector == null) throw new QueueException("The vector is null");
        if(first < 0) throw new QueueException("The first is less than zero");
        if(last < -1) throw new QueueException("The last is less than -1");
        if(size < 0) throw new QueueException("The size is less than zero");
        if(last == -1 && last +1 == size || last - first == size) throw new QueueException("The last - first is less than size");
        
        this.first = first;
        this.last = last;
        this.size = size;
        try { this.vector = (Vector<T>) vector.clone(); }
        catch (CloneNotSupportedException e) { throw new QueueException("The vector is not cloneable"); }
    }

    /**
     * Add elements to the queue
     * @param value Element to enqueue
     * @throws QueueException Element cannot be null or may have reached queue limit
     */
    public void push(T value) throws QueueException {
        if(value == null) throw new QueueException("The value is null");

        if(this.isFull()) {
            if(this.vector.isLimited())
            throw new QueueException("Limit has been exceeded");
            
            try { this.vector.resizeToUp(); }
            catch(Exception e) {  }
        }

        last = (last + 1) % this.vector.length();

        try { this.vector.set(last, value); }
        catch(Exception e) {  }
        size++;
    }

    /**
     * Get the first element of the queue
     * @return The first element of the queue
     * @throws QueueException If the queue is empty
     */
    public T peek() throws QueueException {
        if(this.isEmpty())
            throw new QueueException("The queue is empty");
        try { return this.vector.get(first); }
        catch(Exception e) { return null; }
    }


    /**
     * Get the first element from the queue and remove it
     * @return The first element of the queue
     * @throws QueueException If the queue is empty
     */
    public T pop() throws QueueException {
        T old = peek();

        try { this.vector.set(first, null); }
        catch(Exception e) {  }
        first = (first + 1) % this.vector.length();
        size--;

        return old;
    }
    
    /**
     * @return The queue is empty
     */
    public boolean isEmpty() { return size == 0; }
    /**
     * @return The queue is full
     */
    public boolean isFull() { return size == this.vector.length(); }
    /**
     * @return The queue size
     */
    public int size() { return this.size; }

    /**
     * Transform the queue into an array
     * @return The queue elements as an array
     */
    public Object[] toArray() {
        Object[] array = new Object[this.size];

        for(int i = 0; i < this.size; i++)
            try { array[i] = this.vector.get((first + i) % this.vector.length()); }
            catch (VectorException e) { }

        return array;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        
        if(!this.vector.equals(((Queue<?>) obj).vector)) return false;
        if(this.first != ((Queue<?>) obj).first) return false;

        return true;
    }

    @Override
    public String toString() {
        try { return "{ "+peek()+" }"; } 
        catch(Exception e) { return "{  }"; }
    }
    @Override
    public int hashCode() {
        int hash = 23;

        hash = 3 * hash + Integer.valueOf(this.first).hashCode();
        hash = 5 * hash + this.vector.hashCode();

        if(hash < 0) hash *= -1;

        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new Queue<T>(this.vector, this.first, this.last, this.size); }
        catch (QueueException e) { throw new CloneNotSupportedException(e.getMessage()); }
    }
}
