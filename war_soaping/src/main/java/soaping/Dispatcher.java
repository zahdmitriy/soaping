
package soaping;

import soaping.ejb.EjbArchiveManager;
import soaping.ejb.EjbArchiveManagerException;
import soaping.endpoint.EndpointModel;
import soaping.endpoint.EndpointModelException;
import soaping.jaxws.JaxWSManager;
import soaping.jaxws.JaxWSManagerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Somebody on 05.06.14.
 */
public class Dispatcher extends HttpServlet {

    private JaxWSManager jaxWSManager;
    private EjbArchiveManager ejbArchiveManager;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            jaxWSManager = JaxWSManager.getInstance(getServletConfig());
            ejbArchiveManager = EjbArchiveManager.getInstance();
        } catch (JaxWSManagerException e) {
            throw new ServletException(e);
        } catch (EjbArchiveManagerException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        // чистим архив и дестроим энпоинты
        try {
            jaxWSManager.destroyWebServices();
            ejbArchiveManager.deleteEjbs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // вытягиваем полное имя класса и подгототавливаем модель эндпоинта
        String iface = request.getRequestURI().substring(request.getContextPath().length() + request.getServletPath().length() + 1);
        EndpointModel endpointModel = null;
        try {
            endpointModel = new EndpointModel(iface, request.getServletPath());
        } catch (EndpointModelException e) {
            throw new ServletException(e);
        }

        // если ejb/сервис еще не были зарегистрированы
        if (!ejbArchiveManager.containsEjb(endpointModel.getName())) {
            try {
                ejbArchiveManager.addEjb(endpointModel.getName());
                jaxWSManager.createWebService(endpointModel);
            } catch (EjbArchiveManagerException e) {
                throw new ServletException(e);
            } catch (JaxWSManagerException e) {
                try {
                    ejbArchiveManager.removeEjb(endpointModel.getName());
                } catch (Exception ex) {}
                throw new ServletException(e);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
        jaxWSManager.callWebService(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
        jaxWSManager.callWebService(request, response);
    }

}

