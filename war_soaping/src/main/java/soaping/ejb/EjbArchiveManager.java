package soaping.ejb;

import soaping.IEjbArchive;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;

/**
 * Created by Somebody on 26.06.14.
 */
public class EjbArchiveManager {

    IEjbArchive archive;

    private static volatile EjbArchiveManager instance;

    public static EjbArchiveManager getInstance() throws EjbArchiveManagerException {
        EjbArchiveManager localInstance = instance;
        if (localInstance == null) {
            synchronized (EjbArchiveManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EjbArchiveManager();
                }
            }
        }
        return localInstance;
    }

    private EjbArchiveManager() throws EjbArchiveManagerException {
        archive = getEjbArchive();
    }

    public boolean containsEjb(String clazz) {
        return archive.containsKey(clazz);
    }

    public void addEjb(String clazz) throws EjbArchiveManagerException {
        addEjb(clazz, findJndi(clazz));
    }

    public void addEjb(String clazz, String jndi) {
        archive.put(clazz, jndi);
    }

    public void removeEjb(String clazz) {
        archive.remove(clazz);
    }

    public void deleteEjbs() {
        archive.clear();
    }

    private IEjbArchive getEjbArchive() throws EjbArchiveManagerException {
        Object obj;
        try {
            obj = new InitialContext().lookup("soaping.IEjbArchive");
        } catch (NamingException e) {
            throw new EjbArchiveManagerException("Cannot lookup EjbArchive bean", e);
        }
        return (IEjbArchive) PortableRemoteObject.narrow(obj, IEjbArchive.class);
    }

    private String findJndi(String clazz) throws EjbArchiveManagerException {
        String jndi = null;
        try {
            InitialContext ctx = new InitialContext();
            NamingEnumeration<NameClassPair> list =  ctx.list("");
            while (list.hasMore()) {
                NameClassPair next = list.next();
                if (next.getClassName().equals(clazz)) {
                    jndi = next.getName();
                    break;
                }
            }
        } catch (NamingException e) {
            throw new EjbArchiveManagerException("Cannot find jndi name for ejb " + clazz, e);
        }
        return jndi;
    }

}
