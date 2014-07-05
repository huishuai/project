package com.miniboss.util

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsDomainClass
import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty
import org.codehaus.groovy.grails.web.converters.ConverterUtil
import org.springframework.beans.BeanWrapper
import org.springframework.beans.BeanWrapperImpl

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 2009-8-7
 * Time: 14:49:22
 * To change this template use File | Settings | File Templates.
 */

public class JsonUtil {

    public static JSON getJson(int page, int total, int recordNum, List showList) {
        return getJson(page, total, recordNum, showList, null)
    }

    public static JSON getJson(int page, int total, int recordNum, List objList, Closure c) {
        def map = new HashMap()
        map.put("page", page);
        map.put("total", total);
        def list = new ArrayList()
        for (int i = 0; i < objList?.size(); i++) {
            Object obj = objList.get(i)
            def beanMap = [:]
            Class clazz = obj.getClass();
            GrailsDomainClass domainClass = ConverterUtil.getDomainClass(clazz.getName());
            BeanWrapper beanWrapper = new BeanWrapperImpl(obj);

            GrailsDomainClassProperty id = domainClass.getIdentifier();
            Object idValue = beanWrapper.getPropertyValue(id.getName());
            beanMap.put("id", idValue != null ? String.valueOf(idValue) : null)

            GrailsDomainClassProperty[] properties = domainClass.getPersistentProperties();
            for (GrailsDomainClassProperty property: properties) {
                beanMap.put(property.getName(), beanWrapper.getPropertyValue(property.getName()));
            }
            if (c != null) {
                c.call(obj, beanMap)
            }

            list.add(beanMap)
        }

        map.put("rows", list);
        map.put("records", recordNum);

        JSON json = new JSON(map)
        return json;
    }

    public JSON getJsonByMap(int page, int total, int recordNum, List objList, List<Map> personalList) {
        def map = new HashMap()
        map.put("page", page);
        map.put("total", total);
        def list = new ArrayList()
        for (int i = 0; i < objList.size(); i++) {
            def beanMap = [:]
            Class clazz = objList[i].getClass();
            GrailsDomainClass domainClass = ConverterUtil.getDomainClass(clazz.getName());
            BeanWrapper beanWrapper = new BeanWrapperImpl(objList[i]);

            GrailsDomainClassProperty id = domainClass.getIdentifier();
            Object idValue = beanWrapper.getPropertyValue(id.getName());
            beanMap.put("id", idValue != null ? String.valueOf(idValue) : null)

            GrailsDomainClassProperty[] properties = domainClass.getPersistentProperties();
            for (GrailsDomainClassProperty property: properties) {
                beanMap.put(property.getName(), beanWrapper.getPropertyValue(property.getName()));
            }
            def personalMap = personalList.get(i)
            beanMap.putAll(personalMap)
            list.add(beanMap)
        }

        map.put("rows", list);
        map.put("records", recordNum);

        JSON json = new JSON(map)
        return json;
    }


}