package library;

public class Person {
    String name;
    String passwd;
    String who;

    public Person(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    protected void intro(String said){
        System.out.println(name+" ("+who+") : "+said);
    }

    void 나가기(){
        intro("나가기");
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getWho() {
        return who;
    }

    protected void setWho(String who) {
        this.who = who;
    }
}
