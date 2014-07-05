package com.miniboss.call.vo;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.*;
import java.util.Hashtable;
import java.util.*;

/**
 * <p>Title: SMS</p>
 * <p>Description: 数字电视用户管理系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: http://www.dvnchina.com</p>
 *
 * @author not attributable
 * @version 4.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AreaDetail", namespace = "http://vo.call.sms.dvnchina.com")
public class AreaDetail {
    private String areaID; /* 地区编号 */
    private String areaNM; /* 地区名称 */
    private String parentAreaID; /* 父地区编号 */
    private String areaType; /* 地区类型 */
    private String status; /* 状态 */

    public String getAreaID() {
        return areaID;
    }

    public void setAreaID(String areaID) {
        this.areaID = areaID;
    }

    public String getAreaNM() {
        return areaNM;
    }

    public void setAreaNM(String areaNM) {
        this.areaNM = areaNM;
    }

    public String getParentAreaID() {
        return parentAreaID;
    }

    public void setParentAreaID(String parentAreaID) {
        this.parentAreaID = parentAreaID;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}