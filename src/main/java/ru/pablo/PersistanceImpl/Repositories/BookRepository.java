package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Book;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Persistance.IBookRepository;
import ru.pablo.PersistanceImpl.Mappers.BookMapper;
import ru.pablo.PersistanceImpl.Repositories.Tables.BookTable;

import java.util.List;

public class BookRepository implements IBookRepository {
    private static BookTable bookTable;
    static{
        bookTable = new BookTable();
    }

    @Override
    public Book getBook(long bookId) {
        return null;
    }

    @Override
    public List<Book> getBooks(long shelfId) {
        return BookMapper.mapBookList(bookTable.getBooks(shelfId));
    }

    @Override
    public void addBook(long shelfId, Book book) {

        bookTable.addBook(book.getTitle(), book.getDescription(), shelfId, book.getPayloadId());
    }

    @Override
    public void deleteBook(long shelfId, Book book) {

    }
}
