package com.company;

import java.util.Scanner;

public class Main_test {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Book book = new Book("book.bin");
        Scanner input = new Scanner(System.in);
        for (;;){
            System.out.println("*********************");
            System.out.println("      1.도서 정보 입력                   ");
            System.out.println("      2.도서 정보 정리 (삭제)   ");
            System.out.println("      3.도서 정보 편집             ");
            System.out.println("      4.exit         ");
            System.out.println("*********************");

            System.out.print("input menu number : ");
            int a = input.nextInt();
            switch (a){
                case 1:book.inputBookInfo();
                    break;
                case 2:book.readBookInfoFile();
                    break;
                case 3:book.editBookInfoFile();
                    break;
                case 4:
                    break;
            }
        }
    }

    static boolean user_menu() {
        int menu_no;
        boolean ret = true;
        Scanner in = new Scanner(System.in);
        System.out.println("*********************");
        System.out.println("      1.대    출                   ");
        System.out.println("      2.반    납                   ");
        System.out.println("      3.조    회                   ");
        System.out.println("      4.예    약                   ");
        System.out.println("      5.exit         ");
        System.out.println("*********************");
        System.out.print("input menu number : ");
        menu_no = in.nextInt();

        if (menu_no == 5) {
            ret = false;
        }else {
            if (menu_no>=1 && menu_no<=4) {

            }else {
                System.out.println("메뉴번호 잘못입력 , 다시 입력하세요");
            }
            ret = true;
        }
//		in.close();
        return ret;

    }

    static void admin_menu() {
        System.out.println("*********************");
        System.out.println("      1.대    출                   ");
        System.out.println("      2.반    납                   ");
        System.out.println("      3.책목록 관리             ");
        System.out.println("      4.exit         ");
    }
}
