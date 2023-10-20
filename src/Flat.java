import DataStructure.ShallowOrDeepCopy;
import DataStructure.Vector;

public class Flat {
    private Vector<Integer> v;

    public Flat(Vector<Integer> v) throws Exception {
        this.v = (Vector<Integer>) v.clone();
    }

    public Integer get(int index) throws Exception {
        return this.v.get(index);
    }
}
