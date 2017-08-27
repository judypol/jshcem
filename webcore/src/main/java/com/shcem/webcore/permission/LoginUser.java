package com.shcem.webcore.permission;

/**
 * Created by lizhihua on 2017/3/2.
 */
public class LoginUser {
    private String UserCode;
    private String UserName;
    private String LoginName;
    private String ClientIp;
    private String AppName;
    private String Platform;
    private String[] Roles;
    private String Extension;       //扩展信息
    private int status;

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public String getUUID() {
        return UUID;
    }

    protected void setUUID(String UUID) {
        this.UUID = UUID;
    }

    private String UUID;

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getClientIp() {
        return ClientIp;
    }

    protected void setClientIp(String clientIp) {
        ClientIp = clientIp;
    }

    public String getAppName() {
        return AppName;
    }

    protected void setAppName(String appName) {
        AppName = appName;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String[] getRoles() {
        return Roles;
    }

    protected void setRoles(String[] roles) {
        Roles = roles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
