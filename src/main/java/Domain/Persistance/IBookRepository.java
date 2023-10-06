package Domain.Persistance;

import Domain.Entities.Book;
import Domain.Entities.Shelf;
import java.util.List;

public interface IBookRepository {
    Book getBook(long bookId);
    List<Book> getBooks(Shelf shelf);
    void addBook(Book book);
    void deleteBook(Book book);
}