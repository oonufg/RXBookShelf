package ru.pablo.Services;


import org.springframework.stereotype.Service;
import ru.pablo.Domain.Entities.Book;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.MediaService.Entities.MediaFile;
import ru.pablo.PersistanceImpl.Repositories.ShelfRepository;
import ru.pablo.Services.DTO.BookDTO;
import ru.pablo.Services.DTO.ShelfDTO;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShelfService {
    private static ShelfRepository shelfRepository;

    static{
        shelfRepository = new ShelfRepository();
    }

    public void updateShelf(ShelfDTO shelfDTO){
        shelfRepository.changeShelf(new Shelf(shelfDTO.id(), shelfDTO.title()));
    }

    public void addBookToShelf(long shelfId, BookDTO bookDTO){
        Shelf currentShelf = shelfRepository.getShelf(shelfId);
        currentShelf.addBook(new Book(bookDTO.tile(), bookDTO.description(), bookDTO.mediaFile()));
    }

    public void deleteBookFromShelf(long shelfId, BookDTO bookDTO){
        Shelf currentShelf = shelfRepository.getShelf(shelfId);
        currentShelf.deleteBook(new Book(bookDTO.id(), bookDTO.tile(), bookDTO.description(), bookDTO.payloadId()));
    }

    public MediaFile getBook(long shelfId, long bookId){
        Shelf currentShelf = shelfRepository.getShelf(shelfId);
        return currentShelf.getBook(bookId).getPayload();
    }

    public ShelfDTO getShelf(long shelfId){
        Shelf shelf = shelfRepository.getShelf(shelfId);
        List<BookDTO> booksDTO = new LinkedList<>();
        for(Book currentBook: shelf.getBooks()){
            booksDTO.add(new BookDTO(currentBook.getId(), currentBook.getTitle(), currentBook.getDescription(), currentBook.getPayloadId(),null ));
        }
        return new ShelfDTO(shelf.getId(), shelf.getTitle(), booksDTO);
    }

}
