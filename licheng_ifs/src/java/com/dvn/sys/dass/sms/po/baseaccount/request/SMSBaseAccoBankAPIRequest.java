//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-325
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.07.10 at 07:23:56 ���� CST
//


package com.dvn.sys.dass.sms.po.baseaccount.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{}uuid"/>
 *         &lt;element name="ChargeAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModelID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BillingCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NetId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Remark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "uuid",
    "chargeAccount",
    "modelID",
    "transactionID",
    "billingCount",
    "netId",
    "remark"
})
@XmlRootElement(name = "SMSBaseAccoBankAPI-Request")
public class SMSBaseAccoBankAPIRequest {

    @XmlElement(name = "UUID", required = true)
    protected String uuid;
    @XmlElement(name = "ChargeAccount", required = true)
    protected String chargeAccount;
    @XmlElement(name = "ModelID", required = true)
    protected String modelID;
    @XmlElement(name = "TransactionID", required = true)
    protected String transactionID;
    @XmlElement(name = "BillingCount", required = true)
    protected String billingCount;
    @XmlElement(name = "NetId", required = true)
    protected String netId;
    @XmlElement(name = "Remark", required = true)
    protected String remark;

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUUID(String value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the chargeAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeAccount() {
        return chargeAccount;
    }

    /**
     * Sets the value of the chargeAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeAccount(String value) {
        this.chargeAccount = value;
    }

    /**
     * Gets the value of the modelID property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getModelID() {
        return modelID;
    }

    /**
     * Sets the value of the modelID property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setModelID(String value) {
        this.modelID = value;
    }

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the billingCount property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBillingCount() {
        return billingCount;
    }

    /**
     * Sets the value of the billingCount property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBillingCount(String value) {
        this.billingCount = value;
    }

    /**
     * Gets the value of the netId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetId() {
        return netId;
    }

    /**
     * Sets the value of the netId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetId(String value) {
        this.netId = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}
