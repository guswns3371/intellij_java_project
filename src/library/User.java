package library;

public class User extends Person {

    int Max_books;
    private int expire_num = 0;
    User(String name, String passwd) {
        super(name, passwd);
        super.setWho("유저");
    }

    void 대출한도연장() {
        intro("대출연장");
    }
    boolean 반납일연장(){
        boolean isok;
        intro("반납일 연장");
        if (expire_num<2){
            isok = true;
            expire_num++;
            System.out.println("현재 반납일 연장 횟수 : "+expire_num+"번/2번");
        }else {
            System.out.println("반납일 연장 횟수가 다 찼습니다.");
            isok = false;
        }
        return isok;
    }
}
