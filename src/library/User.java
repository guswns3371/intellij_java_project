package library;

public class User extends Person {

    int Max_books;
    User(String name, String passwd) {
        super(name, passwd);
        super.setWho("유저");
    }

}
