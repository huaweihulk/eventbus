import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hulk on 17-1-13.
 * 默认为1p-1c buffersize为2048 executor 为CachedThreadPool
 */
public enum DefaultEventBus {
    instance(2048);
    private final RingBuffer<ObjectEvent> ringBuffer;
    private EventFactory objectEventFactory;
    private Executor executor;
    private ObjectEventProcesser objectEventProcesser;
    private ObjectTranslator objectTranslator;
    private int bufferSize;

    DefaultEventBus(int bufferSize) {
        objectEventFactory = new ObjectEventFactory();
        executor = Executors.newCachedThreadPool();
        Disruptor<ObjectEvent> disruptor = new Disruptor<ObjectEvent>(objectEventFactory, bufferSize,
                DaemonThreadFactory.INSTANCE, ProducerType.SINGLE,
                new YieldingWaitStrategy());
        objectEventProcesser = new ObjectEventProcesser();
        objectTranslator = new ObjectTranslator();
        disruptor.handleEventsWith(objectEventProcesser);
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
    }

    public void registerHandler(ObjectEventHandler objectEventHandler) throws EvenHandlerDuplicateException {
        objectEventProcesser.registerHanlder(objectEventHandler);
    }

    public void unRegisterHanler(ObjectEventHandler objectEventHandler) {
        objectEventProcesser.unRegisterHandler(objectEventHandler);
    }

    public void post(Object object, int tag) {
        ringBuffer.publishEvent(objectTranslator, object, tag);
    }
}
