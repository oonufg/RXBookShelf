package ru.pablo;

import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Entities.UserBookshelf;

public class RXBookShelf{
    public static void main(String[] args){
        UserBookshelf shelf = new UserBookshelf(1,"Leather", true);
        Shelf shelf1 = new Shelf("BDSM");
        shelf.addShelf(shelf1);
        System.out.println(shelf.getShelves().get(0).getId());
    }
}