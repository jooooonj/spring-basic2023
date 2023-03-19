package com.ll.basic1.domain.member.controller;

import com.ll.basic1.domain.member.entity.Member;
import com.ll.basic1.domain.member.service.MemberService;
import com.ll.basic1.shared.resultData.Result;
import com.ll.basic1.shared.rq.Rq;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@AllArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @PostMapping("member/login")
    @ResponseBody
    public Result login(String username, String password) {

        if (username == null || username.trim().length() == 0) {
            return new Result("F-3", "username(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return new Result("F-4", "password(을)를 입력해주세요.");
        }
        Result result = memberService.tryLogin(username, password);
        if (result.isSuccess()) {
            long memberId = (long) result.getData();
            rq.setSession("loginedMemberId", memberId);
        }
        return result;
    }

    @GetMapping("/member/login")
    public String showLogin(){
        return "usr/member/login";
    }

    @GetMapping("member/logout")
    @ResponseBody
    public Result logout() {
        boolean isRemove = rq.removeSession("loginedMemberId");
        if (isRemove) {
            return new Result("S-1", "로그아웃 되었습니다.");
        }
        return new Result("F-1", "이미 로그아웃 상태입니다.");
    }

    @GetMapping("member/me")
    public String showMe(Model model) {
        long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);


        Member findMember = memberService.findById(loginedMemberId);
        model.addAttribute("member", findMember);

        return "usr/member/me";
    }


}

