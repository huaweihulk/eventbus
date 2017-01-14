import com.lmax.disruptor.EventFactory;

/**
 * Created by hulk on 17-1-14.
 */
public class ObjectEventFactory implements EventFactory<ObjectEvent> {
    @Override
    public ObjectEvent newInstance() {
        return new ObjectEvent();
    }
}
