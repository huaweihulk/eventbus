import java.awt.*;

/**
 * Created by hulk on 17-1-14.
 */
public abstract class ObjectEventHandler {
    public abstract void handleEvent(final Object object, final long sequence, final boolean endOfBatch);

    private int tag;

    public ObjectEventHandler(int tag) {
        this.tag = tag;
    }

    public ObjectEventHandler() {
        this.tag = ObjectHandlerTag.getTag();
    }

    public int getTag() {
        return tag;
    }
}
