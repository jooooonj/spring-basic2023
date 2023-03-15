package com.ll.basic1.controller.member;

import com.ll.basic1.entity.Result;
import com.ll.basic1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("member/login")
    @ResponseBody
    public Result login(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String password) {
        return memberService.tryLogin(username, password);
    }
}

