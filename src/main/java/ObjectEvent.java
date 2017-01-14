/**
 * Created by hulk on 17-1-14.
 */
public class ObjectEvent {
    private Object object;
    private long objectTag;

    public void setObject(Object object) {
        this.object = object;
    }

    public void setObjectTag(long objectTag) {
        this.objectTag = objectTag;
    }

    public Object getObject() {
        return object;
    }

    public long getObjectTag() {
        return objectTag;
    }
}
