package com.company;


public class Faculty extends User{

    String major;
    public Faculty(String name, String major) {
        super(name);
        this.major = major;
        super.Max_books = 10;
    }

    void intro() {
        System.out.print(name+" (교직원) : ");
    }

    void 대출연장() {
        System.out.println("대출연장");
        super.using_days++;
    }

}
