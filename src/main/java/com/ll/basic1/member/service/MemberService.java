package com.ll.basic1.member.service;

import com.ll.basic1.member.entity.Member;
import com.ll.basic1.member.repository.MemberRepository;
import com.ll.basic1.resultData.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Result tryLogin(String username, String password) {
        Member findMember = memberRepository.findByUsername(username);

        if (findMember == null) {
            return new Result("F-1", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if (!findMember.getPassword().equals(password)) {
            return new Result("F-2", "비밀번호가 일치하지 않습니다.");
        }

        return new Result("S-1", "%s 님 환영합니다.".formatted(username), findMember.getId());
    }

    public Member findById(long loginedMemberId) {
        return memberRepository.findById(loginedMemberId);
    }
}
