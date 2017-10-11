package com.yidusoft.utils;

import java.util.List;

/**
 * Created by you on 2017/7/18.
 */
public class IndexTreeNode {
//    name: "任务管理", icon: "", id: "4", childs: url parentId

    private String id;

    private String parentId;

    private String name;

    private String  icon;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private List<IndexTreeNode> childs;

    public IndexTreeNode(String id, String name, String parentId, String icon, String url) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IndexTreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<IndexTreeNode> children) {
        this.childs = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", children=" + childs +
                '}';
    }
}
