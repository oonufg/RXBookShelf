package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Book;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Persistance.IBookRepository;

import java.util.List;

public class BookRepository implements IBookRepository {

    @Override
    public Book getBook(long bookId) {
        return null;
    }

    @Override
    public List<Book> getBooks(Shelf shelf) {
        return null;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void deleteBook(Book book) {

    }
}
