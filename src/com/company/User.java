package com.company;

import java.util.Scanner;

public class User extends Person{

    int Max_books=0,unReturn_books=0,using_days=0;
    boolean isBanned = false;
    Scanner input = null;
    User(int age, String name) {
        super(age, name);
        // TODO Auto-generated constructor stub
    }

    void display_info() {
        System.out.println("-------정     보-------");
        intro();
        System.out.println();
        System.out.println("최대 대출 권수 : "+Max_books);
        System.out.println("미반납 책 권수 (대출한 책 권수): "+unReturn_books);
        System.out.println("대출 기간 : "+using_days);
        System.out.println("-------------------");
        System.out.println();
    }

    public boolean isBanned() {
        return isBanned;
    }


    public void setBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }


    int 대출시도() {
        input = new Scanner(System.in);
        System.out.print("빌릴 책 번호를 입력하세요 : ");
        int book_idx = input.nextInt();
        intro();
        return book_idx;
    }
    void 대출가능성(boolean bookable) {
        if (bookable) {
            System.out.print("<대출완료");
            if (Max_books > unReturn_books) {
                unReturn_books++;
            }else {
                System.out.print("실패 - 최대 대출 권수에 도달했습니다.");
            }
        }

        System.out.println(">");
    }
    int 반납시도() {
        input = new Scanner(System.in);
        System.out.print("반납할 책 번호를 입력하세요 : ");
        int book_idx = input.nextInt();
        intro();
        return book_idx;
    }
    void 반납가능성(boolean returnable) {
        if (returnable) {
            System.out.print("<반납완료");
            if (unReturn_books>0) {
                unReturn_books--;
            }else {
                System.out.print("불가 - 더이상 반납할 책이 없습니다.");
            }
        }

        System.out.println(">");
    }
    void 조회시도() {
        intro();
        System.out.println("<조회>");
    }
    void 예약시도() {
        intro();
        System.out.println("<예약>");
    }

}

