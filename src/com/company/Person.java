package com.company;

public class Person {
    String name;
    Person(String name) {

        this.name = name;
    }

    void intro() {
        System.out.print(name+": ");
    }
    void 나가기() {
        intro();
        System.out.println("나가기");
    }
}
