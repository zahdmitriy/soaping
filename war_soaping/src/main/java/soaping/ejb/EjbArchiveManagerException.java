package soaping.ejb;

import example.interfaces.EJBCheckedException;

/**
 * Created by Somebody on 26.06.14.
 */
public class EjbArchiveManagerException extends Exception {

    public EjbArchiveManagerException() {
        super();
    }

    public EjbArchiveManagerException(String message) {
        super(message);
    }

    public EjbArchiveManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EjbArchiveManagerException(Throwable cause) {
        super(cause);
    }

}
