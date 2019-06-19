package library;

public class BookInfo {
    String idx,name,author,loaner,booker,expire_day;

    public BookInfo(String idx, String name, String author, String loaner, String booker, String expire_day) {
        this.idx = idx;
        this.name = name;
        this.author = author;
        this.loaner = loaner;
        this.booker = booker;
        this.expire_day = expire_day;
    }

    public String getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }


    public String getLoaner() {
        return loaner;
    }

    public String getBooker() {
        return booker;
    }

    public String getExpire_day() {
        return expire_day;
    }

}
