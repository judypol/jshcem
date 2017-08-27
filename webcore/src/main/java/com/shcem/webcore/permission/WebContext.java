package com.shcem.webcore.permission;

import com.shcem.Encrypt.EncrytHelper;
import com.shcem.common.IRedisCache;
import com.shcem.common.RedisCacheManager;

import com.shcem.common.YamlConfiguration;
import com.shcem.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lizhihua on 2017/3/2.
 */
public class WebContext {

    private HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    private HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    private LoginUser CurrentUser;
    private boolean IsAnonymous;
    private static Logger logger = LoggerFactory.getLogger(WebContext.class);

    public HttpServletRequest getRequest() {
        return request;
    }

    public LoginUser getCurrentUser() {
        return CurrentUser;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * @Title: getIpAddress
     * @Description: 获取客户端IP地址
     * @param @return
     * @return String
     * @throws
     */
    public String getIpAddress() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if (ip == null) {
            ip = "localhost";
        }
        return ip;
    }

    private static ThreadLocal<WebContext> CurrentContext = new ThreadLocal<WebContext>();

    /**
     * 获取当前请求的上下文信息，包括用户信息
     * */
    public static WebContext GetCurrentContext() {
        WebContext webContext = CurrentContext.get();
        if (webContext == null) {
            webContext = new WebContext();
            webContext.CurrentUser = new LoginUser();
            String authKey = GetCookieKey();
            try {
                webContext.CurrentUser.setAppName(YamlConfiguration.instance().getProjectName());
                webContext.CurrentUser.setClientIp(webContext.getIpAddress());

                LoginUser user = GetLoginUser(webContext.getRequest(),webContext.getResponse());
                if (user != null) {
                    webContext.CurrentUser.setLoginName(user.getLoginName());
                    webContext.CurrentUser.setUserCode(user.getUserCode());
                    webContext.CurrentUser.setUserName(user.getUserName());
                    webContext.CurrentUser.setPlatform(user.getPlatform());
                    webContext.CurrentUser.setStatus(user.getStatus());
                }

            } catch (Exception e) {
                logger.info("WebContext-GetCurrentext", e);
            } finally {
                try {
                    RedisCacheManager.GetRedisCache().Close();
                } catch (Exception e) {
                    logger.debug("WebContext-GetCurrentext", e);
                }
            }
            CurrentContext.set(webContext);
        }
        return CurrentContext.get();
    }

    /**
     * 获取用户信息
     * */
    private static LoginUser GetLoginUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Cookie cookie= CookieUtils.getCookieByName(request,GetCookieKey());

        LoginUser user = null;
        try {
            user = GetLocalUser(cookie,response);
            if (user == null &&"false".equals(request.getAttribute("isAnonymous"))) {
                user = GetRemoteUser(request);
            }
        } catch (Exception ex) {
            logger.info("WebContext-GetLoginUser", ex);
        } finally {
            return user;
        }
    }

    /**
     * 从本地获取用户
     * */
    private static LoginUser GetLocalUser(Cookie cookie,HttpServletResponse response) throws Exception {
        if (cookie == null)
            return null;
        String cookieVal = cookie.getValue();
        if (cookieVal == null || cookieVal.isEmpty())
            return null;
        IRedisCache cache = RedisCacheManager.GetRedisCache();
        try {
            String redisKey=EncrytHelper.decryptBase64(cookieVal);
            LoginUser user = cache.Get(redisKey, LoginUser.class);
            if (user != null) {
                int expireTime = GetCookieExpire();

                cache.SetExpire(cookieVal, expireTime);
                cookie.setMaxAge(expireTime); 			// 设置过期时间
                cookie.setPath("/"); // 设置路径
                //response.
                response.addCookie(cookie);
            }
            return user;
        } catch (Exception e) {
            throw new Exception("GetLocalUser-error", e);
        } finally {
            cache.Close();
        }
    }

    /**
     * 从远程获取用户
     * */
    private static LoginUser GetRemoteUser(HttpServletRequest request) throws Exception {
        String ssoUrl =YamlConfiguration.instance().getString("ssoUrl");
        if (ssoUrl == null || ssoUrl.isEmpty())
            return null;
        if (IsSameDomain(request, ssoUrl)) {
            return null;
        }
        // ----查看url
        String authKey = "";
        String cookieParameter = request.getParameter("_c");
        String cookieAuth = request.getParameter("_a");

        if (cookieAuth == null || cookieAuth.isEmpty()) {
            authKey = GetRemoteAuthKey();
        } else {
            authKey = cookieParameter;
        }
        IRedisCache cache = RedisCacheManager.GetRedisCache();
        try {
            String redisKey=EncrytHelper.decryptBase64(authKey);
            LoginUser user = cache.Get(redisKey, LoginUser.class);
            if (user != null) {
                // SetUserCookie(user);
                WebContext context = new WebContext();
                HttpServletResponse response = context.getResponse();
                String cookieKey = GetCookieKey();
                int cookieExpire = GetCookieExpire();
                Cookie cookie = new Cookie(cookieKey, authKey);
                cookie.setHttpOnly(true);
                cookie.setMaxAge(cookieExpire); // 设置过期时间
                cookie.setPath("/"); // 设置路径
                response.addCookie(cookie);
            }
            return user;
        } catch (Exception e) {
            throw new Exception("GetRomoteUser-error", e);
        } finally {
            cache.Close();
        }
    }

    private static boolean IsSameDomain(HttpServletRequest request, String ssoUrl) throws Exception {
        boolean flag = false;
        try {
            String sourceUrl = request.getRequestURL().toString();
            URI sourceUri = new URI(sourceUrl);
            String sourceDomain = sourceUri.getHost() + ":" + sourceUri.getPort();

            URI aimUri = new URI(ssoUrl);
            String aimDomain = aimUri.getHost() + ":" + aimUri.getPort();
            if (sourceDomain.equals(aimDomain))
                flag = true;
        } catch (Exception e) {
            throw new Exception("IsSameDomain-error", e);
        }
        return flag;
    }

    private static String GetRemoteAuthKey() {
        WebContext context = new WebContext();
        HttpServletRequest request = context.getRequest();
        String gotoUrl = request.getRequestURL().toString();
        String ssoUrl =YamlConfiguration.instance().getString("ssoUrl") + "?_r=" + gotoUrl;
        HttpServletResponse response = context.getResponse();
        try {
            if(request.getParameter("_a")==null){
                response.sendRedirect(ssoUrl);
            }
        } catch (Exception e) {

        }

        return "";
    }

    private static String GetMode() {
        String mode = System.getProperty("mode");
        if (StringUtils.isBlank(mode) || StringUtils.isEmpty(mode)) {
            mode = "local";
        }
        return mode;
    }

    private static String GetCookieKey() {
        String CookieKey = GetMode() + "_authKey";
        return CookieKey;
    }

    /**
     * 获取过期时间,默认过期时间切一个月
     * */
    private static int GetCookieExpire() {
        int expireTime = 30 * 24 * 60 * 60;
        String expireText =YamlConfiguration.instance().getString("ssoCookie.expire");
        if (!expireText.isEmpty()) {
            expireTime = Integer.parseInt(expireText);
        }
        return expireTime;
    }

    /**
     * 将用户信息设置到cookie中,此方法只在登录页面用到，其他地方请不要使用此方法
     *
     * @param user
     *            用户信息
     * */
    public static void SetUserCookie(LoginUser user) throws Exception {
        IRedisCache cache = RedisCacheManager.GetRedisCache();
        try {
            WebContext context = new WebContext();
            HttpServletResponse response = context.getResponse();
            HttpServletRequest request = context.getRequest();
            // ---生成rediskey信息
            String redisKey = UUID.randomUUID().toString().replaceAll("-", "");
            user.setUUID(redisKey);

            String cookieValue = user.getUserCode() + "-" + redisKey + ".u";

            if ("1".equals(YamlConfiguration.instance().getString("ssoMode"))) {
                redisKey = YamlConfiguration.instance().getString("ssoScope") + "." + user.getUserCode();
                Set<String> keys = cache.FindKeys(redisKey + "*.u");
                if (keys != null) {
                    for (String key : keys) {
                        cache.DeleteKey(key);
                    }
                }
                cookieValue = redisKey + "-" + user.getUUID() + ".u";
            }
            //-----将cookie值编码---
            String cv= EncrytHelper.encryptBase64(cookieValue);
            String authKey = GetCookieKey();
            int cookieExpire = GetCookieExpire();
            Cookie cookie = new Cookie(authKey, cv);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(cookieExpire); 			// 设置过期时间
            cookie.setPath("/"); // 设置路径
            response.addCookie(cookie);

            cache.SetValue(cookieValue, user, cookieExpire);
        } catch (Exception e) {
            logger.info("WebContext-SetUserCookie", e);
        } finally {
            cache.Close();

        }
    }

    /**
     * 清空redis中的当前用户信息
     * */
    public static void ClearUserCookie() {
        // ----并清空redis user
        String authKey = GetCookieKey();
        WebContext webContext = new WebContext();
        Cookie cookie = CookieUtils.getCookieByName(webContext.getRequest(), authKey); // 获取redis中key对应的用户信息
        if (cookie != null) {
            try {
                String redisKey=EncrytHelper.decryptBase64(cookie.getValue());
                RedisCacheManager.GetRedisCache().DeleteKey(redisKey);
            } catch (Exception e) {
            } finally {
                try {
                    RedisCacheManager.GetRedisCache().Close();
                } catch (Exception e) {

                }
            }
        }
        HttpServletResponse response=webContext.getResponse();
        Cookie clearCookie=new Cookie(authKey,null);
        clearCookie.setPath("/");
        clearCookie.setMaxAge(0);
        response.addCookie(clearCookie);
    }

    /**
     * 清空当前用户信息
     * */
    public static void Clear() {
        CurrentContext.remove();
        CurrentContext.set(null);
    }

}
