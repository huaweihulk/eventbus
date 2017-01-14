import com.lmax.disruptor.EventTranslatorTwoArg;

/**
 * Created by hulk on 17-1-14.
 */
public class ObjectTranslator implements EventTranslatorTwoArg<ObjectEvent, Object, Integer> {

    @Override
    public void translateTo(ObjectEvent objectEvent, long l, Object o, Integer aLong) {
        objectEvent.setObject(o);
        objectEvent.setObjectTag(aLong);
    }
}
