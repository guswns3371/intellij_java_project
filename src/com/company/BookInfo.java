package com.company;


public class BookInfo {
    String name,author;
    boolean isOccupied = false;
    int num,occupied_num=0,index;

    public BookInfo(int index, String name, String author, int num) {
        this.index = index;
        this.name = name;
        this.author = author;
        this.num = num;
    }
    void intro() {
        System.out.print("[책] "+index+". "+name+" | "+author+" : ");
    }

    void 책정보() {
        System.out.println(index+". "+name+" | "
                +author+" | "+num+"권 ------ ( "+occupied_num+"권 대출중 )");
    }

    public boolean isOccupied() {
        System.out.println("("+occupied_num+"/"+num+") 권 대출 중");
        return isOccupied;
    }

    public boolean 책_대출가능성() {
        boolean b = false;
        intro();
        if (num> occupied_num) {
            occupied_num++;
            System.out.print("<대출 완료 => 현재 "+num+"권 중 "+occupied_num+"권 대출 중");
            b = true;
        }else {
            System.out.print("<대출 불가능 - "+num+"권 중 "+occupied_num+"권 대출 중");
            b = false;
        }
        System.out.println(">");

        return b;
    }

    public boolean 책_반납가능성() {
        boolean b = false;
        intro();
        if (occupied_num>0) {
            occupied_num--;
            System.out.print("<반납 완료 => 현재"+num+"권 중 "+occupied_num+"권 대출 중");
            b = true;
        }else {
            System.out.print("<반납 불가능 - 대출중인 책이 없습니다.");
            b = false;
        }
        System.out.println(">");

        return b;

    }


}

