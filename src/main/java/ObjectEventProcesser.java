import com.lmax.disruptor.EventHandler;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hulk on 17-1-14.
 */
public class ObjectEventProcesser implements EventHandler<ObjectEvent> {
    private LinkedList<ObjectEventHandler> handlers = new LinkedList<ObjectEventHandler>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Executor executor = Executors.newCachedThreadPool();

    @Override
    public void onEvent(final ObjectEvent objectEvent, final long l, final boolean b) throws Exception {
        readWriteLock.readLock().lock();
        for (final ObjectEventHandler objectEventHandler : handlers) {
            if (objectEvent.getObjectTag() == objectEventHandler.getTag()) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        objectEventHandler.handleEvent(objectEvent.getObject(), l, b);
                    }
                });
            }
        }
        readWriteLock.readLock().unlock();
    }

    public void registerHanlder(ObjectEventHandler objectEventHandler) throws EvenHandlerDuplicateException {
        Utils.checkNotNull(objectEventHandler);
        if (checkHasContain(objectEventHandler)) {
            throw EvenHandlerDuplicateException.INSTANCE;
        }
        readWriteLock.writeLock().lock();
        handlers.add(objectEventHandler);
        readWriteLock.writeLock().unlock();
    }

    public void unRegisterHandler(ObjectEventHandler objectEventHandler) {
        Utils.checkNotNull(objectEventHandler);
        readWriteLock.writeLock().lock();
        for (ObjectEventHandler handler : handlers) {
            if (handler.getTag() == objectEventHandler.getTag()) {
                handlers.remove(handler);
            }
        }
        readWriteLock.writeLock().unlock();
    }

    private boolean checkHasContain(ObjectEventHandler objectEventHandler) {
        readWriteLock.readLock().lock();
        for (ObjectEventHandler handler : handlers) {
            if (objectEventHandler.getTag() == handler.getTag()) {
                return true;
            }
        }
        readWriteLock.readLock().unlock();
        return false;
    }
}
