
package api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AgregarMateriaAEstudiante complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AgregarMateriaAEstudiante">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NombreMateria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CedulaEstudiante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgregarMateriaAEstudiante", propOrder = {
    "nombreMateria",
    "cedulaEstudiante"
})
public class AgregarMateriaAEstudiante {

    @XmlElement(name = "NombreMateria")
    protected String nombreMateria;
    @XmlElement(name = "CedulaEstudiante")
    protected String cedulaEstudiante;

    /**
     * Obtiene el valor de la propiedad nombreMateria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreMateria() {
        return nombreMateria;
    }

    /**
     * Define el valor de la propiedad nombreMateria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreMateria(String value) {
        this.nombreMateria = value;
    }

    /**
     * Obtiene el valor de la propiedad cedulaEstudiante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    /**
     * Define el valor de la propiedad cedulaEstudiante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedulaEstudiante(String value) {
        this.cedulaEstudiante = value;
    }

}
