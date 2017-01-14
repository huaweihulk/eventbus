/**
 * Created by hulk on 17-1-14.
 */
public class App {
    static class StringObjectHandler extends ObjectEventHandler {
        private int count = 0;
        

        @Override
        public void handleEvent(Object object, long sequence, boolean endOfBatch) {
            System.out.println((String) object);

        }
    }

    public static void main(String[] args) throws EvenHandlerDuplicateException {
        ObjectEventHandler stringObjectHanlder = new StringObjectHandler();
        int tag = stringObjectHanlder.getTag();
        DefaultEventBus.instance.registerHandler(stringObjectHanlder);
        DefaultEventBus.instance.post("hello", tag);
    }
}
