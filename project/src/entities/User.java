package entities;

public class User {
    private int id;
    private String name;
    private String nickname;
    private String password;

    public User(Integer id, String name, String nickname, String password) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getNickname(){
        return this.nickname;
    }
    public String getPassword(){
        return this.password;
    }
}
