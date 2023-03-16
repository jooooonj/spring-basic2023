package com.ll.basic1.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class Member {
    private static long num;
    private long id;
    private String username;
    private String password;

    static{
        num = 0;
    }

    public Member(String username, String password) {
        this(++num, username, password);
    }
}