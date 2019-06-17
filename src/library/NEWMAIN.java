package library;

import java.util.Scanner;

public class NEWMAIN {

    private static Scanner input  = null;
    private static MysqlConnect con = null;
    private static Student student=null;
    private static Faculty faculty=null;
    public static void main(String[] args) {
        input  = new Scanner(System.in);
        con = new MysqlConnect();

        System.out.print("사용자 ==> 1\n관리자 ==> 2\n종료 ==> 3 : ");
        int who = input.nextInt();
        switch (who){
            case 1:
                user_act();
                break;
            case 2:
                admin_act();
                break;
            case 3:
                System.out.println("종료 하였습니다.");
                break;
        }

        con.disconnect();
        input.close();
    }

    static void user_act(){
        int num;
        do {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.println("번호 입력 : ");
            num = input.nextInt();
            switch (num){
                case 1:
                    num = personselect();
                    break;
                case 2:
                    personinsert();
                    break;
                case 3:
                    System.out.println("종료 하였습니다.");
                    break;
            }
        }while (num !=3);
    }
    static void user_act_student(Student person){
        int num;
        do {
            System.out.println("1. 조회 - 모든 책");
            System.out.println("2. 조회 - 대출한 책");
            System.out.println("3. 조회 - 예약한 책");
            System.out.println("4. 대출");
            System.out.println("5. 반납");
            System.out.println("6. 예약");
            System.out.println("7. 종료");
            System.out.println("번호 입력 : ");
            num = input.nextInt();
            switch (num){
                case 1:
                    System.out.println("<1. 조회 - 모든 책>");
                    bookDisplay();
                    break;
                case 2:
                    System.out.println("<1. 조회 - 대출한 책>");
                    bookDisplay();
                    break;
                case 3:
                    System.out.println("<1. 조회 - 예약한 책>");
                    bookDisplay();
                    break;
                case 4:
                    System.out.println("<2. 대출>");
                    bookDisplay();
                    bookLoaning(person.name);
                    break;
                case 5:
                    System.out.println("<3. 반납>");
                    bookDisplay();
                    bookReturning(person.name);
                    break;
                case 6:
                    System.out.println("<4. 예약>");
                    bookDisplay();
                    bookBooking(person.name);
                    break;
                case 7:
                    System.out.println("종료 하였습니다.");
                    break;
            }
        }while (num!=5);

    }
    static void user_act_faculty(Faculty person){

    }
    static void student_act(Student person){
        System.out.println(person.name+"("+person.who+")"+"님 반갑습니다");
        user_act_student(person);
    }
    static void faculty_act(Faculty person){
        System.out.println(person.name+"("+person.who+")"+"님 반갑습니다");
        user_act_faculty(person);
    }
    static int personselect(){
        int num = 0;
        String name,passwd,identity;
        input.nextLine();
        do {
            System.out.println("1. 이름 : ");
            name = input.nextLine();
        }while (name.equals(""));

        do {
            System.out.println("2. 비밀번호 : ");
            passwd = input.nextLine();
        }while (passwd.equals(""));

        identity = con.personselect(name,passwd);

        switch (identity){
            case "0":
                System.out.println("존재하지 않은 회원입니다!");
                break;
            case "1":
                System.out.println("존재하지 않은 회원입니다.");
                break;
            case "2":
                System.out.println("비밀번호가 일치하지 않습니다");
                break;
            case "학생":
                student= new Student(name,passwd);
                student_act(student);
                num =3;
                break;
            case "교직원":
                faculty = new Faculty(name,passwd);
                faculty_act(faculty);
                num =3;
                break;
        }
        return num;
    }
    static void personinsert(){
        String name,passwd,identity;
        input.nextLine();
        do {
            System.out.println("1. 이름 : ");
            name = input.nextLine();
        }while (name.equals(""));

        do {
            System.out.println("2. 비밀번호 : ");
            passwd = input.nextLine();
        }while (passwd.equals(""));

        do {
            System.out.println("3. 신분 \na. 학생 b. 교직원 : ");
            identity = input.nextLine();
            if (identity.equals("a"))
                identity = "학생";
            else if (identity.equals("b"))
                identity = "교직원";
        }while (identity.equals(""));

        con.personinsert(name,passwd,identity);
    }
    static void bookBooking(String username){
        System.out.println("책 번호 :");
        int idx = input.nextInt();
        con.book_booking(idx,username);
    }
    static void bookLoaning(String username){
        System.out.println("책 번호 :");
        int idx = input.nextInt();
        con.book_loaning(idx,username);
    }
    static void bookReturning(String username){
        System.out.println("책 번호 :");
        int idx = input.nextInt();
        con.book_return(idx,username);
    }












    /**admin */
    static void admin_act(){
        int num;

        do {
            System.out.println("1. 책 정보 출력");
            System.out.println("2. 책 정보 입력");
            System.out.println("3. 책 정보 수정");
            System.out.println("4. 책 정보 삭제");
            System.out.println("5. 책 정보 선택");
            System.out.println("6. 종료");
            System.out.println("번호 입력 : ");
            num = input.nextInt();

            switch (num){
                case 1:
                    System.out.println("<1. 책 정보 출력>");
                    bookDisplay();
                    break;
                case 2:
                    System.out.println("<2. 책 정보 입력>");
                    bookDisplay();
                    bookInsert();
                    break;
                case 3:
                    System.out.println("<3. 책 정보 수정>");
                    bookDisplay();
                    bookUpdate();
                    break;
                case 4:
                    System.out.println("<4. 책 정보 삭제>");
                    bookDisplay();
                    bookDelete();
                    break;
                case 5:
                    System.out.println("<5. 책 정보 선택>");
                    bookDisplay();
                    bookSelect();
                    break;
                case 6:
                    System.out.println("종료 하였습니다.");
                    break;
            }
        }while (num!=6);
    }
    static void bookDisplay(){
        con.bookselectAll();
    }
    static void bookSelect(){
        input.nextLine();
        int idx;
        System.out.print("책 번호 입력 : ");
        idx = input.nextInt();
        con.bookselect(idx);
    }
    static void bookDelete(){
        input.nextLine();
        int idx;
        System.out.print("책 번호 입력 : ");
        idx = input.nextInt();
        con.bookdelete(idx);
    }
    static void bookInsert(){
        input.nextLine();
        String name,author,loaner,booker;
        System.out.println("책 이름 입력 : ");
        name = input.nextLine();
        System.out.println("책 작가 입력 : ");
        author = input.nextLine();
        System.out.println("책 대출자 입력 : ");
        loaner = input.nextLine();
        System.out.println("책 예약자 입력 : ");
        booker = input.nextLine();
        con.bookinsert(name,author,loaner,booker);
    }
    static void bookUpdate(){
        input.nextLine();
        int idx;
        String name,author,loaner,booker;
        System.out.print("책 번호 입력 : ");
        idx = input.nextInt();
        input.nextLine();
        System.out.print("책 이름 입력 (변경하지 않을 경우 ! 입력) : ");
        name = input.nextLine();
        System.out.print("책 작가 입력 (변경하지 않을 경우 ! 입력) : ");
        author = input.nextLine();
        System.out.print("책 대출자 입력 (변경하지 않을 경우 ! 입력) : ");
        loaner = input.nextLine();
        System.out.print("책 예약자 입력 (변경하지 않을 경우 ! 입력) : ");
        booker = input.nextLine();
        con.bookupdate(idx,name,author,loaner,booker);
    }

}
