package ru.pablo.Domain.Entities;

import ru.pablo.Domain.Persistance.IBookRepository;

import java.util.List;

public class Shelf {
    private long id;
    private String title;
    private IBookRepository bookRepository;

    public Shelf(long id, String title){
        this.id = id;
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
        return bookRepository.getBooks(this);
    }

    public void addBook(Book bookToAdd){
        bookRepository.addBook(bookToAdd);
    }

    public void deleteBook(Book book){
        bookRepository.deleteBook(book);
    }
}