package com.ray.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.rpc.common.constants.Constants;
import com.rpc.framework.key.GeneratePrimaryKey;

/**
 * @author Vincent.wang
 *
 *         production为生产环境，development为测试环境
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-dao-datasource.xml", "classpath*:spring/applicationContext-dao-transaction.xml", "classpath*:spring/applicationContext-dubbo.xml",
    "classpath*:spring/applicationContext-mybatis.xml", "classpath*:spring/applicationContext-redis.xml", "classpath*:spring/applicationContext.xml", "classpath*:spring/service/applicationContext-*.xml" })
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

    public static List<Permission> importPermissionData() {
        List<Permission> permis = new ArrayList<Permission>();

        Permission a = newPermission("权限模块", "auth", null, null, 1, 1);

        Permission aa = newPermission("角色", a.getMenuCode() + "_role", a.getId(), null, 2, 2);
        aa.setMenuCode(a.getId() + aa.getId());

        Permission ab = newPermission("用户", a.getMenuCode() + "_user", a.getId(), null, 2, 3);
        ab.setMenuCode(a.getId() + ab.getId());

        Permission ac = newPermission("菜单", a.getMenuCode() + "_menu", a.getId(), null, 2, 4);
        ac.setMenuCode(a.getId() + ac.getId());

        Permission aaa = newPermission("角色管理", aa.getMenuCode() + "_manager", aa.getParentId(), "role/manager", 2, 5);
        aaa.setMenuCode(aa.getParentId() + aaa.getId());

        Permission aba = newPermission("用户管理", ab.getMenuCode() + "_manager", ab.getParentId(), "user/manager", 2, 6);
        aba.setMenuCode(ab.getParentId() + aba.getId());

        Permission aca = newPermission("菜单管理", ac.getMenuCode() + "_manager", ac.getParentId(), "menu/manager", 2, 7);
        aca.setMenuCode(ac.getParentId() + aca.getId());

        permis.add(a);
        permis.add(aa);
        permis.add(ab);
        permis.add(ac);
        permis.add(aaa);
        permis.add(aba);
        permis.add(aca);
        return permis;
    }

    private synchronized static Permission newPermission(String menuName, String menuCode, String parentId, String url, Integer lev, Integer sort) {
        Permission per = new Permission();
        String id = GeneratePrimaryKey.getPkValue("t_auth_permission");
        per.setId(id);
        per.setMenuCode(menuCode);
        if (StringUtils.isBlank(parentId)) {
            per.setParentId(id);
        } else {
            per.setParentId(parentId + id);
        }
        System.out.println(id);
        System.err.println(per.getParentId());
        per.setMenuName(menuName);
        per.setLev(lev);
        per.setUrl(url);
        per.setSort(sort);
        return per;
    }

    /**
     * 创建菜单
     */
    private void addPermission() {
        try {
            Permission a = newPermission("权限模块", "auth", null, null, 1, 1);
            authService.addPermission(a);

            Permission aa = newPermission("角色", a.getMenuCode() + "_role", a.getId(), null, 2, 2);
            aa.setMenuCode(a.getId() + aa.getId());
            authService.addPermission(aa);

            Permission ab = newPermission("用户", a.getMenuCode() + "_user", a.getId(), null, 2, 3);
            ab.setMenuCode(a.getId() + ab.getId());
            authService.addPermission(ab);

            Permission ac = newPermission("菜单", a.getMenuCode() + "_menu", a.getId(), null, 2, 4);
            ac.setMenuCode(a.getId() + ac.getId());
            authService.addPermission(ac);

            Permission aaa = newPermission("角色管理", aa.getMenuCode() + "_manager", aa.getParentId(), "role/manager", 2, 5);
            aaa.setMenuCode(aa.getParentId() + aaa.getId());
            authService.addPermission(aaa);

            Permission aba = newPermission("用户管理", ab.getMenuCode() + "_manager", ab.getParentId(), "user/manager", 2, 6);
            aba.setMenuCode(ab.getParentId() + aba.getId());
            authService.addPermission(aba);

            Permission aca = newPermission("菜单管理", ac.getMenuCode() + "_manager", ac.getParentId(), "menu/manager", 2, 7);
            aca.setMenuCode(ac.getParentId() + aca.getId());
            authService.addPermission(aca);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建用户
     */
    private void addUsers() {
        try {
            Role commonRole = authService.findRoleByCode(Constants.COMMON_ROLE_CODE);// 普通用户
            User wangxin = new User();
            wangxin.setLoginName("wangxin");
            wangxin.setUserName("王鑫");
            wangxin.setEmail("infowangxin@139.com");
            wangxin.setPassword("123456");
            authService.addUser(wangxin, commonRole);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 给角色授权
    private void bindRolePermission() {
        try {
            List<Permission> pers = authService.getPermissions();
            for (Permission p : pers) {
                authService.addRolePermission(Constants.SYSTEM_ROLE_CODE, p.getParentId()); // 系统管理员
                authService.addRolePermission(Constants.COMMON_ROLE_CODE, p.getParentId()); // 普通用户
            }
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
