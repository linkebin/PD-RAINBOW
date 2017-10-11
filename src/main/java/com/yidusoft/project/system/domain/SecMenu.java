package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "sec_menu")
public class SecMenu {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 菜单编码
     */
    @Column(name = "MENU_CODE")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 父菜单ID
     */
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 菜单类型
1:菜单
2:按钮
     */
    @Column(name = "MENU_TYPE")
    private Integer menuType;

    /**
     * 打开方式
1:模态窗口
2:open新页面
     */
    @Column(name = "OPEN_TYPE")
    private Integer openType;

    /**
     * 连接地址
     */
    @Column(name = "URL")
    private String url;

    /**
     * 菜单图标
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 是否可用  0否  1是
     */
    @Column(name = "ENABLED")
    private Integer enabled;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "DELETED")
    private Integer deleted;

    @Column(name = "SORT")
    private Integer sort;

    public Integer  getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取主键ID
     *
     * @return ID_ - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取菜单编码
     *
     * @return MENU_CODE - 菜单编码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编码
     *
     * @param menuCode 菜单编码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 获取菜单名称
     *
     * @return MENU_NAME - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取父菜单ID
     *
     * @return PARENT_ID - 父菜单ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父菜单ID
     *
     * @param parentId 父菜单ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单类型
1:菜单
2:按钮
     *
     * @return MENU_TYPE - 菜单类型
1:菜单
2:按钮
     */
    public Integer getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型
1:菜单
2:按钮
     *
     * @param menuType 菜单类型
1:菜单
2:按钮
     */
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取打开方式
1:模态窗口
2:open新页面
     *
     * @return OPEN_TYPE - 打开方式
1:模态窗口
2:open新页面
     */
    public Integer getOpenType() {
        return openType;
    }

    /**
     * 设置打开方式
1:模态窗口
2:open新页面
     *
     * @param openType 打开方式
1:模态窗口
2:open新页面
     */
    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    /**
     * 获取连接地址
     *
     * @return URL - 连接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置连接地址
     *
     * @param url 连接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取是否可用  0否  1是
     *
     * @return ENABLED - 是否可用  0否  1是
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用  0否  1是
     *
     * @param enabled 是否可用  0否  1是
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * @return CREATOR
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return DELETED
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Transient
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}