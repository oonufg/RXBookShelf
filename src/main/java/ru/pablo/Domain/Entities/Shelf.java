package ru.pablo.Domain.Entities;

import ru.pablo.Domain.Persistance.IBookRepository;
import ru.pablo.PersistanceImpl.Repositories.BookRepository;

import java.util.List;

public class Shelf {
    private long id;
    private String title;
    private IBookRepository bookRepository;

    public Shelf(long id, String title){
        this.id = id;
        this.title = title;
        this.bookRepository = new BookRepository();
    }
    public Shelf(String title){
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Book getBook(long bookId){
        return bookRepository.getBook(bookId);
    }

    public List<Book> getBooks(){
        return bookRepository.getBooks(id);
    }

    public void addBook(Book bookToAdd){
        bookRepository.addBook(id, bookToAdd);
    }

    public void deleteBook(Book book){
        bookRepository.deleteBook(id, book);
    }
}