package com.rpc.auth.mapper;

import org.springframework.stereotype.Repository;

import com.rpc.auth.model.RolePermission;
import com.rpc.framework.mybatis.mapper.BaseMapper;

/**
 * 角色菜单许可Mapper
 */
@Repository
public interface RolePermissionMapper extends BaseMapper<String, RolePermission> {

    public RolePermission findRolePermission(RolePermission per);

}
