public class Testing implements Comparable<Testing>, Cloneable {
    public int num;

    public Testing(int num) {
        this.num = num;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        return ""+num;
    }

    @Override
    public int compareTo(Testing other) {
        return this.num - other.num;
    }
}
