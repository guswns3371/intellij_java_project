package library;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class LibrarySystem {

    final private int recordSize = 75;
    //	index =3, booknanme =30, 대출여부 =1, 대출자 =6(학번), 반납날짜 =10 (date), 예약자 =6 (학번)
    final static private byte paddingChar = 0x40;
    final static private char padding= 0x40;

    private String book_fn,person_fn;

    LibrarySystem(){
        this.book_fn = "book.bin";
        this.person_fn = "person.bin";
    }

    void 도서정보출력() {

        FileInputStream in;
        try {
            in = new FileInputStream(book_fn);
            int total=0;
            total = in.available();
            byte[] newrecord = new byte[total];
            String[] booklist = new String[total/recordSize];
            in.read(newrecord);
            String str = new String(newrecord, StandardCharsets.UTF_8);
            StringBuilder s= new StringBuilder();
            int a=0;
            for (int i =0; i<str.length(); i++){
                s.append(str.charAt(i));
                if ((i+1) % recordSize == 0){
                    booklist[a] = s.toString();
                    s = new StringBuilder();
                    a++;
                }
            }
            System.out.println(
                    "책 번호   "+" | "+
                            "책 제목                          "+" | "+
                            "대출자    "+" | "+
                            "예약자    "+" | "+
                            "반납일    "+"| "+
                            "대출여부"+"|"
            );
            for (String str2: booklist) {
                String idx="",name="",dae="",yea="",exp="",isDae="";
                for (int i=0;i<str2.length(); i++){
                    String A = Character.toString(str2.charAt(i));
                    if (A.equals("@"))
                        A=" ";
                    if (i < 10)
                        idx += A;
                    if (i>=10 && i<40)
                        name += A;
                    if (i>=40 && i<50)
                        dae += A;
                    if (i>=50 && i<60)
                        yea += A;
                    if (i>=60 && i<70)
                        exp += A;
                    if (i>=70 && i<75)
                        isDae += A;
                }
                System.out.println(
                        idx+" | "+
                                name+" | "+
                                dae+" | "+
                                yea+" | "+
                                exp+" | "+
                                isDae+" | "
                );
            }


            in.close();

        } catch (FileNotFoundException e){
            System.out.println(book_fn+"파일이 없습니다.");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    void 대출관리(int book_idx,String name){
        if (대출가능여부판단(book_idx)){

        }
    }
    void 반납관리(int book_idx,String name){
        if (반납가능여부판단(book_idx)){

        }
    }
    void 예약관리(int book_idx,String name){
        if (예약가능여부판단(book_idx)){

        }
    }
    void 조회관리(int book_idx,String name){
        if (조회가능여부판단(book_idx)){

        }
    }

    //책 입장에서
    //유저의 최대 대출권수의 의미가 없는 코드
    private boolean 대출가능여부판단(int book_idx){
        return check(book_idx,1);
    }
    private boolean 반납가능여부판단(int book_idx){
        return check(book_idx,2);
    }
    private boolean 예약가능여부판단(int book_idx){
        return check(book_idx,3);
    }
    private boolean 조회가능여부판단(int book_idx){
        return check(book_idx,4);
    }


    private boolean check(int book_idx,int isOk_of_what){
        boolean isOk = false;
        switch (isOk_of_what){
            case 1://대출
                isOk = searchFromBookFile(book_idx,isOk_of_what);
                if (!isOk)
                    System.out.println("!! 대출 불가능 !!");
                else
                    System.out.println("!! 대출 가능 !!");
                break;
            case 2://반납
                isOk = searchFromBookFile(book_idx,isOk_of_what);
                if (!isOk)
                    System.out.println("!! 반납 불가능 !!");
                else
                    System.out.println("!! 반납 가능 !!");
                break;
            case 3://예약
                isOk = searchFromBookFile(book_idx,isOk_of_what);
                if (!isOk)
                    System.out.println("!! 예약 불가능 !!");
                else
                    System.out.println("!! 예약 가능 !!");
                break;
            case 4://조회
                isOk = searchFromBookFile(book_idx,isOk_of_what);
                if (!isOk)
                    System.out.println("!! 조회 불가능 !!");
                else
                    System.out.println("!! 조회 가능 !!");
                break;
        }
        return  isOk;
    }

    private boolean searchFromBookFile(int book_idx,int isOk_of_what){
        boolean isOk= false;

        FileInputStream in;
        try {
            in = new FileInputStream(book_fn);
            int total=0;
            total = in.available();
            byte[] newrecord = new byte[total];
            String[] booklist = new String[total/recordSize];
            in.read(newrecord);
            String str = new String(newrecord, StandardCharsets.UTF_8);
            StringBuilder s= new StringBuilder();
            int a=0;
            for (int i =0; i<str.length(); i++){
                s.append(str.charAt(i));
                if ((i+1) % recordSize == 0){
                    booklist[a] = s.toString();
                    s = new StringBuilder();
                    a++;
                }
            }

            for (String str2: booklist) {
                String idx="",name="",dae="",yea="",exp="",isDae="";
                for (int i=0;i<str2.length(); i++){
                    String A = Character.toString(str2.charAt(i));
                    if (A.equals("@"))
                        A="";
                    if (i < 10)
                        idx += A;
                    if (i>=10 && i<40)
                        name += A;
                    if (i>=40 && i<50)
                        dae += A;
                    if (i>=50 && i<60)
                        yea += A;
                    if (i>=60 && i<70)
                        exp += A;
                    if (i>=70 && i<75)
                        isDae += A;
                }

                if (idx.equals(Integer.toString(book_idx))){
                    System.out.println(
                            "책 번호"+" | "+
                                    "책 제목                 "+" | "+
                                    "대출자"+" | "+
                                    "예약자"+" | "+
                                    "반납일"+"| "+
                                    "대출여부"+"|"
                    );System.out.println(
                            idx+" | "+
                                    name+" | "+
                                    dae+" | "+
                                    yea+" | "+
                                    exp+" | "+
                                    isDae+" | "
                    );
                    switch (isOk_of_what){
                        case 1://대출
                            if (dae.equals("") || isDae.equals("0")){
                                isOk = true;
                            }else {
                                System.out.print("대출자 : "+dae+", 대출여부 : "+isDae+" ==> ");
                                isOk = false;
                            }
                            break;
                        case 2://반납
                            if (dae.equals("") || isDae.equals("0")){
                                isOk = false;
                            }else {
//                                System.out.print("반납일"+exp+", 대출자 : "+dae+", 대출여부 : "+isDae+" ==> ");
                                isOk = true;
                            }
                            break;
                        case 3://예약
                            if (yea.equals("")){
                                isOk = true;
                            }else{
                                System.out.print("예약자 : "+yea+" ==> ");
                                isOk = false;
                            }
                            break;
                        case 4://조회
                            isOk = true;
                            break;
                    }
                }
            }


            in.close();

        } catch (FileNotFoundException e){
            System.out.println(book_fn+"파일이 없습니다.1");
            isOk = false;
        }catch (IOException e) {
            System.out.println(book_fn+"파일이 없습니다.2");
            isOk = false;
            e.printStackTrace();
        }
        return isOk;
    }
    private boolean 반납하기(int book_idx,String userName){
        boolean isOk= false;

        FileInputStream in;
        try {
            in = new FileInputStream(book_fn);
            int total=0;
            total = in.available();
            byte[] newrecord = new byte[total];
            String[] booklist = new String[total/recordSize];
            in.read(newrecord);
            String str = new String(newrecord, StandardCharsets.UTF_8);
            StringBuilder s= new StringBuilder();
            int a=0;
            for (int i =0; i<str.length(); i++){
                s.append(str.charAt(i));
                if ((i+1) % recordSize == 0){
                    booklist[a] = s.toString();
                    s = new StringBuilder();
                    a++;
                }
            }

            for (String str2: booklist) {
                String idx="",name="",dae="",yea="",exp="",isDae="";
                for (int i=0;i<str2.length(); i++){
                    String A = Character.toString(str2.charAt(i));
                    if (A.equals("@"))
                        A="";
                    if (i < 10)
                        idx += A;
                    if (i>=10 && i<40)
                        name += A;
                    if (i>=40 && i<50)
                        dae += A;
                    if (i>=50 && i<60)
                        yea += A;
                    if (i>=60 && i<70)
                        exp += A;
                    if (i>=70 && i<75)
                        isDae += A;
                }

                if (idx.equals(Integer.toString(book_idx))){
                    System.out.println(
                            "책 번호"+" | "+
                                    "책 제목                 "+" | "+
                                    "대출자"+" | "+
                                    "예약자"+" | "+
                                    "반납일"+"| "+
                                    "대출여부"+"|"
                    );System.out.println(
                            idx+" | "+
                                    name+" | "+
                                    dae+" | "+
                                    yea+" | "+
                                    exp+" | "+
                                    isDae+" | "
                    );
                            if (dae.equals("") || isDae.equals("0")){
                                isOk = false;
                            }else {
                                System.out.print("반납일"+exp+", 대출자 : "+dae+", 대출여부 : "+isDae+" ==> ");
                                isOk = true;
                            }
                }
            }


            in.close();

        } catch (FileNotFoundException e){
            System.out.println(book_fn+"파일이 없습니다.1");
            isOk = false;
        }catch (IOException e) {
            System.out.println(book_fn+"파일이 없습니다.2");
            isOk = false;
            e.printStackTrace();
        }
        return isOk;
    }

    private static String writeBookInfo(Scanner input, String s,String str,int length){
        for (;;){
            String book;
            if (!str.equals("책 번호")){
                System.out.print(str+" ("+length+"자 이내) : ");
                book = input.nextLine();
            }else {
                book = s;
            }

            if (book.length()<=length){
                for (int i=book.length(); i<length; i++){
                    book += padding;
                }
                System.out.println(book);
                return book;
            }else {
                System.out.println(str+"(이)가 "+length+"자가 넘습니다");
            }
        }
    }

    private static String writeAtoB(String str,int length){
        if (str.length()<=length){
            for (int i=str.length(); i<length; i++){
                str += "&";
            }
            System.out.println(str);
            return str;
        }else {
            System.out.println(str+"(이)가 "+length+"자가 넘습니다");
            return null;
        }
    }

}
