package com.rpc.auth.model;

import com.rpc.framework.base.BaseModel;

/**
 * 用户对象
 *
 */
public class User extends BaseModel {

    private static final long serialVersionUID = -1083711780724721275L;

    /** email **/
    private String email;

    /** 登录名 **/
    private String loginName;

    /** 用户名 **/
    private String userName;

    /** 密码 **/
    private String password;

    /** salt码 **/
    private String salt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}