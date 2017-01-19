import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hulk on 17-1-14.
 */
public class App {
    static class StringObjectHandler extends ObjectEventHandler {
        private int count = 0;

        public int getCount() {
            return count;
        }

        @Override
        public void handleEvent(Object object, long sequence, boolean endOfBatch) {
            //System.err.println((String) object);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    static class GuavaEventBus {
        private int count = 0;

        @Subscribe
        public void printString(String string) {
            //System.err.println(string);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    public static void main(String[] args) throws EvenHandlerDuplicateException {
        StringObjectHandler stringObjectHanlder = new StringObjectHandler();
//        StringObjectHandler stringObjectHandler2 = new StringObjectHandler();
//        StringObjectHandler stringObjectHandler3 = new StringObjectHandler();
        int tag = stringObjectHanlder.getTag();
        DefaultEventBus.getInstance().registerHandler(stringObjectHanlder);
//        DefaultEventBus.getInstance().registerHandler(stringObjectHandler2);
//        DefaultEventBus.getInstance().registerHandler(stringObjectHandler3);
        long start = 0L;
        start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 1000) {
            DefaultEventBus.getInstance().post("hello", tag);
        }
        System.out.println(stringObjectHanlder.getCount());

        AsyncEventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
        GuavaEventBus guavaEventBus = new GuavaEventBus();
        eventBus.register(guavaEventBus);
        start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) < 1000) {
            eventBus.post("hello");
        }
        System.out.println(guavaEventBus.count);
    }
}
