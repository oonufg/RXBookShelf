package Domain.Entities;

import Domain.Persistance.IBookRepository;

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
        return null;
    }
    public List<Book> getBooks(){
        return null;
    }
}
