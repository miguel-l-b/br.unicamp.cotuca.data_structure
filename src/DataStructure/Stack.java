package DataStructure;
import DataStructure.Exceptions.StackException;
import DataStructure.Exceptions.VectorException;

public class Stack<T> implements Cloneable {
    private Vector<T> vector; 
    private int last = -1;

    /**
     * Constructor with no limit of stacked elements
     */
    public Stack() {
        this.vector = new Vector<T>();
    }

    /**
     * Constructor with stacked element limit
     * @param limit element quantity limit
     * @throws Exception if the limit is negative
     */
    public Stack(int limit) throws Exception {
        this.vector = new Vector<T>(limit);
    }

    /**
     * Copy builder
     * @param vector
     * @param last
     * @throws StackException
     */
    protected Stack(Vector<T> vector, int last) throws StackException {
        if(vector == null) throw new StackException("The vector is null");
        if(last < -1) throw new StackException("The last is less than -1");

        this.last = last;
        try { this.vector = (Vector<T>) vector.clone(); }
        catch (CloneNotSupportedException e) { throw new StackException("The vector is not cloneable"); }
    }

    
    /**
     * Get the last element of the stack
     * @return the last element of the stack
     * @throws StackException if the stack is empty
     */
    public T peek() throws StackException {
        if(this.isEmpty())
        throw new StackException("The stack is empty");
        try { return (T)(this.vector.get(last)); }
        catch(Exception e) { return null; }
    }

    /**
     * Add elements to the stack
     * @param value element to stack up
     * @throws StackException Element cannot be null or may have reached stack limit
     */
    public void push(T value) throws StackException {
        if(value == null) throw new StackException("The value is null");
        if(this.isFull())
        if(this.vector.isLimited())
        throw new StackException("Limit has been exceeded");
        else
        try { this.vector.resizeToUp(); }
        catch(Exception e) {  }
        
        last++;
        
        try { this.vector.set(last, value); }
        catch(Exception e) {  }
    }
    
    /**
     * Get the last element of the stack and remove it
     * @return the last element of the stack
     * @throws StackException If the stack is empty
     */
    public T pop() throws StackException {
        T old = peek();
        
        try { this.vector.set(last, null); }
        catch (VectorException e) { }
        this.last--;
        
        return old;
    }
    
    /**
     * @return the stack is empty
     */
    public boolean isEmpty() { return last < 0; }
    /**
     * @return the stack is full
     */
    public boolean isFull() { return last+1 == this.vector.length(); }
    /**
     * @return the stack size
     */
    public int size() { return this.last; }

    /**
     * Transform the stack into an array
     * @return the stack as an array
     */
    public Object[] toArray() {
        Object[] array = new Object[this.last+1];
        
        for(int i = 0; i < this.last+1; i++)
            try { array[i] = this.vector.get(i); }
            catch (VectorException e) { }

        return array;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        
        if(!this.vector.equals(((Stack<?>) obj).vector)) return false;
        if(this.last != ((Stack<?>) obj).last) return false;

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

        hash = 3 * hash + ((Integer) this.last).hashCode();
        hash = 5 * hash + this.vector.hashCode();

        if(hash < 0) hash *= -1;

        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new Stack<T>(this.vector, this.last); }
        catch (StackException e) { throw new CloneNotSupportedException(e.getMessage()); }
    }
}