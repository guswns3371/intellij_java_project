package library;

public class Student extends User {


    public Student(String name, String passwd) {
        super(name, passwd);
        super.setWho("학생");
        super.Max_books = 3;
    }

    void 대출한도연장(){
        intro("대출연장");
        super.Max_books +=3;
    }
}
