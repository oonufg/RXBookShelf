package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyInSubscribesException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotInSubscribesException;
import ru.pablo.PersistanceImpl.Mappers.BookshelfMapper;
import ru.pablo.PersistanceImpl.Tables.BookshelfTable;
import ru.pablo.PersistanceImpl.Tables.BookshelvesSubscribesTable;

import java.util.List;

public class SubscribeBookshelfRepository {
    private static BookshelvesSubscribesTable bookshelvesSubscribesTable;
    private static BookshelfTable bookshelfTable;

    static{
        bookshelvesSubscribesTable = new BookshelvesSubscribesTable();
        bookshelfTable = new BookshelfTable();
    }

    public List<Bookshelf> getSubscribeBookshelves(long userId){
        return BookshelfMapper.mapListBookshelf(bookshelvesSubscribesTable.getSubscribeBookshelves(userId));
    }

    public void addBookshelfToSubscribes(long userId, Bookshelf bookshelf) throws BookshelfAlreadyInSubscribesException, BookshelfNotExistException{
        if(bookshelfTable.isBookshelfExists(bookshelf.getId())) {
            if (!bookshelvesSubscribesTable.isUserSubscriberOfBookshelf(userId, bookshelf.getId())) {
                bookshelvesSubscribesTable.subscribeToBookshelf(userId, bookshelf.getId());
            } else {
                throw new BookshelfAlreadyInSubscribesException();
            }
        }else{
            throw new BookshelfNotExistException();
        }
    }

    public void deleteBookshelfFromSubscribes(long userId, Bookshelf bookshelf) throws BookshelfNotInSubscribesException, BookshelfNotExistException{
        if(bookshelfTable.isBookshelfExists(bookshelf.getId())) {
            if (bookshelvesSubscribesTable.isUserSubscriberOfBookshelf(userId, bookshelf.getId())) {
                bookshelvesSubscribesTable.unsubscribeFromBookshelf(userId, bookshelf.getId());
            } else {
                throw new BookshelfNotInSubscribesException();
            }
        }else{
            throw new BookshelfNotExistException();
        }
    }

}
