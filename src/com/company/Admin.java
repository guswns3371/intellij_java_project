package com.company;


public class Admin extends Person{


    public Admin(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    void intro() {
        System.out.print(name+" (관리자) : ");
    }

    void 대출제제여부() {
        intro();
        System.out.println("대출 제제");
    }
    void 사용자_배열정보() {
        intro();
        System.out.println("사용자 배열 정보 작성");
    }

    void 대출처리() {
        intro();
        System.out.println("대출 처리");
    }
    void 반납처리() {
        intro();
        System.out.println("반납 처리");
    }

//	public boolean 책_대출가능성(Book book) {
//		boolean b = false;
//		intro();
//		if (book.num> book.occupied_num) {
//			book.occupied_num++;
//			System.out.print("<대출 완료 => 현재 "+book.num+"권 중 "+book.occupied_num+"권 대출 중");
//			b = true;
//		}else {
//			System.out.print("<대출 불가능 - "+book.num+"권 중 "+book.occupied_num+"권 대출 중");
//			b = false;
//		}
//		System.out.println(">");
//
//		return b;
//	}
//
//	public boolean 책_반납가능성(Book book) {
//		boolean b = false;
//		intro();
//		if (book.occupied_num>0) {
//			book.occupied_num--;
//			System.out.print("<반납 완료 => 현재"+book.num+"권 중 "+book.occupied_num+"권 대출 중");
//			b = true;
//		}else {
//			System.out.print("<반납 불가능 - 대출중인 책이 없습니다.");
//			b = false;
//		}
//		System.out.println(">");
//
//		return b;
//
//	}

}

