package ru.pablo;

import ru.pablo.Domain.Entities.Book;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.MediaService.Entities.MediaFile;

public class RXBookShelf{
    public static void main(String[] args){
        Shelf shelf = new Shelf(1,"Java");
        System.out.println(shelf.getBooks().get(1).getPayload().getPayload().toString());
        System.out.println(new String(shelf.getBooks().get(1).getPayload().getPayload()));
    }
}