package library;

import library.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Admin extends Person {

    final private int recordSize = 75;
    //	index =3, booknanme =30, 대출여부 =1, 대출자 =6(학번), 반납날짜 =10 (date), 예약자 =6 (학번)
    final static private byte paddingChar = 0x40;
    final static private char padding= 0x40;

    private String book_fn,person_fn;

     Admin(String name, String passwd) {
        super(name, passwd);
        super.setWho("관리자");
        this.book_fn = "book.bin";
        this.person_fn = "person.bin";
    }


    void 도서정보입력() {
        String cont = "y";
        String bookIdx = "",bookName="",bookDae="",bookYea="",bookExp="",bookisDae="";
        byte[] oneRecord = new byte[recordSize];
        Scanner input = new Scanner(System.in);
        FileOutputStream fout;
        FileInputStream fin;
        try {
            fin = new FileInputStream(book_fn);
            int len = fin.available();
            len = (len/recordSize)+1;
            fin.close();

            fout = new FileOutputStream(book_fn,true);
            int a=0;
            while(cont.compareTo("y") == 0) {
//			index=10 , bookname =30 ,대출자=10 ,예약자 = 10바이트, 반납일=5 >>> 총 60바이트
                String totalInfo =
                writeBookInfo(input,"","책 번호",10)+
                writeBookInfo(input,"","책 이름",30)+
                writeBookInfo(input,"","책 대출자",10)+
                writeBookInfo(input,"","책 예약자",10)+
                writeBookInfo(input,"","책 반납일",10)+
                writeBookInfo(input,"","책 대출여부",5);
                fout.write(totalInfo.getBytes());
                a++;
                System.out.println("Continue? (y/n)");
                cont = input.nextLine();
            }
            fout.close();
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        } catch (IOException e) {
            // TODO: handle exception
        }


    }

    void 도서정보수정() {
        RandomAccessFile acc;
        try {
            acc= new RandomAccessFile(book_fn,"rw");
            byte[] data = new byte[(int)acc.length()];
            String[] datalist = new String[(int)acc.length()/recordSize];
            acc.read(data);
            String dataStr = new String(data, StandardCharsets.UTF_8);
            StringBuilder s= new StringBuilder();

            int a=0;
            for (int i =0; i<dataStr.length(); i++){
                s.append(dataStr.charAt(i));
                if ((i+1) % recordSize == 0){
                    datalist[a] = s.toString();
                    s = new StringBuilder();
                    a++;
                }
            }
            for (String one: datalist) {
                System.out.println(one);
            }

            acc.close();
            /***/
            acc= new RandomAccessFile(book_fn,"rw");
            String idx="",name="",dae="",yea="",exp="",isDae="";
            idx =writeAtoB("1234567890",30);
            acc.seek(10);
            acc.write(idx.getBytes());
            acc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void 도서정보삭제(){

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

    private  String writeBookInfo(Scanner input,String s,String str,int length){
        for (;;){
                String book;
                System.out.print(str+" ("+length+"자 이내) : ");
                book = input.nextLine();

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

    private  String writeAtoB(String str,int length){
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
