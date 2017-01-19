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
public class DefaultEventBus extends EventBus {
    
    private static volatile DefaultEventBus defaultEventBus;

    /**
     * 放弃懒汉式,ringbuffer　会直接消耗内存
     *
     * @return
     */
    public static DefaultEventBus getInstance() {
        if (defaultEventBus == null) {
            synchronized (DefaultEventBus.class) {
                if (defaultEventBus == null) {
                    defaultEventBus = new DefaultEventBus(2048);
                }
            }
        }
        return defaultEventBus;
    }

    public DefaultEventBus(int bufferSize) {
        super(bufferSize);
    }
}
