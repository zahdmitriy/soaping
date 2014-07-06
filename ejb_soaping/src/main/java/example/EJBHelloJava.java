package example;

import example.interfaces.EJBCheckedException;
import example.interfaces.IEJBHelloJava;
import example.reqresp.TestClassesRequest;
import example.reqresp.TestClassesResponse;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;


/**
 * Created by Somebody on 11.06.14.
 */

@Stateless
@Remote(IEJBHelloJava.class)
public class EJBHelloJava implements IEJBHelloJava {

    @Override
    public String testHello(String name) {
        return "Hello from remote EJB, " + name;
    }

    @Override
    public TestClassesResponse testBO(TestClassesRequest request) {
        TestClassesResponse response = new TestClassesResponse();
        response.setOutputMessage("This is a test string from request: " + request.getInputMessage());
        return response;
    }

    @Override
    public void testCheckedException() throws EJBCheckedException {
        throw new EJBCheckedException("This is checked exception test");
    }

    @Override
    public void testUncheckedException() {
        int a = 1/0;
    }

}
