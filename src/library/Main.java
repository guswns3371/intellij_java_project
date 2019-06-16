package library;

import java.util.Scanner;

public class Main {

    static Scanner input;
    static LibrarySystem librarySystem ;
    public static void main(String[] args) {
        input = new Scanner(System.in);
        librarySystem = new LibrarySystem();
//        Student student = new Student("하현준","15109379");
//        Faculty faculty = new Faculty("김상훤","19890321");
//        Admin admin = new Admin("이수영","2580");

        int user_menu_num,admin_menu_num;
        String name,passwd;
        Student student = null;
        Faculty faculty = null;
        Admin admin = null;

        System.out.print("사용자 ==> 1\n관리자 ==> 2\n종료 ==> 3 : ");
        int who = input.nextInt();
        switch (who) {
            case 1:
                System.out.print("학생 ==> 1 , 교직원 ==> 2 :");
                int who2 = input.nextInt();
                switch (who2) {
                    case 1:
                        System.out.print("이름을 입력하세요 : ");
                        name = input.next();
                        System.out.print("비밀번호를 입력하세요 : ");
                        passwd = input.next();
                        student = new Student(name, passwd);

                        break;
                    case 2:
                        System.out.print("이름을 입력하세요 : ");
                        name = input.next();
                        System.out.print("비밀번호를 입력하세요 : ");
                        passwd = input.next();
                        faculty = new Faculty( name, passwd);
                        break;

                }
                for(;;) {
                    user_menu_num = user_menu();
                    if (user_menu_num==5) {
                        break;
                    }else if (user_menu_num>5 || user_menu_num <1) {
                        System.out.println("입력값 오류");
                    }else {
                        if (student == null) {
                            faculty_act(faculty, user_menu_num);
                        }else {
                            student_act(student, user_menu_num);
                        }
                    }
                }
                break;

            case 2:
                System.out.print("이름을 입력하세요 : ");
                name = input.next();
                System.out.print("비밀번호를 입력하세요 : ");
                passwd = input.next();
                admin = new Admin(name,passwd);

                for(;;) {
                    admin_menu_num = admin_menu();
                    if (admin_menu_num==5) {
                        break;
                    }else if (admin_menu_num>5 || admin_menu_num <1) {
                        System.out.println("입력값 오류");
                    }else {
                        admin_act(admin, admin_menu_num);
                    }
                }
                break;
            case 3:
                System.out.println("종료하였습니다.");
                break;
        }
        input.close();
    }

    static int user_menu() {
        System.out.println("*********************");
        System.out.println("      1.대    출                   ");
        System.out.println("      2.반    납                   ");
        System.out.println("      3.조    회                   ");
//		조회 : 도서정보 검색 화면
//		<- 1. book.bin 파일에서 읽어와서 정보 긁어온다
//		2. 그 결과에 따라 도서 대출 여부, 예약 여부 판단
//		사용자 정보 파일 1개 - student.bin
//		도서정보 파일 1개 - book.bin ( 대출 정보, 예약정보)
        System.out.println("      4.예    약                   ");
        System.out.println("      5.exit         ");
        System.out.println("*********************");
        System.out.print("input menu number : ");
        int num = input.nextInt();

        return num;
    }

    static int admin_menu() {
        System.out.println("*********************");
        System.out.println("      1.도서 정보 입력                ");
        System.out.println("      2.도서 정보 수정    ");
        System.out.println("      3.도서 정보 삭제 .   ");
        System.out.println("      4.도서 정보 출력             ");
        System.out.println("      5.exit         ");
        System.out.println("*********************");
        System.out.print("input menu number : ");
        int num = input.nextInt();

        return num;
    }

    static void student_act(Student student, int user_menu_num) {
        int num;
        librarySystem.도서정보출력();
        switch (user_menu_num) {
            case 0:
                break;
            case 1:
                System.out.print("      대출할 책의 번호를 입력하세요 : ");
                num = student.대출(input.nextInt());
                librarySystem.대출관리(num,student.getName());
                break;
            case 2:
                System.out.print("      반납할 책의 번호를 입력하세요 : ");
                num = student.반납(input.nextInt());
                librarySystem.반납관리(num,student.getName());
                break;
            case 3:
                System.out.print("      조회할 책의 번호를 입력하세요 : ");
                num = student.조회(input.nextInt());
                librarySystem.조회관리(num,student.getName());
                break;
            case 4:
                System.out.print("      예약할 책의 번호를 입력하세요 : ");
                num = student.예약(input.nextInt());
                librarySystem.예약관리(num,student.getName());
                break;
            case 5:
                student.나가기();
                break;
            default:
                System.out.println("        error");
                break;
        }
    }

    static void faculty_act(Faculty faculty, int user_menu_num ) {
        int num;
        librarySystem.도서정보출력();
        switch (user_menu_num) {
            case 0:
                break;
            case 1:
                System.out.print("      대출할 책의 번호를 입력하세요 : ");
                num = faculty.대출(input.nextInt());
                librarySystem.대출관리(num,faculty.getName());
                break;
            case 2:
                System.out.print("      반납할 책의 번호를 입력하세요 : ");
                num = faculty.반납(input.nextInt());
                librarySystem.반납관리(num,faculty.getName());
                break;
            case 3:
                System.out.print("      조회할 책의 번호를 입력하세요 : ");
                num = faculty.조회(input.nextInt());
                librarySystem.조회관리(num,faculty.getName());
                break;
            case 4:
                System.out.print("      예약할 책의 번호를 입력하세요 : ");
                num = faculty.예약(input.nextInt());
                librarySystem.예약관리(num,faculty.getName());
                break;
            case 5:
                faculty.나가기();
                break;
            default:
                System.out.println("        error");
                break;
        }
    }

    static void admin_act(Admin admin, int admin_menu_num) {
        librarySystem.도서정보출력();
        switch (admin_menu_num) {
            case 1:
                admin.도서정보입력();
                break;
            case 2:
                admin.도서정보수정();
                break;
            case 3:
                admin.도서정보삭제();
                break;
            case 4:
                admin.도서정보출력();
                break;
            case 5:
                admin.나가기();
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    static void system_act(LibrarySystem system, int menu_num){
        switch (menu_num){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }
}
