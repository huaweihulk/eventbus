import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

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
            count++;
        }
    }

    static class GuavaEventBus {
        private int count = 0;

        @Subscribe
        public void printString(String string) {
            //System.err.println(string);
            count++;
        }
    }

    public static void main(String[] args) throws EvenHandlerDuplicateException {
        StringObjectHandler stringObjectHanlder = new StringObjectHandler();
        int tag = stringObjectHanlder.getTag();
        DefaultEventBus.instance.registerHandler(stringObjectHanlder);
        long start = 0L;
        start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 1000) {
            DefaultEventBus.instance.post("hello", tag);
        }
        System.out.println(stringObjectHanlder.getCount());

//        EventBus eventBus = new EventBus();
//        GuavaEventBus guavaEventBus = new GuavaEventBus();
//        eventBus.register(guavaEventBus);
//        start = System.currentTimeMillis();
//        while ((System.currentTimeMillis() - start) < 1000) {
//            eventBus.post("hello");
//        }
 //       System.out.println(guavaEventBus.count);
    }
}
