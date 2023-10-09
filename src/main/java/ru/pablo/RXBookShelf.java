package ru.pablo;

import ru.pablo.Domain.Entities.Shelf;

public class RXBookShelf{
    public static void main(String[] args){
        Shelf shelf = new Shelf(1,"Java");
        System.out.println(shelf.getBooks().get(0).getTitle());
    }
}