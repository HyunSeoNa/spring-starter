package com.ssmi.springstarter.controller;

import com.ssmi.springstarter.service.MemberService;
import org.springframework.stereotype.Controller;

/**
 * packageName    : com.ssmi.springstarter.controller
 * fileName       : MemberController
 * author         : ssmihs
 * date           : 2025-10-13
 * description    :
 */
@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
