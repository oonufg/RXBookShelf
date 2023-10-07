package ru.pablo.Domain.Entities;

public class User {
    private long id;
    private String nickname;
    public User(long id, String nickname){
        this.id = id;
        this.nickname = nickname;
    }

    public User(String nickname){
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
