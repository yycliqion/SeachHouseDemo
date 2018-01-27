package com.weshep.house.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yycli
 * @title 角色登录控制器
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private PathMatcher pathMatcher = new AntPathMatcher();

    private final Map<String, String> anthEntryPointMap;

    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        anthEntryPointMap = new HashMap<>();

        //普通用户登录入口映射
        anthEntryPointMap.put("/user/**", "/user/login");

        //管理员登录入口映射
        anthEntryPointMap.put("/admin/**", "/admin/login");
    }

    /**
     * @param request
     * @param response
     * @param exception
     * @return
     * @title 根据请求跳转到指定的页面，父类默认使用loginFormUrl
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     AuthenticationException exception) {
        /** 获取初始跳转url */
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        for (Map.Entry<String, String> authEntry : this.anthEntryPointMap.entrySet()) {
            if (this.pathMatcher.match(authEntry.getKey(), uri)){
                return authEntry.getValue();
            }
        }

        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
