package example.interfaces;

import example.reqresp.TestClassesRequest;
import example.reqresp.TestClassesResponse;

/**
 * Created by Somebody on 11.06.14.
 */


public interface IEJBHelloJava {

    String testHello(String name);

    TestClassesResponse testBO(TestClassesRequest request);

    void testCheckedException() throws EJBCheckedException;

    void testUncheckedException();
}
