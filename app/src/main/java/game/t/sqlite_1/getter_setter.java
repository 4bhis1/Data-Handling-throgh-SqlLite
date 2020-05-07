package game.t.sqlite_1;

public class getter_setter {

    private int id;
    private String name;
    private String content;

    public getter_setter() {//When nothing is there no then also it will be add something

    }

    public getter_setter(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public getter_setter(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
