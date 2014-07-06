package soaping;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.HashMap;

/**
 * Created by Somebody on 22.06.14.
 */
@Stateless
@Remote(IEjbArchive.class)
public class EjbArchiveBean extends HashMap<String, String> implements IEjbArchive {

}
