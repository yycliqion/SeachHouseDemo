package com.weshep.house.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yycli
 * @title 登录验证失败处理
 */
public class LoginAuthFailedHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * @title 获取跳转目标url
     */
    private final LoginUrlEntryPoint urlEntryPoint;


    public LoginAuthFailedHandler(LoginUrlEntryPoint urlEntryPoint) {
        this.urlEntryPoint = urlEntryPoint;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String targetUrl =
                this.urlEntryPoint.determineUrlToUseForThisRequest(request, response, exception);
        targetUrl += "?" + exception.getMessage();
        super.setDefaultFailureUrl(targetUrl);
        super.onAuthenticationFailure(request, response, exception);
    }
}
