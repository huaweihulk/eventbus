import org.junit.Test

/**
 * Created by hulk on 17-1-14.
 */
class DefaultEventBusTest {
    class TestObjectHandler extends ObjectEventHandler {

        @Override
        void handleEvent(Object object, long sequence, boolean endOfBatch) {
            System.out.println((String) object);
        }
    }

    @Test
    void testPost() {
        TestObjectHandler testObjectHandler = new TestObjectHandler();
        DefaultEventBus.instance.registerHandler(testObjectHandler);
        DefaultEventBus.instance.post("Hello", testObjectHandler.getTag());
    }
}
