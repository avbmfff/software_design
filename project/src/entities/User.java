package entities;

public class User {
    private int id; //идентификатор пользователя
    private String name; //имя пользователя
    private String nickname; //псевдоним пользователя
    private String password; //пароль пользователя

    //создание пользователя
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
