package com.ll.basic1.domain.member.service;

import com.ll.basic1.domain.member.entity.Member;
import com.ll.basic1.domain.member.repository.MemberRepository;
import com.ll.basic1.shared.resultData.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Result tryLogin(String username, String password) {
        Member findMember = getMember(username);

        if (findMember == null) {
            return new Result("F-1", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if (!findMember.getPassword().equals(password)) {
            return new Result("F-2", "비밀번호가 일치하지 않습니다.");
        }

        return new Result("S-1", "%s 님 환영합니다.".formatted(username), findMember.getId());
    }

    public Member getMember(long id) {
        return memberRepository.findById(id).orElse(null);
    }
    public Member getMember(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    public Member addMember(String username, String password) {
        Member newMember = Member.builder()
                .username(username)
                .password(password)
                .build();
        return memberRepository.save(newMember);
    }

    public Member updateMember(String username, String password) {
       Member findMember = memberRepository.findByUsername(username).orElse(null);

       if(findMember!=null)
        findMember.updateMe(password);

        return memberRepository.save(findMember);
    }
}
