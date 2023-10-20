package DataStructure;
import DataStructure.Exceptions.VectorException;

public class Vector<X> implements Cloneable {
    private Object[] array;
    private final boolean isLimited;

    public Vector() {
        this.array = new Object[5];
        this.isLimited = false;
    }

    public Vector(int limit) throws VectorException {
        if (limit < 1)
            throw new VectorException("The limit can't be lower than one");
        this.isLimited = true;
        this.array = new Object[limit];
    }

    protected Vector(Object[] array, boolean isLimited) throws VectorException {
        if(array == null) throw new VectorException("The array is null");

        this.array = new Object[array.length];
        this.isLimited = isLimited;

        for (int i = 0; i < array.length; i++)
            this.array[i] = ShallowOrDeepCopy.verifyAndCopy(array[i]);
    }

    protected int firstFreeIndex() {
        for (int i = 0; i < array.length; i++)
            if (this.array[i] == null)
                return i;

        return -1;
    }

    protected int lastFreeIndex() {
        for (int i = this.array.length - 1; i < -1; i--)
            if (this.array[i] == null)
                return i;

        return -1;
    }

    public void resizeToDown() throws VectorException {
        resizeToDown((float) 2);
    }

    public void resizeToDown(float percent) throws VectorException {
        if (percent < 1)
            throw new VectorException("value cannot be less than 0");
        if (this.isLimited)
            throw new VectorException("the vector is limited");

        Object[] oldArray = this.array;

        this.array = new Object[Math.round(oldArray.length * percent)];

        for (int i = 0; i < oldArray.length; i++) {
            this.array[i + (oldArray.length - this.array.length)] = oldArray[i];
        }
    }

    public void resizeToUp() throws VectorException {
        resizeToUp((float) 2);
    }

    public void resizeToUp(float percent) throws VectorException {
        if (percent < 1)
            throw new VectorException("value cannot be less than 0");
        if (this.isLimited)
            throw new VectorException("the vector is limited");

        Object[] oldArray = this.array;

        this.array = new Object[Math.round(oldArray.length * percent)];

        for (int i = 0; i < oldArray.length; i++) {
            this.array[i] = oldArray[i];
        }
    }

    public Object[] toArray() { return this.array; }

    public void push(X data) throws VectorException {
        int i = this.firstFreeIndex();
        if (i < 0) {
            if(this.isLimited)
                throw new VectorException("Limit has been exceeded");
            
            try { this.resizeToUp(); } catch (Exception e) {  }
            i = this.firstFreeIndex();
        }

        this.array[i] = ShallowOrDeepCopy.verifyAndCopy(data);
    }

    public void set(int index, X value) throws VectorException {
        if (index < 0 || index > this.array.length)
            throw new VectorException("index cannot be less than 0 or greater than the length of the array");
        this.array[index] = ShallowOrDeepCopy.verifyAndCopy(value);
    }

    public X get(int index) throws VectorException {
        if (index < 0)
            throw new VectorException("index cannot be less than 0");

        return (X) ShallowOrDeepCopy.verifyAndCopy(this.array[index]);
    }

    public int size() {
        int i = this.lastFreeIndex();
        if (i < 0)
            i = this.array.length;

        return i;
    }

    public int length() {
        return this.array.length;
    }

    public boolean isLimited() {
        return isLimited;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
            if (obj == null)
            return false;
            if (obj.getClass() != obj.getClass())
            return false;
            
            if (!this.array.equals(((Vector<?>) obj).array))
            return false;
            if (this.isLimited != ((Vector<?>) obj).isLimited)
            return false;

        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 23;
        
        hash = 3 * hash + this.array.hashCode();
        hash = 5 * hash + Boolean.valueOf(this.isLimited).hashCode();
        
        if(hash < 0) hash *= -1;

        return hash;
    }
    
    @Override
    public String toString() {
        String ret = "{";
        
        for (Object obj : this.array)
            if(obj == null)
                ret += " null";
            else
            ret += " " + obj.toString();
            
            ret += " }";
            return ret;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new Vector<X>(this.array, this.isLimited); }
        catch (VectorException e) { e.printStackTrace(); throw new CloneNotSupportedException(e.getMessage()); }
    }
}