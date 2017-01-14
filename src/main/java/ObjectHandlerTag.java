import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hulk on 17-1-14.
 */
public class ObjectHandlerTag {
    private static AtomicInteger tagLong = new AtomicInteger(0);

    public static int getTag() {
        return tagLong.incrementAndGet();
    }
}
