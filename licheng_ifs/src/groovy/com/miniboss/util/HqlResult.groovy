package com.miniboss.util

import com.miniboss.acct.utils.DateUtil

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-11-25
 * Time: 11:19:54
 * To change this template use File | Settings | File Templates.
 */

public class HqlResult {
  String hql = "";
  def paramValueMap = [:];

  public HqlResult() {

  }

  public HqlResult(List params, Map grailsParamertMap, String operator) {
    this.set(params, grailsParamertMap, operator, "and")
  }

  public HqlResult(String param, Map grailsParamertMap, String hql) {
    Object value = getValue(param, grailsParamertMap)
    if (value != null) {
      this.hql = hql
      paramValueMap.put(param, value);
    }
  }

  public static HqlResult getAnds(List params, Map grailsParamertMap, String operator) {
    HqlResult hqlResult = new HqlResult();
    return hqlResult.set(params, grailsParamertMap, operator, "and")
  }

  public static HqlResult getOrs(List params, Map grailsParamertMap, String operator) {
    HqlResult hqlResult = new HqlResult();
    return hqlResult.set(params, grailsParamertMap, operator, "or")
  }

  public HqlResult or(HqlResult hqlResult) {
    if (hqlResult.hql.length() > 0) {
      if (hql.length() == 0) {
        hql += " (" + hqlResult.hql + ")"
      } else {
        hql += " or (" + hqlResult.hql + ")"
      }
      paramValueMap.putAll(hqlResult.paramValueMap);
    }
    return this;
  }

  public HqlResult and(HqlResult hqlResult) {
    if (hqlResult.hql.length() > 0) {
      if (hql.length() == 0) {
        hql += " (" + hqlResult.hql + ")"
      } else {
        hql += " and (" + hqlResult.hql + ")"
      }
      paramValueMap.putAll(hqlResult.paramValueMap);
    }
    return this;
  }

  private HqlResult set(List params, Map grailsParamertMap, String operator, String logic) {
    StringBuffer sb = new StringBuffer("");
    boolean firstRecord = true;
    for (int i = 0; i < params.size(); i++) {
      String param = (String) params.get(i);
      Object value = getValue(param, grailsParamertMap)
      if (value != null) {
        if (firstRecord) {
          sb.append(' o.' + param + ' ' + operator + ' :' + param)
        } else {
          sb.append(' ' + logic + ' o.' + param + ' ' + operator + ' :' + param)
        }
        if (operator.equals("like")) {
          value = "%" + value + "%"
        }
        paramValueMap.put(param, value)
        firstRecord = false;
      }
    }
    if (sb.toString().length() > 0) {
      hql += " (" + sb.toString() + ")";
    }
    return this;
  }

  private Object getValue(String param, Map grailsParamertMap) {
    Object value = grailsParamertMap.get(param)
    if (value != null && value.trim().length() > 0) {
      value = value.trim();
      if (grailsParamertMap.containsKey(param + "_value")) {
        value = DateUtil.getGrailsParamterDate(grailsParamertMap, param)
      }
      return value;
    }
    return null;
  }


}