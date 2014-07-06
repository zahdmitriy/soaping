
package example;

//import com.sun.xml.ws.transport.http.servlet.WSServlet;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Somebody on 14.05.14.
 */
//@WebService
public class HelloJava implements IHello {

    public String sayHelloJavaFrom(String from) {
        String result = "Hello, Java, from " + from;
        System.out.println(result);
        return result;
    }

    @WebMethod
    public String sayWHAT(String from) {
        String result = Arrays.asList(this.getClass().getAnnotations()).toString();
        //Annotation a = this.getClass().getAnnotations()[0];
        //result += "\n" + a.annotationType().getSimpleName();

        System.out.println(result);
        return result;
    }

    public static void main(String[] argv) {
    /*Dispatcher implementor = new HelloWorld ();
    String address = "http://localhost:9000/HelloWorld";
    Endpoint.publish(address, implementor);*/

    }

}
