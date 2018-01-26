package com.example.demo.configuration;


import com.shcem.webcore.permission.PermissionCheck;
import com.shcem.webcore.permission.authentication.AuthenticationToken;
import com.shcem.webcore.permission.exceptions.AuthenticationException;
import com.shcem.webcore.permission.exceptions.NoPermissionException;

public class MyPermissionCheck implements PermissionCheck {
    /**
     * check是否有权限，必须在子类中实现
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException 没有认证（在系统中没有注册过），
     * @throws NoPermissionException   没有权限
     */
    @Override
    public boolean process(AuthenticationToken authenticationToken) throws AuthenticationException, NoPermissionException {
        return false;
    }
}
