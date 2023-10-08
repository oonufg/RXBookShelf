package ru.pablo.Domain.Entities;

import ru.pablo.Domain.Persistance.IShelfRepository;

import java.util.List;

public class Bookshelf {
    protected String title;

    public Bookshelf(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }



}
