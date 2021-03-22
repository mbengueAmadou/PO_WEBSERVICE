//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2021.02.16 à 01:14:48 PM UTC 
//


package sn.isi.m2gl.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nbrtest" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="postifcase" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="importedCase" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="death" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="recovered" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nbrtest",
    "postifcase",
    "importedCase",
    "death",
    "recovered",
    "date"
})
@XmlRootElement(name = "getCovid19InfoResponse")
public class GetCovid19InfoResponse {

    @XmlElement(required = true)
    protected String nbrtest;
    @XmlElement(required = true)
    protected String postifcase;
    @XmlElement(required = true)
    protected String importedCase;
    @XmlElement(required = true)
    protected String death;
    @XmlElement(required = true)
    protected String recovered;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;

    /**
     * Obtient la valeur de la propriété nbrtest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNbrtest() {
        return nbrtest;
    }

    /**
     * Définit la valeur de la propriété nbrtest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNbrtest(String value) {
        this.nbrtest = value;
    }

    /**
     * Obtient la valeur de la propriété postifcase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostifcase() {
        return postifcase;
    }

    /**
     * Définit la valeur de la propriété postifcase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostifcase(String value) {
        this.postifcase = value;
    }

    /**
     * Obtient la valeur de la propriété importedCase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportedCase() {
        return importedCase;
    }

    /**
     * Définit la valeur de la propriété importedCase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportedCase(String value) {
        this.importedCase = value;
    }

    /**
     * Obtient la valeur de la propriété death.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeath() {
        return death;
    }

    /**
     * Définit la valeur de la propriété death.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeath(String value) {
        this.death = value;
    }

    /**
     * Obtient la valeur de la propriété recovered.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecovered() {
        return recovered;
    }

    /**
     * Définit la valeur de la propriété recovered.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecovered(String value) {
        this.recovered = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

}
