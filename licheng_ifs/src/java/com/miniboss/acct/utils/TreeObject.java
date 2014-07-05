package com.miniboss.acct.utils;

import grails.converters.JSON;

import java.util.*;

/**
 * Copyright lr.
 * User: admin
 * Date: 2009-8-26
 * Time: 10:53:44
 */
public class TreeObject {
    List<TreeObject> children = new ArrayList<TreeObject>();
    Map<String, TreeObject> map = new HashMap<String, TreeObject>();
    TreeObject parent;
    private String id;
    private Map<String, String> data = new HashMap<String, String>();

    public void initChild(String id, String data) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", data);
        initChild(id, map, null);
    }

    public void initChildValue(String id, String key, String value) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        initChild(id, map, null);
    }

    public void initChild(String id, Map<String, String> data) {
        initChild(id, data, null);
    }

    public void setParent(String id, String parentId) {
        TreeObject obj = map.get(id);
        TreeObject parentObj = map.get(parentId);
        if (obj == null || parentObj == null) {
            return;
        }
        parentObj.children.add(obj);
        obj.parent = parentObj;
    }

    public void initChild(String id, String data, String parentId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", data);
        initChild(id, map, parentId);
    }

    public void initChild(String id, Map<String, String> data, String parentId) {
        TreeObject obj = map.get(id);
        if (obj == null) {
            obj = new TreeObject();
            map.put(id, obj);
        }
        obj.setId(id);
        obj.getData().putAll(data);
        if (parentId != null) {
            TreeObject parentObj = map.get(parentId);
            if (parentObj == null) {
                parentObj = new TreeObject();
                map.put(parentId, parentObj);
            }
            parentObj.setId(parentId);
            parentObj.children.add(obj);
            obj.parent = parentObj;
        }
    }

    public TreeObject getRoot() {
        Collection<TreeObject> treeObjectCollection = map.values();
        for (TreeObject treeObject : treeObjectCollection) {
            if (treeObject.parent == null)
                return treeObject;
        }
        return null;
    }

    public List<TreeObject> getRoots() {
        List<TreeObject> list = new ArrayList<TreeObject>();
        Collection<TreeObject> treeObjectCollection = map.values();
        for (TreeObject treeObject : treeObjectCollection) {
            if (treeObject.parent == null)
                list.add(treeObject);
        }
        return list;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public List<TreeObject> getChildren() {
        return children;
    }

    public TreeObject getParent() {
        return parent;
    }

    public String toJsTreeJson() {
        List treeList = new ArrayList();
        TreeObject root = this;
        if (id == null) {
            root = this.getRoot();
        }


        TreeObject rootTree = new TreeObject();
        rootTree.setId(root.id);
        rootTree.setData(root.data);

        rootTree.children.add(root);

        for (TreeObject child : rootTree.children) {
            getChild(child, treeList);
        }


        return new JSON(treeList).toString();
    }

    public String toJsTree() {
        List<TreeObject> treeList = new ArrayList<TreeObject>();
        List<TreeObject> roots = this.getRoots();
        for (TreeObject root : roots) {
            getChild(root, treeList);
        }

        return new JSON(treeList).toString();
    }

    @SuppressWarnings("unchecked")
    private static void getChild(TreeObject treeObject, List list) {
        Map map = new HashMap();
        Map attributeMap = new HashMap();
        String title = treeObject.data.get("title");
        if(title==null || title.trim().equals("")){
            title = treeObject.id;
        }
        attributeMap.put("id", treeObject.id);
        map.put("attributes", attributeMap);
        attributeMap.putAll(treeObject.data);
        attributeMap.remove("title");
        String icon = (String) attributeMap.remove("icon");
        Map titleMap = new HashMap();
        titleMap.put("title", title);
        titleMap.put("icon", icon);
        map.put("data", titleMap);

        List<TreeObject> children = treeObject.children;
        if (children.size() != 0) {
            List childList = new ArrayList();
            map.put("children", childList);
            for (TreeObject child : children) {
                getChild(child, childList);
            }
        }
        list.add(map);
    }

}
