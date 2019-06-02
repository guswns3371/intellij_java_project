package library;


public class Faculty extends User {

    public Faculty(String name, String passwd) {
        super(name, passwd);
        super.setWho("교직원");
        super.Max_books = 5;
    }

    void 대출연장(){
        intro("대출연장");
    }
}
