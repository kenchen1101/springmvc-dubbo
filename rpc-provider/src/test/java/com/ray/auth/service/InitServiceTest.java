package com.ray.auth.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rpc.auth.model.Permission;
import com.rpc.auth.model.Role;
import com.rpc.auth.model.User;
import com.rpc.auth.service.AuthService;
import com.rpc.auth.util.MenuUtil;
import com.rpc.common.constants.Constants;

/**
 * @author Vincent.wang
 *
 *         production为生产环境，development为测试环境
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/framework/applicationContext.xml")
@ActiveProfiles("development")
public class InitServiceTest {

    @Autowired
    private AuthService authService;

    /**
     * 创建角色
     */
    private void addRoles() {
        try {
            Role system = new Role();
            system.setRoleName(Constants.SYSTEM_ROLE_NAME);// 系统管理员
            system.setRoleCode(Constants.SYSTEM_ROLE_CODE);
            system.setRemark(Constants.SYSTEM_ROLE_NAME);
            system.setParentId("0");
            authService.addRole(system);

            Role common = new Role();
            common.setRoleName(Constants.COMMON_ROLE_NAME);// 普通用户
            common.setRoleCode(Constants.COMMON_ROLE_CODE);
            common.setRemark(Constants.COMMON_ROLE_NAME);
            common.setParentId("0");
            authService.addRole(common);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建菜单
     */
    private void addPermission() {
        try {
            List<Permission> permis = MenuUtil.importPermissionData();
            for (Permission permission : permis) {
                authService.addPermission(permission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建用户
     */
    private void addUsers() {
        try {
            Role systemRole = authService.findRoleByCode(Constants.SYSTEM_ROLE_CODE);// 管理员
            Role commonRole = authService.findRoleByCode(Constants.COMMON_ROLE_CODE);// 普通用户
            String password = "123456";

            User admin = new User();
            admin.setLoginName("admin");
            admin.setEmail("infowangxin@163.com");
            admin.setUserName("管理员");
            admin.setPassword(password);
            authService.addUser(admin, systemRole);

            User wangxin = new User();
            wangxin.setLoginName("wangxin");
            wangxin.setUserName("王鑫");
            wangxin.setEmail("infowangxin@139.com");
            wangxin.setPassword(password);
            authService.addUser(wangxin, commonRole);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 给角色授权
    private void bindRolePermission() {
        try {
            // 系统管理员
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.user);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.useradd);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.upload);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.ajaxupload);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.springupload);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.download);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.zipupload);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.search);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.jquery_search);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.news);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.newsadd);
            authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, MenuUtil.news_search);

            // 普通用户
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.upload);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.ajaxupload);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.springupload);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.download);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.zipupload);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.search);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.jquery_search);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.news);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.newsadd);
            authService.addRolePermission(Constants.COMMON_ROLE_CODE, MenuUtil.news_search);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void init() {
        try {
            addRoles();
            addPermission();
            addUsers();
            bindRolePermission();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
