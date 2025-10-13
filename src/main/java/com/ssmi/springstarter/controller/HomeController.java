package com.ssmi.springstarter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.ssmi.springstarter.controller
 * fileName       : HomeController
 * author         : ssmihs
 * date           : 2025-10-13
 * description    :
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
