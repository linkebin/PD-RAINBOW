package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by zcb on 2017/9/4.
 */
@Table(name = "sec_menu_org")
public class SecMenuOrg {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 角色ID
     */
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 菜单ID
     */
    @Column(name = "ORG_ID")
    private String orgId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
