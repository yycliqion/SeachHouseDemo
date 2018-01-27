package com.weshep.house.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yycli
 */
@Controller
public class UserController {
    @GetMapping("/user/login")
    public String loginPage() {
        return "user/login";
    }
    @GetMapping("/user/cneter")
    public String centerPagr() {
        return "user/login";
    }
}
