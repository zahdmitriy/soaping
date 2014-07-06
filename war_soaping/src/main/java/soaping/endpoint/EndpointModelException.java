package soaping.endpoint;

/**
 * Created by Somebody on 26.06.14.
 */
public class EndpointModelException extends Exception {

    public EndpointModelException() {
        super();
    }

    public EndpointModelException(String message) {
        super(message);
    }

    public EndpointModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public EndpointModelException(Throwable cause) {
        super(cause);
    }

}
