package ru.pablo;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Entities.UserBookshelf;
import ru.pablo.PersistanceImpl.Repositories.BookshelfRepository;

public class RXBookShelf{
    public static void main(String[] args){
        BookshelfRepository repository = new BookshelfRepository();
        UserBookshelf b = repository.getBookshelf(1l, 1l);
        Shelf shelf = new Shelf("Architecture");
        //b.addShelf(shelf);
        for(Shelf s: b.getShelves()){
            System.out.println(s.getTitle());
        }
    }
}