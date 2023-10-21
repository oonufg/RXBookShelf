package ru.pablo.Services;


import org.springframework.stereotype.Service;
import ru.pablo.Domain.Entities.Book;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.Domain.MediaService.Entities.MediaFile;
import ru.pablo.PersistanceImpl.DAO.ShelfDAO;
import ru.pablo.PersistanceImpl.Repositories.ShelfRepository;
import ru.pablo.Services.DTO.BookDTO;
import ru.pablo.Services.DTO.ShelfDTO;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShelfService {
    private static ShelfRepository shelfRepository;
    private static ShelfDAO shelfDAO;

    static{
        shelfRepository = new ShelfRepository();
        shelfDAO = new ShelfDAO();
    }

    public void updateShelf(long userID, ShelfDTO shelfDTO) throws UserNotHaveAccessException, ShelfNotExistsException {
        shelfRepository.changeShelf(userID, new Shelf(shelfDTO.id(), shelfDTO.title()));
    }

    public void addBookToShelf(long userID, long shelfId, BookDTO bookDTO) throws UserNotHaveAccessException{
        if(shelfDAO.isUserOwnerOfShelf(userID, shelfId)) {
            Shelf currentShelf = shelfRepository.getShelf(shelfId);
            currentShelf.addBook(new Book(bookDTO.tile(), bookDTO.description(), bookDTO.mediaFile()));
        }else{
            throw new UserNotHaveAccessException();
        }
    }

    public void deleteBookFromShelf(long userId, long shelfId, BookDTO bookDTO) throws UserNotHaveAccessException{
        if(shelfDAO.isUserOwnerOfShelf(userId, shelfId)) {
            Shelf currentShelf = shelfRepository.getShelf(shelfId);
            currentShelf.deleteBook(new Book(bookDTO.id(), bookDTO.tile(), bookDTO.description(), bookDTO.payloadId()));
        }else{
            throw new UserNotHaveAccessException();
        }
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
