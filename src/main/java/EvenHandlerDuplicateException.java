/**
 * Created by hulk on 17-1-14.
 */
public class EvenHandlerDuplicateException extends Exception {
    public static final EvenHandlerDuplicateException INSTANCE = new EvenHandlerDuplicateException("EventHandler occure duplicate");

    public EvenHandlerDuplicateException(String s) {
        super(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
