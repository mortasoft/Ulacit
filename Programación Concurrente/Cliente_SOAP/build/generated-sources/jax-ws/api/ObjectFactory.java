
package api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListaEstudiantes_QNAME = new QName("http://API/", "ListaEstudiantes");
    private final static QName _AgregarEstudiante_QNAME = new QName("http://API/", "AgregarEstudiante");
    private final static QName _AgregarMateriaAEstudianteResponse_QNAME = new QName("http://API/", "AgregarMateriaAEstudianteResponse");
    private final static QName _MateriasDelEstudiante_QNAME = new QName("http://API/", "MateriasDelEstudiante");
    private final static QName _AgregarMateriaResponse_QNAME = new QName("http://API/", "AgregarMateriaResponse");
    private final static QName _AgregarEstudianteResponse_QNAME = new QName("http://API/", "AgregarEstudianteResponse");
    private final static QName _AgregarMateriaAEstudiante_QNAME = new QName("http://API/", "AgregarMateriaAEstudiante");
    private final static QName _ListaEstudiantesResponse_QNAME = new QName("http://API/", "ListaEstudiantesResponse");
    private final static QName _MateriasDelEstudianteResponse_QNAME = new QName("http://API/", "MateriasDelEstudianteResponse");
    private final static QName _AgregarMateria_QNAME = new QName("http://API/", "AgregarMateria");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AgregarMateria }
     * 
     */
    public AgregarMateria createAgregarMateria() {
        return new AgregarMateria();
    }

    /**
     * Create an instance of {@link MateriasDelEstudianteResponse }
     * 
     */
    public MateriasDelEstudianteResponse createMateriasDelEstudianteResponse() {
        return new MateriasDelEstudianteResponse();
    }

    /**
     * Create an instance of {@link ListaEstudiantesResponse }
     * 
     */
    public ListaEstudiantesResponse createListaEstudiantesResponse() {
        return new ListaEstudiantesResponse();
    }

    /**
     * Create an instance of {@link AgregarEstudianteResponse }
     * 
     */
    public AgregarEstudianteResponse createAgregarEstudianteResponse() {
        return new AgregarEstudianteResponse();
    }

    /**
     * Create an instance of {@link AgregarMateriaAEstudiante }
     * 
     */
    public AgregarMateriaAEstudiante createAgregarMateriaAEstudiante() {
        return new AgregarMateriaAEstudiante();
    }

    /**
     * Create an instance of {@link AgregarMateriaResponse }
     * 
     */
    public AgregarMateriaResponse createAgregarMateriaResponse() {
        return new AgregarMateriaResponse();
    }

    /**
     * Create an instance of {@link AgregarMateriaAEstudianteResponse }
     * 
     */
    public AgregarMateriaAEstudianteResponse createAgregarMateriaAEstudianteResponse() {
        return new AgregarMateriaAEstudianteResponse();
    }

    /**
     * Create an instance of {@link MateriasDelEstudiante }
     * 
     */
    public MateriasDelEstudiante createMateriasDelEstudiante() {
        return new MateriasDelEstudiante();
    }

    /**
     * Create an instance of {@link AgregarEstudiante }
     * 
     */
    public AgregarEstudiante createAgregarEstudiante() {
        return new AgregarEstudiante();
    }

    /**
     * Create an instance of {@link ListaEstudiantes }
     * 
     */
    public ListaEstudiantes createListaEstudiantes() {
        return new ListaEstudiantes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaEstudiantes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "ListaEstudiantes")
    public JAXBElement<ListaEstudiantes> createListaEstudiantes(ListaEstudiantes value) {
        return new JAXBElement<ListaEstudiantes>(_ListaEstudiantes_QNAME, ListaEstudiantes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarEstudiante }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "AgregarEstudiante")
    public JAXBElement<AgregarEstudiante> createAgregarEstudiante(AgregarEstudiante value) {
        return new JAXBElement<AgregarEstudiante>(_AgregarEstudiante_QNAME, AgregarEstudiante.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarMateriaAEstudianteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "AgregarMateriaAEstudianteResponse")
    public JAXBElement<AgregarMateriaAEstudianteResponse> createAgregarMateriaAEstudianteResponse(AgregarMateriaAEstudianteResponse value) {
        return new JAXBElement<AgregarMateriaAEstudianteResponse>(_AgregarMateriaAEstudianteResponse_QNAME, AgregarMateriaAEstudianteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MateriasDelEstudiante }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "MateriasDelEstudiante")
    public JAXBElement<MateriasDelEstudiante> createMateriasDelEstudiante(MateriasDelEstudiante value) {
        return new JAXBElement<MateriasDelEstudiante>(_MateriasDelEstudiante_QNAME, MateriasDelEstudiante.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarMateriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "AgregarMateriaResponse")
    public JAXBElement<AgregarMateriaResponse> createAgregarMateriaResponse(AgregarMateriaResponse value) {
        return new JAXBElement<AgregarMateriaResponse>(_AgregarMateriaResponse_QNAME, AgregarMateriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarEstudianteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "AgregarEstudianteResponse")
    public JAXBElement<AgregarEstudianteResponse> createAgregarEstudianteResponse(AgregarEstudianteResponse value) {
        return new JAXBElement<AgregarEstudianteResponse>(_AgregarEstudianteResponse_QNAME, AgregarEstudianteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarMateriaAEstudiante }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "AgregarMateriaAEstudiante")
    public JAXBElement<AgregarMateriaAEstudiante> createAgregarMateriaAEstudiante(AgregarMateriaAEstudiante value) {
        return new JAXBElement<AgregarMateriaAEstudiante>(_AgregarMateriaAEstudiante_QNAME, AgregarMateriaAEstudiante.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaEstudiantesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "ListaEstudiantesResponse")
    public JAXBElement<ListaEstudiantesResponse> createListaEstudiantesResponse(ListaEstudiantesResponse value) {
        return new JAXBElement<ListaEstudiantesResponse>(_ListaEstudiantesResponse_QNAME, ListaEstudiantesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MateriasDelEstudianteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "MateriasDelEstudianteResponse")
    public JAXBElement<MateriasDelEstudianteResponse> createMateriasDelEstudianteResponse(MateriasDelEstudianteResponse value) {
        return new JAXBElement<MateriasDelEstudianteResponse>(_MateriasDelEstudianteResponse_QNAME, MateriasDelEstudianteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarMateria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://API/", name = "AgregarMateria")
    public JAXBElement<AgregarMateria> createAgregarMateria(AgregarMateria value) {
        return new JAXBElement<AgregarMateria>(_AgregarMateria_QNAME, AgregarMateria.class, null, value);
    }

}
