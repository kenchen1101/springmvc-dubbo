package com.rpc.auth.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.rpc.auth.dto.PermissionDto;
import com.rpc.auth.model.Permission;
import com.rpc.common.constants.Constants;

/**
 * 菜单工具类
 * 
 * @author Vincent.wang
 *
 */
public class MenuUtil implements Serializable {

    private static final long serialVersionUID = -2205059367852213007L;

    public static final String user = "user";
    public static final String useradd = "register";
    public static final String upload = "upload";
    public static final String ajaxupload = "ajaxupload";
    public static final String springupload = "springupload";
    public static final String download = "download";
    public static final String zipupload = "zipupload";
    public static final String search = "search";
    public static final String jquery_search = "jquerysearch";
    public static final String news = "news";
    public static final String newsadd = "newsadd";
    public static final String news_search = "newssearch";

    public static List<Permission> importPermissionData() {
        List<Permission> permis = new ArrayList<Permission>();

        permis.add(initPermission("文件上传", upload, Constants.STATUS_VALID, null, 1, upload));
        permis.add(initPermission("ajax上传文件", ajaxupload, Constants.STATUS_VALID, "upload/ajax", 2, upload));
        permis.add(initPermission("spring上传文件", springupload, Constants.STATUS_VALID, "upload/spring", 3, upload));

        permis.add(initPermission("文件下载", download, Constants.STATUS_VALID, null, 101, download));
        permis.add(initPermission("zip下载", zipupload, Constants.STATUS_VALID, "download/zip", 102, download));

        permis.add(initPermission("用户管理", user, Constants.STATUS_VALID, null, 201, user));
        permis.add(initPermission("新增", useradd, Constants.STATUS_VALID, "user/register", 202, user));

        permis.add(initPermission("JQuery", search, Constants.STATUS_VALID, null, 301, search));
        permis.add(initPermission("快速查询", jquery_search, Constants.STATUS_VALID, "jq", 302, search));

        permis.add(initPermission("新闻", news, Constants.STATUS_VALID, null, 401, news));
        permis.add(initPermission("新增新闻", newsadd, Constants.STATUS_VALID, "news/add", 402, news));
        permis.add(initPermission("新增列表", news_search, Constants.STATUS_VALID, "news/search", 403, news));

        return permis;
    }

    private static Permission initPermission(String name, String key, Integer hide, String url, Integer sort, String parentKey) {
        Permission per = new Permission();
        per.setMenuName(name);
        per.setMenuCode(key);
        // per.setHide(hide);
        per.setUrl(url);
        per.setSort(sort);
        per.setParentId(parentKey);
        return per;
    }

    public static List<PermissionDto> getMenus(List<Permission> permissions) {

        if (permissions == null || permissions.size() == 0) {
            return null;
        }

        List<PermissionDto> pers = new ArrayList<PermissionDto>();

        // 筛选出一级菜单
        PermissionDto pvo = null;
        for (Permission per : permissions) {
            if (StringUtils.equals(per.getMenuCode(), per.getParentId())) {
                pvo = new PermissionDto();
                pvo.setId(per.getId());
                pvo.setMenuName(per.getMenuName());
                // pvo.setHide(per.getHide());
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
                    // pvo.setHide(p.getHide());
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

    public static Permission getUserCententMenu() {
        return null;
    }

}
