package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

//import com.sun.xml.ws.transport.http.servlet.WSServlet;

/**
 * Created by Somebody on 14.05.14.
 */
@WebService()
public class HelloWorld {

    private int counter = 0;

    @WebMethod
    public String sayHelloWorldFrom(String from) throws InterruptedException {
        counter += 1;
//        Thread.sleep(30 * 1000);
        String result = "Hello, World, from " + from;
        result += " (counter = " + counter + ")";
        return result;

    }

    public static void main(String[] argv) {
    /*Dispatcher implementor = new HelloWorld ();
    String address = "http://localhost:9000/HelloWorld";
    Endpoint.publish(address, implementor);*/
    }
}
