package com.ll.basic1.home.controller;

import com.ll.basic1.entity.People;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    int n = 0;
    List<People> list = new ArrayList<>();

    @GetMapping("/home/main")
    @ResponseBody
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "안녕하세요2";
    }

    @GetMapping("home/increase")
    @ResponseBody
    public String increaseMain() {
        return Integer.toString(n++);
    }

    @GetMapping("/home/hi")
    @ResponseBody
    public String hiMain() {
        return "spring";
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int paramMain(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, int age) {
        People people = new People(name, age);
        people.setId(++n);
        list.add(people);
        return people.getId() + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<People> getPeople() {
        return list;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam int id) {
        boolean isRemove = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                isRemove = true;
                break;
            }
        }
        if (isRemove)
            return id + "번이 삭제되었습니다.";
        else
            return "그런 아이디는 없습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String removePerson(@RequestParam int id, @RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "0") int age) {
        boolean isFind = false;
        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
                isFind = true;
                People findPerson = list.get(i);

                if (name.equals("")) {
                    name = findPerson.getName();
                }
                if (age == 0) {
                    age = findPerson.getAge();
                }

                findPerson.modify(name, age);
            }
        }

        if (isFind)
            return id + "번이 수정되었습니다.";
        else
            return id + "번은 없습니다.";
    }
}

