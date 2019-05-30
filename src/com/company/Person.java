package com.company;

public class Person {
    int age;
    String name;
    Person(int age, String name) {

        this.age = age;
        this.name = name;
    }

    void intro() {
        System.out.print(name+" ("+age+") : ");
    }
    void 나가기() {
        intro();
        System.out.println("나가기");
    }
}
