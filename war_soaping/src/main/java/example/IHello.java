package example;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Somebody on 18.05.14.
 */
@WebService
public interface IHello {

    @WebMethod
    public String sayHelloJavaFrom(String from);

}
