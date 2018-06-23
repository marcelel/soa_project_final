
package com.agh.edu.soap_client_simulator;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for elementTypeDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="elementTypeDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attributeAmountLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributeNameLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryTypeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="elements" type="{http://soap.edu.agh.com/}elementDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "elementTypeDto", propOrder = {
    "attributeAmountLabel",
    "attributeNameLabel",
    "categoryTypeId",
    "elements",
    "id",
    "name"
})
public class ElementTypeDto {

    protected String attributeAmountLabel;
    protected String attributeNameLabel;
    protected Integer categoryTypeId;
    @XmlElement(nillable = true)
    protected List<ElementDto> elements;
    protected Integer id;
    protected String name;

    /**
     * Gets the value of the attributeAmountLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeAmountLabel() {
        return attributeAmountLabel;
    }

    /**
     * Sets the value of the attributeAmountLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeAmountLabel(String value) {
        this.attributeAmountLabel = value;
    }

    /**
     * Gets the value of the attributeNameLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeNameLabel() {
        return attributeNameLabel;
    }

    /**
     * Sets the value of the attributeNameLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeNameLabel(String value) {
        this.attributeNameLabel = value;
    }

    /**
     * Gets the value of the categoryTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }

    /**
     * Sets the value of the categoryTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCategoryTypeId(Integer value) {
        this.categoryTypeId = value;
    }

    /**
     * Gets the value of the elements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElementDto }
     * 
     * 
     */
    public List<ElementDto> getElements() {
        if (elements == null) {
            elements = new ArrayList<ElementDto>();
        }
        return this.elements;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
