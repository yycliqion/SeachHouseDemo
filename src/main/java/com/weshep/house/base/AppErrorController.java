package com.weshep.house.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yycli
 * @title 全局自定义错误页面处理
 */
@Controller
public class AppErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @Autowired
    public AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * web页面错误处理
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorPageHandle(HttpServletRequest request,
                                  HttpServletResponse response) {
        int status = response.getStatus();
        switch (status) {
            case 403:
                return "error/403";
            case 404:
                return "error/404";
            case 500:
                return "error/505";
            default:
                return "error/index";
        }
    }

    /**
     * 非web页面错误处理，json，xml等
     */
    @RequestMapping(value = "ERROR_PATH")
    @ResponseBody
    public ApiResponse errorApiHandle(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);

        Map<String, Object> attr = this
                .errorAttributes
                .getErrorAttributes(requestAttributes, false);

        int status = getStatus(request);

        return ApiResponse.ofMessage(status,
                String.valueOf(attr.getOrDefault("message", "error")));

    }

    public int getStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            return status;
        }
        return 500;
    }
}
