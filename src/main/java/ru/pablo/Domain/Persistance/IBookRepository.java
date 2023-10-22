package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Book;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Exceptions.Book.BookAlreadyOnShelfException;
import ru.pablo.Domain.Exceptions.Book.BookNotExistException;

import java.util.List;

public interface IBookRepository {
    Book getBook(long bookId) throws BookNotExistException;
    List<Book> getBooks(long shelfId);
    void addBook(long shelfId, Book book) throws BookAlreadyOnShelfException;
    void deleteBook(long shelfId, Book book) throws BookNotExistException;
}