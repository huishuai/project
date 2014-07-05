package com.miniboss.util
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-10-12
 * Time: 13:55:25
 * To change this template use File | Settings | File Templates.
 */

public class AjaxUtil {
    static Map AJAX_REQUEST_LINK_MAP = [
            delete: "delete",
            update: "update",
            save: "save",
            jsonList: "jsonList",
            selectJsonList: "selectJsonList"
    ]

    static Map NEED_SESSION_MAP = [
            create: "create",
            edit: "edit",
            index: "index",
            list: "list",
            search: "search",
            personalCustom: "personalCustom",
            groupAuthService: "groupAuthService",
            companyAuthService: "companyAuthService",
            companyCreate: "companyCreate",
            groupCreate: "groupCreate",
            choose: "choose",
            customerLogin: "customerLogin",
            changeWare: "changeWare",
            pair: "pair",
            backWare: "backWare",
            show: "show"
    ]

    public static boolean hasAjax(String action) {
        boolean result = false;
        if (AJAX_REQUEST_LINK_MAP[action] || action.endsWith("Ajax")) {
            result = true;
        }
        return result;
    }

    public static boolean needSession(String action) {
        boolean result = false;
        if (NEED_SESSION_MAP[action]) {
            result = true;
        }
        return result;
    }
}