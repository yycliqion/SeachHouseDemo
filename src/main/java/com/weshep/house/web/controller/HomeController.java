package com.weshep.house.web.controller;

import com.weshep.house.base.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yycli
 * @title 主路径控制器
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "六六六1");
        return "index";
    }

    @GetMapping("/logout/page")
    public String logoutPage() {
        return "logout";
    }
}
