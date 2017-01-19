import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hulk on 17-1-16.
 * 用于异步处理ringbuffer中的数据
 * 针对p的生产能力大于c的消费能力
 * 而且c的处理时间较长的情况
 * 使用线程池处理作对应处理
 * 默认大小为2048
 */
public class DefaultAnycEventBus extends EventBus {
    private Executor executor;

    private volatile DefaultAnycEventBus defaultAnycEventBus;

    public DefaultAnycEventBus(int bufferSize) {
        super(bufferSize);
        executor = Executors.newCachedThreadPool();
    }



}
