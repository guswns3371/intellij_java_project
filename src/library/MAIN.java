package library;

import java.util.Scanner;


public class MAIN {

    private static Scanner input  = null;
    private static LibrarySystem sys = null;
    private static Student student=null;
    private static Faculty faculty=null;
    public static void main(String[] args) {
        input  = new Scanner(System.in);
        sys = new LibrarySystem();

        System.out.print("사용자 ==> 1\n관리자 ==> 2\n종료 ==> 3 : ");
        int who = input.nextInt();
        switch (who){
            case 1:
                user_act_1();
                break;
            case 2:
                admin_act();
                break;
            case 3:
                System.out.println("종료 하였습니다.");
                break;
        }
        sys.disconnect();
        input.close();
    }

    private static void user_act_1(){
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
    private static void user_act_2(){
        int num;
        do {
            System.out.println("0. 검색 - 이름/ 작가/ 번호");
            System.out.println("1. 조회 - 모든 책");
            System.out.println("2. 조회 - 대출한 책");
            System.out.println("3. 조회 - 예약한 책");
            System.out.println("4. 대출");
            System.out.println("5. 반납");
            System.out.println("6. 예약");
            System.out.println("7. 대출 한도 연장");
            System.out.println("8. 반납일 연장");
            System.out.println("9. 종료");
            System.out.println("번호 입력 : ");
            num = input.nextInt();
            switch (num){
                case 0:
                    bookSearch();
                    break;
                case 1:
                    System.out.println("<1. 조회 - 모든 책>");
                    bookDisplay("all");
                    break;
                case 2:
                    System.out.println("<2. 조회 - 대출한 책>");
                    bookDisplay("loan");
                    break;
                case 3:
                    System.out.println("<3. 조회 - 예약한 책>");
                    bookDisplay("book");
                    break;
                case 4:
                    System.out.println("<4. 대출>");
                    bookDisplay("all");
                    bookLoaning();
                    break;
                case 5:
                    System.out.println("<5. 반납>");
                    bookDisplay("loan");
                    bookReturning();
                    break;
                case 6:
                    System.out.println("<6. 예약>");
                    bookDisplay("all");
                    bookBooking();
                    break;
                case 7:
                    사용자대출한도연장();
                    break;
                case 8:
                    사용자반납일연장();
                    break;
                case 9:
                    System.out.println("종료 하였습니다.");
                    break;
            }
        }while (num!=9);

    }

    /**user*/
    private static int personselect(){
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

        identity = sys.personselect(name,passwd);

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
                sys.connStudent(student);
                System.out.println(student.getName()+"("+student.getWho()+")"+"님 반갑습니다");
                user_act_2();
                num =3;
                break;
            case "교직원":
                faculty = new Faculty(name,passwd);
                sys.connFaculty(faculty);
                System.out.println(faculty.getName()+"("+faculty.getWho()+")"+"님 반갑습니다");
                user_act_2();
                num =3;
                break;
        }
        return num;
    }
    private static void personinsert(){
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

        sys.personinsert(name,passwd,identity);
    }
    private static void bookBooking(){
        System.out.println("책 번호 :");
        int idx = input.nextInt();
        sys.book_booking(idx);
    }
    private static void bookLoaning(){
        System.out.println("책 번호 :");
        int idx = input.nextInt();
        sys.book_loaning(idx);
    }
    private static void bookReturning(){
        System.out.println("책 번호 :");
        int idx = input.nextInt();
        sys.book_return(idx);
    }
    private static void 사용자대출한도연장(){
        if (student!=null)
            student.대출한도연장();
        else
            faculty.대출한도연장();
    }
    private static void 사용자반납일연장(){
        if (student!=null){
            if (student.반납일연장()){
                ExtendingExp();
            }
        }
        else{
            if (faculty.반납일연장()){
                ExtendingExp();
            }
        }
    }
    private static void ExtendingExp(){
        input.nextLine();
        bookDisplay("loan");
        System.out.println("연장할 책 번호: ");
        int idx = input.nextInt();
        sys.book_extending_exp(idx);
    }

    /**admin */
    private static void admin_act(){
        int num;

        do {
            System.out.println("1. 책 정보 출력");
            System.out.println("2. 책 정보 입력");
            System.out.println("3. 책 정보 수정");
            System.out.println("4. 책 정보 삭제");
            System.out.println("5. 책 정보 검색");
            System.out.println("6. 종료");
            System.out.println("번호 입력 : ");
            num = input.nextInt();

            switch (num){
                case 1:
                    System.out.println("<1. 책 정보 출력>");
                    bookDisplay("all");
                    break;
                case 2:
                    System.out.println("<2. 책 정보 입력>");
                    bookInsert();
                    break;
                case 3:
                    System.out.println("<3. 책 정보 수정>");
                    bookUpdate();
                    break;
                case 4:
                    System.out.println("<4. 책 정보 삭제>");
                    bookDelete();
                    break;
                case 5:
                    System.out.println("<5. 책 정보 검색>");
                    bookSearch();
                    break;
                case 6:
                    System.out.println("종료 하였습니다.");
                    break;
            }
        }while (num!=6);
    }
    private static void bookDisplay(String str){
        sys.bookselectWhere(str);
    }
    private static void bookSearch(){
        input.nextLine();
        int num;
        System.out.println("1. 번호로 찾기");
        System.out.println("2. 작가로 찾기");
        System.out.println("3. 책 이름으로 찾기");
        num = input.nextInt();
        input.nextLine();
        String str;
        switch (num){
            case 1:
                System.out.print("책 번호 입력 : ");
                str = input.nextLine();
                sys.booksearch("번호",str);
                break;
            case 2:
                System.out.print("책 작가 입력 : ");
                str = input.nextLine();
                sys.booksearch("작가",str);
                break;
            case 3:
                System.out.print("책 이름 입력 : ");
                str = input.nextLine();
                sys.booksearch("이름",str);
                break;
        }
    }
    private static void bookDelete(){
        input.nextLine();
        int idx;
        System.out.print("책 번호 입력 : ");
        idx = input.nextInt();
        sys.bookdelete(idx);
    }
    private static void bookInsert(){
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
        sys.bookinsert(name,author,loaner,booker);
    }
    private static void bookUpdate(){
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
        sys.bookupdate(idx,name,author,loaner,booker);
    }

}
