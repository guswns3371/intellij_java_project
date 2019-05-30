package com.company;


public class Faculty extends User{

    String major;
    Faculty(int age, String name,String major) {
        super(age, name);
        this.major = major;

        super.Max_books = 10;
        // TODO Auto-generated constructor stub
    }

    void intro() {
        System.out.print(name+" ("+age+"/ 교직원) : ");
    }

    void 대출연장() {
        System.out.println("대출연장");
        super.using_days++;
    }

}
