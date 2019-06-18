package library;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MysqlConnect {

    private Connection con = null;
    private Statement stmt = null;
    private String table_book = "BookInfo";
    private String table_person = "PersonInfo";
    private Student student;
    private Faculty faculty;
    private boolean isStudent = false;
    private boolean isFaculty = false;

    MysqlConnect() {
        connect();
    }

    void connStudent(Student student){
        this.student = student;
        isStudent = true;

        Date today = new java.sql.Date(new Date().getTime());
        BookInfo book;
        book = first_checking("loan");

        book = first_checking("book");
    }
    void connFaculty(Faculty faculty){
        this.faculty = faculty;
        isFaculty = true;
    }

    void connect(){
        String server = "localhost"; // MySQL 서버 주소
        String database = "guswns_db"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "wnsgusgk3537"; // MySQL 서버 비밀번호

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server +"/"+database  + "?characterEncoding=UTF-8&serverTimezone=UTC", user_name, password);
//            System.out.println("정상적으로 연결되었습니다.");
            stmt = con.createStatement();
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

    }
    void disconnect(){
        // 3.해제
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("discon 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /** person */
    //삽입
     void personinsert(String name, String passwd, String identity){
        String isUser = personselect(name, passwd);
        if (isUser.equals("0") || isUser.equals("1")){
            StringBuilder sb = new StringBuilder();
            String sql = sb.append("insert into " + table_person + " (name,password,identity) values (")
                    .append("'" +name + "',")
                    .append("'" + passwd + "',")
                    .append("'" + identity + "'")
                    .append(")")
                    .append(";")
                    .toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else if (isUser.equals("2") || !isUser.equals("")){
            System.out.println("이미 있는 아이디입니다.");
        }

    }
    //검색
     String personselect(String name, String passwd){
        String identity = "0";
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table_person + " where")
                .append(" name = ")
                .append("'"+name+"'")
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String name2 = rs.getString("name");
                String passwd2 = rs.getString("password");
                if (name2 ==null || name2.equals("")){
//                    System.out.println("존재하지 않은 회원입니다.");
                    identity = "1";
                }else {
                    if (!passwd2.equals(passwd)){
//                        System.out.println("비밀번호가 일치하지 않습니다");
                        identity = "2";
                    }else{
                        identity = rs.getString("identity");
                    }

                }
            }


        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return identity;
    }


    /** book -admin */
    // 삽입
     void bookinsert(String name,String author,String loaner, String booker) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into " + table_book + " (name,author,loaner,booker) values (")
                .append("'" +name + "',")
                .append("'" + author + "',")
                .append("'" + loaner + "',")
                .append("'" + booker + "'")
                .append(");")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // 삭제
     void bookdelete(int idx) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("delete from " + table_book + " where idx = ")
                .append(idx)
                .append(";")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // 수정
     void bookupdate(int idx, String name,String author,String loaner, String booker) {
        StringBuilder sb = new StringBuilder();
        sb.append("update " + table_book + " set");

        if (!name.equals("!")) {
            sb.append(" name = ")
            .append("'" + name + "'");
        }
        if (!name.equals("!") && !author.equals("!")) {
            sb.append(", author = ")
                    .append("'" + author + "'");
        }else if (name.equals("!") && !author.equals("!")){
            sb.append(" author = ")
                    .append("'" + author + "'");
        }

        if (!author.equals("!") && !loaner.equals("!")) {
            sb.append(", loaner = ")
                    .append("'" + loaner + "'");
        }else if (author.equals("!") && !loaner.equals("!")){
            sb.append(" loaner = ")
                    .append("'" + loaner + "'");
        }

        if (!loaner.equals("!") && !booker.equals("!")) {
            sb.append(", booker = ")
                    .append("'" + booker + "'");
        }else if (loaner.equals("!") && !booker.equals("!")){
            sb.append(" booker = ")
                    .append("'" + booker + "'");
        }

        if (!(name.equals("!") && author.equals("!") && loaner.equals("!") && booker.equals("!"))){
            sb.append(" where idx = ")
                    .append(idx)
                    .append(";");

            String sql = sb.toString();
            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    // 모든 검색
     void bookselectWhere(String str) {
         String username = null;
         if (isStudent)
             username = student.getName();
         else if (isFaculty)
             username = faculty.getName();

        StringBuilder sb = new StringBuilder();
        String sql = null;
        switch (str){
            case "all":
                sql = sb.append("select * from " + table_book)
                        .append(";").toString();
                break;
            case "loan":
                sql = sb.append("select * from " + table_book + " where")
                        .append(" loaner = ")
                        .append("'"+username+"'")
                        .append(";").toString();
                break;
            case "book":
                sql = sb.append("select * from " + table_book + " where")
                        .append(" booker = ")
                        .append("'"+username+"'")
                        .append(";").toString();
                break;
        }

        try {
            ResultSet rs = stmt.executeQuery(sql);

            System.out.print("책 번호 |");
            System.out.print("\n");
            System.out.print("책 이름 |");
            System.out.print("\t");
            System.out.print("책 작가 |");
            System.out.print("\n");
            System.out.print("책 대출자 |");
            System.out.print("\t");
            System.out.print("책 반납일 |");
            System.out.print("\n");
            System.out.print("책 예약자 |");
            System.out.print("\n");
            System.out.println("────────────────────────────");

            while(rs.next()){
                System.out.print(rs.getInt("idx")+" 번 | ");
                System.out.print("\n");
                System.out.print(rs.getString("name")+" | ");
                System.out.print("\t");
                System.out.print(rs.getString("author")+" 저 | ");
                System.out.print("\n");
                String loaner = (rs.getString("loaner").equals("")? "<없음>" : rs.getString("loaner"));
                System.out.print(loaner+" | ");
                System.out.print("\t");
                String expire_day = (rs.getString("expire_day") == null? "<없음>" : rs.getString("expire_day"));
                System.out.print(expire_day+" 까지 | ");
                System.out.print("\n");
                String booker = (rs.getString("booker").equals("")? "<없음>" : rs.getString("booker"));
                System.out.print(booker+" | ");
                System.out.print(" ==> 대출가능 여부 : ");
                if (loaner.equals("<없음>"))
                    System.out.print("O");
                else
                    System.out.print("X");
                System.out.print(" , 예약가능 여부 : ");
                if (booker.equals("<없음>"))
                    System.out.println("O");
                else
                    System.out.println("X");
                System.out.println("-------------------------------------------------------");
            }

            System.out.print("\n");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // 검색
     void booksearch(String str,String str2) {

        StringBuilder sb = new StringBuilder();
        String sql = null;
         switch (str){
             case "이름":
                 sql = sb.append("select * from " + table_book + " where")
                         .append(" name like ")
                         .append("'%")
                         .append(str2)
                         .append("%'")
                         .append(";").toString();
                 break;
             case "작가":
                 sql = sb.append("select * from " + table_book + " where")
                         .append(" author like ")
                         .append("'%")
                         .append(str2)
                         .append("%'")
                         .append(";").toString();
                 break;
             case "번호":
                 sql = sb.append("select * from " + table_book + " where")
                         .append(" idx = ")
                         .append(Integer.parseInt(str2))
                         .append(";").toString();
                 break;
         }
        try {
            ResultSet rs = stmt.executeQuery(sql);

            System.out.print("책 번호 |");
            System.out.print("\n");
            System.out.print("책 이름 |");
            System.out.print("\t");
            System.out.print("책 작가 |");
            System.out.print("\n");
            System.out.print("책 대출자 |");
            System.out.print("\t");
            System.out.print("책 반납일 |");
            System.out.print("\n");
            System.out.print("책 예약자 |");
            System.out.print("\n");
            System.out.println("────────────────────────────");

            while(rs.next()){
                System.out.print(rs.getInt("idx")+" 번 | ");
                System.out.print("\n");
                System.out.print(rs.getString("name")+" | ");
                System.out.print("\t");
                System.out.print(rs.getString("author")+" 저 | ");
                System.out.print("\n");
                String loaner = (rs.getString("loaner").equals("")? "<없음>" : rs.getString("loaner"));
                System.out.print(loaner+" | ");
                System.out.print("\t");
                String expire_day = (rs.getString("expire_day") == null? "<없음>" : rs.getString("expire_day"));
                System.out.print(expire_day+" 까지 | ");
                System.out.print("\n");
                String booker = (rs.getString("booker").equals("")? "<없음>" : rs.getString("booker"));
                System.out.print(booker+" | ");
                System.out.print(" ==> 대출가능 여부 : ");
                if (loaner.equals("<없음>"))
                    System.out.print("O");
                else
                    System.out.print("X");
                System.out.print(" , 예약가능 여부 : ");
                if (booker.equals("<없음>"))
                    System.out.println("O");
                else
                    System.out.println("X");


                System.out.println("-------------------------------------------------------");
            }
            System.out.print("\n");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** book -user */
    private BookInfo first_checking(String str){
        String username = null;
        if (isStudent)
            username = student.getName();
        else if (isFaculty)
            username = faculty.getName();

        BookInfo book = null;
        StringBuilder sb = new StringBuilder();
        String sql = null;
        switch (str){
            case "loan":
                sql = sb.append("select * from " + table_book + " where")
                        .append(" loaner = ")
                        .append("'"+username+"'")
                        .append(";").toString();
                break;
            case "book":
                sql = sb.append("select * from " + table_book + " where")
                        .append(" booker = ")
                        .append("'"+username+"'")
                        .append(";").toString();
                break;
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String expire_day = (rs.getString("expire_day") == null? "<없음>" : rs.getString("expire_day"));
                book = new BookInfo(
                        rs.getString("idx"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("loaner"),
                        rs.getString("booker"),
                        expire_day
                );
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (!book.getExpire_day().equals("<없음>")){
                    Date date1 = sdf.parse(book.getExpire_day());
                    Date date2 = sdf.parse(sdf.format(new Date()));
                    if (date1.before(date2)){

                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return book;
    }

    private int user_checking(){
        int num = 0;
        String username = null;
        int max_book = 0;
        if (isStudent){
            username = student.getName();
            max_book = student.Max_books;
        }else if (isFaculty){
            username = faculty.getName();
            max_book = faculty.Max_books;
        }


        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table_book + " where ")
                        .append(" loaner = ")
                        .append("'"+username+"'")
                        .toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
                num++;

//            isOk = max_book < num;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return num;
    }

    private BookInfo book_checking(int idx){
        BookInfo book = null;
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table_book + " where")
                        .append(" idx = ")
                        .append(idx)
                        .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String expire_day = (rs.getString("expire_day") == null? "<없음>" : rs.getString("expire_day"));
                book = new BookInfo(
                        rs.getString("idx"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("loaner"),
                        rs.getString("booker"),
                        expire_day
                );
            }

        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return book;
    }
    // 예약
    void book_booking(int idx){
        String username = null;
        if (isStudent)
            username = student.getName();
        else if (isFaculty)
            username = faculty.getName();

        String check = book_checking(idx).booker;

        if (check.equals("")){
            if (!book_checking(idx).loaner.equals(username)){
                    StringBuilder sb = new StringBuilder();
                    String sql =  sb.append("update " + table_book + " set")
                            .append(" booker = ")
                            .append("'" + username + "'")
                            .append(" where idx = ")
                            .append(idx)
                            .append(";").toString();
                    try {
                        stmt.executeUpdate(sql);
                        System.out.println("예약 완료하였습니다.");

                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            }else {
                System.out.println("대출한 책은 예약을 할 수 없습니다");
            }
        }else {
            if (check.equals(username))
                System.out.println("이미 예약한 책입니다.");
            else
                System.out.println("예약 불가 (예약자 : "+check+")");
        }

    }
    // 대출
    void book_loaning(int idx){
        String username = null;
        int max_book = 0;
        if (isStudent){
            username = student.getName();
            max_book = student.Max_books;
        }else if (isFaculty){
            username = faculty.getName();
            max_book = faculty.Max_books;
        }


        if (book_checking(idx)!=null){
            String check = book_checking(idx).loaner;
            if (check.equals("")){
                if (max_book > user_checking()){
                    StringBuilder sb = new StringBuilder();
                    String sql =  sb.append("update " + table_book + " set")
                            .append(" loaner = ")
                            .append("'" + username + "'")
                            .append(" , ")
                            .append(" expire_day =  DATE_ADD( CURDATE(),INTERVAL 10 DAY) ")
                            .append(" where idx = ")
                            .append(idx)
                            .append(";").toString();
                    try {
                        stmt.executeUpdate(sql);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("현재 대출중인 책이 <"+user_checking()+" / "+max_book+">권이므로 대출 불가능합니다.");
                }


            }else {
                if (check.equals(username))
                    System.out.println("이미 대출한 책입니다.");
                else
                    System.out.println("대출 불가 (대출자 : "+check+")");
            }
        }else {
            System.out.println("존재하지 않는 책 번호 입니다.");
        }


    }
    // 반납
    void book_return(int idx){
        String username = null;
        if (isStudent)
            username = student.getName();
        else if (isFaculty)
            username = faculty.getName();

        String check = book_checking(idx).loaner;
        if (!check.equals("")){
            if (check.equals(username)){
                StringBuilder sb = new StringBuilder();
                String sql =  sb.append("update " + table_book + " set")
                        .append(" loaner = ")
                        .append("''")
                        .append(",")
                        .append(" expire_day = null ")
                        .append(" where idx = ")
                        .append(idx)
                        .append(";").toString();
                try {
                    stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                System.out.println(username+"님께서 대출한 책이 아닙니다.");
            }
        }else {
            System.out.println(username+"님께서 대출한 책이 아닙니다.");
        }
    }
    // 반납일 연장
    void book_extending_exp(int idx){
        String username = null;
        if (isStudent)
            username = student.getName();
        else if (isFaculty)
            username = faculty.getName();


        StringBuilder sb = new StringBuilder();
        String sql =  sb.append("update " + table_book + " set ")
                        .append("expire_day = DATE_ADD( expire_day, INTERVAL 7 DAY) where ")
                        .append(" loaner = ")
                        .append("'"+username+"' and ")
                        .append(" idx = ")
                        .append(idx)
                        .append(";").toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
