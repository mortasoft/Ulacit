
package api;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Estudiantes", targetNamespace = "http://API/", wsdlLocation = "http://localhost:8080/ServidorSOAP/Estudiantes?wsdl")
public class Estudiantes_Service
    extends Service
{

    private final static URL ESTUDIANTES_WSDL_LOCATION;
    private final static WebServiceException ESTUDIANTES_EXCEPTION;
    private final static QName ESTUDIANTES_QNAME = new QName("http://API/", "Estudiantes");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ServidorSOAP/Estudiantes?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ESTUDIANTES_WSDL_LOCATION = url;
        ESTUDIANTES_EXCEPTION = e;
    }

    public Estudiantes_Service() {
        super(__getWsdlLocation(), ESTUDIANTES_QNAME);
    }

    public Estudiantes_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ESTUDIANTES_QNAME, features);
    }

    public Estudiantes_Service(URL wsdlLocation) {
        super(wsdlLocation, ESTUDIANTES_QNAME);
    }

    public Estudiantes_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ESTUDIANTES_QNAME, features);
    }

    public Estudiantes_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Estudiantes_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Estudiantes
     */
    @WebEndpoint(name = "EstudiantesPort")
    public Estudiantes getEstudiantesPort() {
        return super.getPort(new QName("http://API/", "EstudiantesPort"), Estudiantes.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Estudiantes
     */
    @WebEndpoint(name = "EstudiantesPort")
    public Estudiantes getEstudiantesPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://API/", "EstudiantesPort"), Estudiantes.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ESTUDIANTES_EXCEPTION!= null) {
            throw ESTUDIANTES_EXCEPTION;
        }
        return ESTUDIANTES_WSDL_LOCATION;
    }

}
