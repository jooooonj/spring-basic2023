package com.ll.basic1.member.controller;

import com.ll.basic1.member.entity.Member;
import com.ll.basic1.resultData.Result;
import com.ll.basic1.member.service.MemberService;
import com.ll.basic1.rq.Rq;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public Result login(String username, String password, HttpServletRequest req, HttpServletResponse res) {
        Rq rq = new Rq(req, res);
        if (username == null || username.trim().length() == 0) {
            return new Result("F-3", "username(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return new Result("F-4", "password(을)를 입력해주세요.");
        }
        Result result = memberService.tryLogin(username, password);
        if (result.isSuccess()) {
            long memberId = (long) result.getData();
            rq.setCookie("loginedMemberId", memberId);
        }
        return result;
    }

    @GetMapping("member/logout")
    @ResponseBody
    public Result logout(HttpServletRequest req, HttpServletResponse res) {
        Rq rq = new Rq(req, res);
        boolean isRemove = rq.removeCookie("loginedMemberId");
        if (isRemove) {
            return new Result("S-1", "로그아웃 되었습니다.");
        }
        return new Result("F-1", "이미 로그아웃 상태입니다.");
    }

    @GetMapping("member/me")
    @ResponseBody
    public Result loginState(HttpServletRequest req, HttpServletResponse res) {
        Rq rq = new Rq(req, res);
        long loginedMemberId = rq.getCookieAsLong("loginedMemberId", 0);
        boolean isLogined = loginedMemberId > 0;
        if (!isLogined) {
            return new Result("F-1", "로그인 후에 이용해주세요");
        }

        Member member = memberService.findById(loginedMemberId);
        return new Result("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));
    }
}

