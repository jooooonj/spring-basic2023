package com.ll.basic1.entity;

import lombok.Getter;
import lombok.Setter;

public class People {
    @Setter
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void modify(String name, int age){
        this.name = name;
        this.age = age;
    }


}
