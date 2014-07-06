package example.interfaces;

/**
 * Created by Somebody on 28.06.14.
 */
public class EJBCheckedException extends Exception {

    public EJBCheckedException() {
        super();
    }

    public EJBCheckedException(String message) {
        super(message);
    }

    public EJBCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EJBCheckedException(Throwable cause) {
        super(cause);
    }


}
