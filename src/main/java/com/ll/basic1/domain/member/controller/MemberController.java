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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@AllArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @PostMapping("/login")
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

    @GetMapping("/login")
    public String showLogin() {
        return "usr/member/login";
    }

    @GetMapping("/logout")
    @ResponseBody
    public Result logout() {
        boolean isRemove = rq.removeSession("loginedMemberId");
        if (isRemove) {
            return new Result("S-1", "로그아웃 되었습니다.");
        }
        return new Result("F-1", "이미 로그아웃 상태입니다.");
    }

    @GetMapping("/me")
    public String showMe(Model model) {
        long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);


        Member findMember = memberService.getMember(loginedMemberId);
        model.addAttribute("member", findMember);

        return "usr/member/me";
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            return new Result("F-3", "아이디(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return new Result("F-4", "패스워드(을)를 입력해주세요.");
        }

        Member member = memberService.join(username, password);
        return new Result("S-1", member.getUsername() + "님 환영합니다.");
    }

    @PostMapping("/update")
    @ResponseBody
    public Result updateMember(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            return new Result("F-3", "아이디(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return new Result("F-4", "패스워드(을)를 입력해주세요.");
        }

        Member member = memberService.updateMember(username, password);
        if(member==null)
            return new Result("F-2", username+"의 회원은 존재하지 않습니다.");

        return new Result("S-1", member.getUsername() + "님 비밀번호 변경 완료됐습니다." );
    }
}

