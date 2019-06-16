package library;

public class BookInfo {
    String idx,name,author,loaner,booker;

    public BookInfo(String idx, String name, String author, String loaner, String booker) {
        this.idx = idx;
        this.name = name;
        this.author = author;
        this.loaner = loaner;
        this.booker = booker;
        if (loaner.equals(""))
            this.loaner = "없음";
        if (booker.equals(""))
            this.booker = "없음";
    }

    void displayBookInfo(){
//        System.out.println(
//                        "책 번호"+" | "+
//                        "책 제목                 "+" | "+
//                        "작가"+" | "+
//                        "대출자"+" | "+
//                        "예약자"+" | "
//        );
        System.out.println(
                        idx+" | "+
                        name+" | "+
                        author+" | "+
                        loaner+" | "+
                        booker
        );
    }

}
