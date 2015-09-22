package com.rpc.auth.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rpc.auth.dto.PermissionDto;
import com.rpc.auth.model.Permission;

/**
 * 菜单工具类
 * 
 * @author Vincent.wang
 *
 */
public class MenuUtil implements Serializable {

    private static final long serialVersionUID = -2205059367852213007L;

    public static List<PermissionDto> getMenus(List<Permission> permissions) {
        if (permissions == null || permissions.size() == 0) {
            return null;
        }

        List<PermissionDto> pers = new ArrayList<PermissionDto>();
        PermissionDto pvo = null;
        for (Permission per : permissions) {
            if (StringUtils.equals(per.getMenuCode(), per.getParentId())) {
                pvo = new PermissionDto();
                pvo.setId(per.getId());
                pvo.setMenuName(per.getMenuName());
                pvo.setLev(per.getLev());
                pvo.setMenuCode(per.getMenuCode());
                pvo.setParentId(per.getParentId());
                pvo.setUrl(per.getUrl());
                pers.add(pvo);
            }
        }

        List<PermissionDto> children = null;
        // 给一级菜单绑定对应的二级菜单
        for (PermissionDto vo : pers) {
            children = new ArrayList<PermissionDto>();
            for (Permission p : permissions) {
                if (StringUtils.equals(vo.getMenuCode(), p.getParentId()) && !StringUtils.equals(p.getMenuCode(), p.getParentId())) {
                    pvo = new PermissionDto();
                    pvo.setId(p.getId());
                    pvo.setMenuName(p.getMenuName());
                    pvo.setLev(p.getLev());
                    pvo.setMenuCode(p.getMenuCode());
                    pvo.setParentId(p.getParentId());
                    pvo.setUrl(p.getUrl());
                    children.add(pvo);
                }
            }
            vo.setChildren(children);
        }
        return pers;
    }

}
