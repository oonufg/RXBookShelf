package ru.pablo.Domain.Entities;

public class Bookshelf {
    protected Long id;
    protected String title;

    public Bookshelf(Long id, String title){
        this.id = id;
        this.title = title;
    }

    public Bookshelf(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }



}
