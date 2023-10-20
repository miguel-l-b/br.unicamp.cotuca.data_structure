package DataStructure;
import java.lang.reflect.Method;

public class ShallowOrDeepCopy {
    /**
     * Verify if the object is cloneable and copy it
     * @param data Object to be copied
     * @return A copy of the object
     */
    public static Object verifyAndCopy(Object data) {
        if(data instanceof Cloneable) {
            Object newData = deepCopy(data);
            if(newData == null)
                return data;
            else return newData;
        }
        else return data;
    }

    /**
     * Copy the object using the clone method
     * @param data Object to be copied
     * @return A copy of the object
     */
    public static Object deepCopy(Object data) {
        try {
            Class<?> x = data.getClass();
            Method method = x.getMethod("clone");
            return (Object)method.invoke(data);
        } catch(Exception err) { return null; }
    }
}