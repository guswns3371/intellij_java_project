package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static BookInfo[] booklist = {
            new BookInfo(0,"방에관한 기억", "서성란", 5),
            new BookInfo(1,"파프리카", "서성란", 3),
            new BookInfo(2,"어린 왕자", "쌩땍쥐뼤리", 10),
            new BookInfo(3,"자바전쟁", "생활토딩", 4),
            new BookInfo(4,"PHP 뿌쉬기", "마크 주커버그", 7),
            new BookInfo(5,"아홉살 인생", "하현준", 2),
            new BookInfo(6,"하이탑", "서보인", 6),
            new BookInfo(7,"이산수학", "박두순", 11),
            new BookInfo(8,"오베라는 남자", "프레드릭 배크만", 3),
            new BookInfo(9,"연금술사", "파울로 코엘료", 5),
            new BookInfo(10,"숨", "테드 창", 3),
            new BookInfo(11,"살인 카드 게임", "제임스 패터슨", 6),
            new BookInfo(12,"고양이 1", "베르나르 베르베르", 9),
            new BookInfo(13,"편지", "히가시노 게이고", 2),
            new BookInfo(14,"흐린 길","윤덕원",4)
    };
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int user_menu_num,admin_menu_num,age;
        String name,major;
        Student student = null;
        Faculty faculty = null;
        Admin admin = null;

        System.out.print("사용자 ==> 1\n관리자 ==> 2\n사용자 정보 파일관리 ==> 3\n도서정보 파일관리 ==> 4 \n종료 ==> 5 : ");
        int who = input.nextInt();
        switch (who) {
            case 1:
                System.out.print("학생 ==> 1 , 교직원 ==> 2 :");
                int who2 = input.nextInt();
                switch (who2) {
                    case 1:
                        System.out.print("이름을 입력하세요 : ");
                        name = input.next();
                        System.out.print("나이를 입력하세요 : ");
                        age = input.nextInt();
                        System.out.print("학과를 입력하세요 : ");
                        major = input.next();
                        System.out.print("학번을 입력하세요 : ");
                        String stu_num = input.next();
                        student = new Student(age, name, stu_num, major);

                        break;
                    case 2:
                        System.out.print("이름을 입력하세요 : ");
                        name = input.next();
                        System.out.print("나이를 입력하세요 : ");
                        age = input.nextInt();
                        System.out.print("담당 학과를 입력하세요 : ");
                        major = input.next();
                        faculty = new Faculty(age, name, major);
                        break;

                }
                for(;;) {
                    user_menu_num = user_menu();

                    if (student == null) {
                        faculty_act(faculty, user_menu_num);
                    }else if (student != null) {
                        student_act(student, user_menu_num);
                    }

                    if (user_menu_num==5) {
                        break;
                    }else if (user_menu_num>5 || user_menu_num <1) {
                        System.out.println("입력값 오류");
                    }
                }
                break;

            case 2:
                System.out.print("이름을 입력하세요 : ");
                name = input.next();
                System.out.print("나이를 입력하세요 : ");
                age = input.nextInt();
                admin = new Admin(age, name);

                for(;;) {
                    admin_menu_num = admin_menu();

                    admin_act(admin, admin_menu_num);

                    if (admin_menu_num==4) {
                        break;
                    }else if (admin_menu_num>4 || admin_menu_num <1) {
                        System.out.println("입력값 오류");
                    }
                }
                break;
            case 5:
                System.out.println("종료하였습니다.");
                break;
        }

        input.close();
    }

    static int user_menu() {
        System.out.println("*********************");
        System.out.println("      1.대    출                   ");
        System.out.println("      2.반    납                   ");
        System.out.println("      3.조    회                   ");
//		조회 : 도서정보 검색 화면
//		<- 1. book.bin 파일에서 읽어와서 정보 긁어온다
//		2. 그 결과에 따라 도서 대출 여부, 예약 여부 판단
//		사용자 정보 파일 1개 - student.bin
//		도서정보 파일 1개 - book.bin ( 대출 정보, 예약정보)
        System.out.println("      4.예    약                   ");
        System.out.println("      5.exit         ");
        System.out.println("*********************");
        System.out.print("input menu number : ");
        int num = input.nextInt();

        return num;
    }

    static int admin_menu() {
        System.out.println("*********************");
        System.out.println("      1.도서정보 입력                   ");
        System.out.println("      2.도소 정보 정리 (삭제)   ");
        System.out.println("      3.미반납자 확인             ");
        System.out.println("      4.exit         ");
        System.out.println("*********************");

        System.out.print("input menu number : ");
        int num = input.nextInt();

        return num;
    }

    static void display_booklist() {
        System.out.println("*********************");
        for(BookInfo b : booklist) {
            b.책정보();
        }
        System.out.println("*********************");

    }
    static void student_act(Student student, int user_menu_num) {
        int book_idx;
        switch (user_menu_num) {
            case 1:
                book_idx = student.대출시도();
                student.대출가능성(booklist[book_idx].책_대출가능성());
                student.display_info();

                break;
            case 2:
                book_idx = student.반납시도();
                student.반납가능성(booklist[book_idx].책_반납가능성());
                student.display_info();

                break;
            case 3:
                student.조회시도();
                display_booklist();
                break;
            case 4:
                student.예약시도();
                break;
            case 5:
                student.나가기();
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    static void faculty_act(Faculty faculty, int user_menu_num ) {
        int book_idx;
        switch (user_menu_num) {
            case 1:
                book_idx = faculty.대출시도();
                faculty.대출가능성(booklist[book_idx].책_대출가능성());
                faculty.display_info();
                break;
            case 2:
                book_idx = faculty.반납시도();
                faculty.반납가능성(booklist[book_idx].책_반납가능성());
                faculty.display_info();
                break;
            case 3:
                faculty.조회시도();
                display_booklist();
                break;
            case 4:
                faculty.예약시도();
                break;
            case 5:
                faculty.나가기();
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    static void admin_act(Admin admin, int admin_menu_num) {
        switch (admin_menu_num) {
            case 1:
                admin.대출처리();
                break;
            case 2:
                admin.반납처리();
                break;
            case 3:
                admin.사용자_배열정보();
                break;
            case 4:
                admin.나가기();
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}
