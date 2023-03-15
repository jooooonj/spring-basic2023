package com.ll.basic1.service;

import com.ll.basic1.entity.Result;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public Result tryLogin(String username, String password) {
        if (!username.equals("user1")) {
            return new Result("F-1", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        } else if (!password.equals("1234")) {
            return new Result("F-2", "비밀번호가 틀렸습니다");
        }
        return new Result("S-1", "%s님 환영합니다.".formatted(username));
    }
}
