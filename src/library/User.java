package library;

import library.MEnum;
import library.Person;

public class User extends Person {

    int Max_books;
    User(String name, String passwd) {
        super(name, passwd);
        super.setWho("유저");
    }

    public int 대출(int book_idx){
        intro("대출");
        return book_idx;
    }
    int 반납(int book_idx){
        intro("반납");
        return book_idx;
    }
    int 조회(int book_idx){
        intro("조회");
        return book_idx;
    }
    int 예약(int book_idx){
        intro("예약");
        return book_idx;
    }
}
