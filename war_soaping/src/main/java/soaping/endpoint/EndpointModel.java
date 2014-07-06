package soaping.endpoint;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Somebody on 05.06.14.
 */
public class EndpointModel {

    private String name;
    private String implementation;
    private String urlPattern;

    public EndpointModel(String interfaceClass, String context) throws EndpointModelException {
        Class<?> cl;
        try {
            //cl = getServletContext().getClassLoader().loadClass(iface);
            // because Java EE 5 ...
            cl = this.getClass().getClassLoader().loadClass(interfaceClass);
        } catch (ClassNotFoundException e) {
            throw new EndpointModelException("Cannot build endpoint model. Class not found [" + interfaceClass + "]", e);
        }

        this.name = cl.getCanonicalName();
        this.implementation = cl.getCanonicalName();
        this.urlPattern = context + "/" + this.name;
    }

    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<endpoints xmlns='http://java.sun.com/xml/ns/jax-ws/ri/runtime' version='2.0'>");
        xml.append("<endpoint name='"); xml.append(name);           xml.append("' ");
        xml.append("implementation='"); xml.append(implementation); xml.append("' ");
        xml.append("url-pattern='");    xml.append(urlPattern);     xml.append("' ");
        xml.append("/>");
        xml.append("</endpoints>");

        return xml.toString();
    }

    public InputStream toXmlInputStream() {
        return new ByteArrayInputStream(toXml().toString().getBytes());
    }

    public String getName() {
        return name;
    }

    public String getImplementation() {
        return implementation;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    @Override
    public String toString() {
        return "EndpointModel{" +
                "name='" + name + '\'' +
                ", implementation='" + implementation + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                '}';
    }
}

/*

<endpoints xmlns='http://java.sun.com/xml/ns/jax-ws/ri/runtime' version='2.0'>
    <endpoint
            name='HelloJava'
            implementation='example.HelloJava'
            url-pattern='/services/HelloJava'/>
</endpoints>

 */
