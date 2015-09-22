package com.rpc.auth.model;

import com.rpc.framework.base.BaseModel;

/**
 * 角色菜单许可关系
 */
public class RolePermission extends BaseModel {

    private static final long serialVersionUID = -7948507636703811294L;

    /** 角色ID **/
    private String roleId;

    /** 菜单ID **/
    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

}
