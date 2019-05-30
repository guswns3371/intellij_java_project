package com.company;


public class Student extends User{

    String stu_num,major;
    Student(int age, String name,String stu_num, String major) {
        super(age, name);
        this.stu_num = stu_num;
        this.major = major;

        super.Max_books = 5;
    }

    void intro() {
        System.out.print(name+" ("+age+"/ 학생) : ");
    }
    void 대출연장() {
        System.out.println("대출연장");
        super.using_days++;
    }
}
