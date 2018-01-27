package com.weshep.house.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author yycli
 * @title 控制台ui
 */
@Controller
public class AdminController {
    @GetMapping("/admin/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }
}
