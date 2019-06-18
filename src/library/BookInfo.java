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

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLoaner() {
        return loaner;
    }

    public void setLoaner(String loaner) {
        this.loaner = loaner;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public String getExpire_day() {
        return expire_day;
    }

    public void setExpire_day(String expire_day) {
        this.expire_day = expire_day;
    }
}
