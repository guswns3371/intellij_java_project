package library;


public class Faculty extends User {

    private int expand_num = 0;
    public Faculty(String name, String passwd) {
        super(name, passwd);
        super.setWho("교직원");
        super.Max_books = 5;
    }

    void 대출한도연장(){
        intro("대출연장");
        if (expand_num<2){
            super.Max_books += 2;
            expand_num++;
            System.out.println("현재 대출 한도 연장 횟수 : "+expand_num+"번/2번");
        }else {
            System.out.println("대출 한도 연장 횟수가 다 찼습니다.");
        }

    }

}
