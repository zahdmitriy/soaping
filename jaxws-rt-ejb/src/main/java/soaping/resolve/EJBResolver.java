package soaping.resolve;

import com.sun.xml.ws.api.server.ITargetResolver;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.util.Map;

/**
 * Created by Somebody on 22.06.14.
 */
public class EJBResolver implements ITargetResolver {

    @Override
    public <T> T resolve(Class<T> clazz) {
        try {
            InitialContext ic = new InitialContext();
            // TODO: get JNDI
            Map<String, String> ejbArchive = (Map<String, String>) ic.lookup("soaping.IEjbArchive");
            String targetBeanJndi = ejbArchive.get(clazz.getCanonicalName());
            Object remoteBean = ic.lookup(targetBeanJndi);
            return (T) PortableRemoteObject.narrow(remoteBean, clazz);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
