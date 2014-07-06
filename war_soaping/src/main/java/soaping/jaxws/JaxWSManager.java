package soaping.jaxws;

import com.sun.xml.ws.transport.http.servlet.ServletAdapter;
import com.sun.xml.ws.transport.http.servlet.WSServlet;
import com.sun.xml.ws.transport.http.servlet.WSServletContextListener;
import com.sun.xml.ws.transport.http.servlet.WSServletDelegate;
import soaping.endpoint.EndpointModel;

import javax.naming.Context;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Somebody on 26.06.14.
 */
public class JaxWSManager {

    private ServletContext context;
    private WSServlet wsServlet;

    private static volatile JaxWSManager instance;

    public static JaxWSManager getInstance(ServletConfig servletConfig) throws JaxWSManagerException {
        JaxWSManager localInstance = instance;
        if (localInstance == null) {
            synchronized (JaxWSManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new JaxWSManager(servletConfig);
                }
            }
        }
        return localInstance;
    }


    private JaxWSManager(ServletConfig servletConfig) throws JaxWSManagerException {
        if (servletConfig == null) {
            throw new JaxWSManagerException("ServletConfig cannot be null");
        }
        context = servletConfig.getServletContext();

        try {
            wsServlet = new WSServlet();
            wsServlet.init(servletConfig);
        } catch (ServletException e) {
            throw new JaxWSManagerException("Cannot create or initialize WSServlet", e);
        }
    }

    public synchronized void createWebService(EndpointModel endpointModel) throws JaxWSManagerException {
        try {
            getWsServletContextListener().parseAndAppendAdapters(context, endpointModel.toXmlInputStream());
        } catch (Exception e) {
            throw new JaxWSManagerException(e);
        }
    }

    public synchronized void destroyWebServices() throws JaxWSManagerException {
        getWsServletContextListener().disposeEndpoints();
    }

    public void callWebService(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        wsServlet.doGet(request, response);
    }

    private WSServletDelegate getDelegate() throws JaxWSManagerException {
        WSServletDelegate delegate = (WSServletDelegate) context.getAttribute(WSServlet.JAXWS_RI_RUNTIME_INFO);
        if (delegate == null) {
            throw new JaxWSManagerException("WSServletDelegate was not found at context");
        }
        return delegate;
    }

    private WSServletContextListener getWsServletContextListener() throws JaxWSManagerException {
        WSServletContextListener listener = (WSServletContextListener) context.getAttribute(WSServletContextListener.JAXWS_RI_LISTENER);
        if (listener == null) {
            throw new JaxWSManagerException("WSServletContextListener was not found at context");
        }
        return listener;
    }

}
