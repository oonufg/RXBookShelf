package ru.pablo.Domain.Entities;

public class Book {
    private long id;
    private String title;
    private String description;
    private byte[] payload;

    public Book(long id, String title, String description, byte[] payload){
        this.id = id;
        this.title = title;
        this.description = description;
        this.payload = payload;
    }

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPayload(){
        return payload;
    }
}
