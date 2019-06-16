package library;

import java.sql.*;

public class MysqlConnect {

    Connection con = null;
    Statement stmt = null;
    String table = null;

    MysqlConnect() {
        connect();
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
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?characterEncoding=UTF-8&serverTimezone=UTC", user_name, password);
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

    void showdatabase(){
        try {
            java.sql.Statement st = null;
            ResultSet rs = null;
            st = con.createStatement();
            rs = st.executeQuery("SHOW DATABASES");
            if (st.execute("SHOW DATABASES")) {
                rs = st.getResultSet();
            }
            while (rs.next()) {
                String str = rs.getNString(1);
                System.out.println(str);
            }
        }catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void adminAction(int num){
        switch (num){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }

    }

    public void peopleAction(int num){

    }

    // 삽입
    private void bookinsert(String name,String author,String loaner, String booker) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into " + table + " values(")
                .append(name + "',")
                .append("'" + author + "',")
                .append("'" + loaner + "',")
                .append(booker)
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
    private void bookdelete(int idx) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("delete from " + table + " where idx = ")
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
    private void bookupdate(int idx, String name,String author,String loaner, String booker) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("update " + table + " set")
                .append(" name = ")
                .append("'" + name + "',")
                .append(" author = ")
                .append("'" + author + "',")
                .append(" loaner = ")
                .append("'" + loaner + "',")
                .append(" booker = ")
                .append(booker)
                .append(" where idx = ")
                .append(idx)
                .append(";").toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 모든 검색
    private void bookselectAll() {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table)
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);

            System.out.print("idx");
            System.out.print("\t");
            System.out.print("name");
            System.out.print("\t");
            System.out.print("author");
            System.out.print("\t");
            System.out.print("loaner");
            System.out.print("\t");
            System.out.print("booker");
            System.out.print("\n");
            System.out.println("────────────────────────");

            while(rs.next()){
                System.out.print(rs.getInt("idx"));
                System.out.print("\t");
                System.out.print(rs.getString("name"));
                System.out.print("\t");
                System.out.print(rs.getString("author"));
                System.out.print("\t");
                System.out.print(rs.getString("loaner"));
                System.out.print("\t");
                System.out.print(rs.getString("booker"));
                System.out.print("\n");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 검색
    private void bookselect(int idx) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from " + table + " where")
                .append(" idx = ")
                .append(idx)
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);

            System.out.print("idx");
            System.out.print("\t");
            System.out.print("name");
            System.out.print("\t");
            System.out.print("author");
            System.out.print("\t");
            System.out.print("loaner");
            System.out.print("\t");
            System.out.print("booker");
            System.out.print("\n");
            System.out.println("────────────────────────");

            while(rs.next()){
                System.out.print(rs.getInt("idx"));
                System.out.print("\t");
                System.out.print(rs.getString("name"));
                System.out.print("\t");
                System.out.print(rs.getString("author"));
                System.out.print("\t");
                System.out.print(rs.getString("loaner"));
                System.out.print("\t");
                System.out.print(rs.getString("booker"));
                System.out.print("\n");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
