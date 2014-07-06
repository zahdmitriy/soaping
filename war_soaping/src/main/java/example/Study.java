
package example;

import com.sun.xml.ws.transport.http.servlet.WSServletDelegate;
import example.interfaces.IEJBHelloJava;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import java.io.IOException;
import java.security.AccessController;
import java.util.Map;

/**
 * Created by Somebody on 14.05.14.
 */
public class Study extends HttpServlet {

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();

        /*Enumeration<String> e = this.getServletConfig().getInitParameterNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            out.println(name + "=" + this.getServletConfig().getInitParameter(name));
        }
*/
        //out.println();


        //WSServlet servlet = new WSServlet();

      //  WSServlet servlet = getServletContext().createServlet(WSServlet.class); // .addServlet("WSServlet", servlet);
       // getServletContext().addServlet("WSServlet", servlet);

        //getServletContext().removeAttribute("com.sun.xml.ws.transport.http.servlet.WSServletContextListener.Invoked");

        //WSServletContextListener l = new WSServletContextListener();
        //l.contextInitialized(new ServletContextEvent(getServletContext()));


/*       Map<String, ? extends ServletRegistration> map = getServletContext().getServletRegistrations();
        for(Map.Entry<String, ? extends ServletRegistration> entry : map.entrySet()) {
            out.println("Servlet registration: " + entry.getKey() + ", name = " + entry.getValue().getName() + ", class = " + entry.getValue().getClassName());
            out.print("Mappings: ");
            for (String mapping : entry.getValue().getMappings()) {
                out.print(mapping + " ");
            }
            out.println();
        }*/

//        WSServletDelegate delegate = (WSServletDelegate) getServletContext().getAttribute("com.sun.xml.ws.server.http.servletDelegate");
//        out.println(delegate.adapters.size());

//        WSServletContextListener listener = (WSServletContextListener) getServletContext().getAttribute(WSServletContextListener.JAXWS_RI_LISTENER);
//        listener.parseAndAppendAdapters(getServletContext(), null);

        String factoryClassName = AccessController.doPrivileged(new sun.security.action.GetPropertyAction(JAXBContext.class.getName()));

        out.println("yeeah11! but..." + factoryClassName);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
