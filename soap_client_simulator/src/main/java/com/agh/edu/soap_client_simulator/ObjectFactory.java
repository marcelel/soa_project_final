
package com.agh.edu.soap_client_simulator;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.agh.edu.soap_client_simulator package. 
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

    private final static QName _AddNewElementTypeResponse_QNAME = new QName("http://soap.edu.agh.com/", "addNewElementTypeResponse");
    private final static QName _AddNewElementType_QNAME = new QName("http://soap.edu.agh.com/", "addNewElementType");
    private final static QName _CategoryTypeDto_QNAME = new QName("http://soap.edu.agh.com/", "categoryTypeDto");
    private final static QName _GetElementDtoById_QNAME = new QName("http://soap.edu.agh.com/", "getElementDtoById");
    private final static QName _ElementDto_QNAME = new QName("http://soap.edu.agh.com/", "elementDto");
    private final static QName _GetElementDtoByIdResponse_QNAME = new QName("http://soap.edu.agh.com/", "getElementDtoByIdResponse");
    private final static QName _EditElementResponse_QNAME = new QName("http://soap.edu.agh.com/", "editElementResponse");
    private final static QName _EditElement_QNAME = new QName("http://soap.edu.agh.com/", "editElement");
    private final static QName _AddNewCategoryType_QNAME = new QName("http://soap.edu.agh.com/", "addNewCategoryType");
    private final static QName _AddNewCategoryTypeResponse_QNAME = new QName("http://soap.edu.agh.com/", "addNewCategoryTypeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.agh.edu.soap_client_simulator
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ElementDto }
     * 
     */
    public ElementDto createElementDto() {
        return new ElementDto();
    }

    /**
     * Create an instance of {@link GetElementDtoByIdResponse }
     * 
     */
    public GetElementDtoByIdResponse createGetElementDtoByIdResponse() {
        return new GetElementDtoByIdResponse();
    }

    /**
     * Create an instance of {@link EditElement }
     * 
     */
    public EditElement createEditElement() {
        return new EditElement();
    }

    /**
     * Create an instance of {@link EditElementResponse }
     * 
     */
    public EditElementResponse createEditElementResponse() {
        return new EditElementResponse();
    }

    /**
     * Create an instance of {@link AddNewCategoryType }
     * 
     */
    public AddNewCategoryType createAddNewCategoryType() {
        return new AddNewCategoryType();
    }

    /**
     * Create an instance of {@link AddNewCategoryTypeResponse }
     * 
     */
    public AddNewCategoryTypeResponse createAddNewCategoryTypeResponse() {
        return new AddNewCategoryTypeResponse();
    }

    /**
     * Create an instance of {@link AddNewElementType }
     * 
     */
    public AddNewElementType createAddNewElementType() {
        return new AddNewElementType();
    }

    /**
     * Create an instance of {@link CategoryTypeDto }
     * 
     */
    public CategoryTypeDto createCategoryTypeDto() {
        return new CategoryTypeDto();
    }

    /**
     * Create an instance of {@link AddNewElementTypeResponse }
     * 
     */
    public AddNewElementTypeResponse createAddNewElementTypeResponse() {
        return new AddNewElementTypeResponse();
    }

    /**
     * Create an instance of {@link GetElementDtoById }
     * 
     */
    public GetElementDtoById createGetElementDtoById() {
        return new GetElementDtoById();
    }

    /**
     * Create an instance of {@link ElementTypeDto }
     * 
     */
    public ElementTypeDto createElementTypeDto() {
        return new ElementTypeDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewElementTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "addNewElementTypeResponse")
    public JAXBElement<AddNewElementTypeResponse> createAddNewElementTypeResponse(AddNewElementTypeResponse value) {
        return new JAXBElement<AddNewElementTypeResponse>(_AddNewElementTypeResponse_QNAME, AddNewElementTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "addNewElementType")
    public JAXBElement<AddNewElementType> createAddNewElementType(AddNewElementType value) {
        return new JAXBElement<AddNewElementType>(_AddNewElementType_QNAME, AddNewElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryTypeDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "categoryTypeDto")
    public JAXBElement<CategoryTypeDto> createCategoryTypeDto(CategoryTypeDto value) {
        return new JAXBElement<CategoryTypeDto>(_CategoryTypeDto_QNAME, CategoryTypeDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetElementDtoById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "getElementDtoById")
    public JAXBElement<GetElementDtoById> createGetElementDtoById(GetElementDtoById value) {
        return new JAXBElement<GetElementDtoById>(_GetElementDtoById_QNAME, GetElementDtoById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElementDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "elementDto")
    public JAXBElement<ElementDto> createElementDto(ElementDto value) {
        return new JAXBElement<ElementDto>(_ElementDto_QNAME, ElementDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetElementDtoByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "getElementDtoByIdResponse")
    public JAXBElement<GetElementDtoByIdResponse> createGetElementDtoByIdResponse(GetElementDtoByIdResponse value) {
        return new JAXBElement<GetElementDtoByIdResponse>(_GetElementDtoByIdResponse_QNAME, GetElementDtoByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditElementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "editElementResponse")
    public JAXBElement<EditElementResponse> createEditElementResponse(EditElementResponse value) {
        return new JAXBElement<EditElementResponse>(_EditElementResponse_QNAME, EditElementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditElement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "editElement")
    public JAXBElement<EditElement> createEditElement(EditElement value) {
        return new JAXBElement<EditElement>(_EditElement_QNAME, EditElement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "addNewCategoryType")
    public JAXBElement<AddNewCategoryType> createAddNewCategoryType(AddNewCategoryType value) {
        return new JAXBElement<AddNewCategoryType>(_AddNewCategoryType_QNAME, AddNewCategoryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCategoryTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.edu.agh.com/", name = "addNewCategoryTypeResponse")
    public JAXBElement<AddNewCategoryTypeResponse> createAddNewCategoryTypeResponse(AddNewCategoryTypeResponse value) {
        return new JAXBElement<AddNewCategoryTypeResponse>(_AddNewCategoryTypeResponse_QNAME, AddNewCategoryTypeResponse.class, null, value);
    }

}
