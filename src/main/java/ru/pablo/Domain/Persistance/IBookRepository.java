package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Book;
import ru.pablo.Domain.Entities.Shelf;

import java.util.List;

public interface IBookRepository {
    Book getBook(long bookId);
    List<Book> getBooks(long shelfId);
    void addBook(long shelfId, Book book);
    void deleteBook(long shelfId, Book book);
}