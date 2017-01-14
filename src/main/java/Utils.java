/**
 * Created by hulk on 17-1-14.
 */
public class Utils {
    public static void checkNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException("object null ,please check");
        }
    }
}
