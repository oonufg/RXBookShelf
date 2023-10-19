package ru.pablo.Services;

import org.springframework.stereotype.Service;
import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyExistsException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.PersistanceImpl.Repositories.BookshelfRepository;
import ru.pablo.Services.DTO.BookshelfDTO;
import ru.pablo.Services.DTO.ShelfDTO;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookshelfService {
    private static BookshelfRepository bookshelfRepository;
    static {
        bookshelfRepository = new BookshelfRepository();
    }

    public void createBookshelf(long userId, BookshelfDTO bookshelfDTO) throws BookshelfAlreadyExistsException {
        bookshelfRepository.appendBookshelf(userId, new Bookshelf(bookshelfDTO.title()));
    }

    public void deleteBookshelf(long userId, long bookshelfId) throws UserNotHaveAccessException {
        bookshelfRepository.deleteBookshelf(userId, bookshelfId);
    }

    public void changeBookshelf(long userId,long bookshelfId, String title) throws UserNotHaveAccessException{
        bookshelfRepository.changeBookshelf(userId, new Bookshelf(bookshelfId, title));
    }

    public List<BookshelfDTO> getBookshelves(long userId){
        List<BookshelfDTO> result = new LinkedList<>();
        for(Bookshelf bookshelf: bookshelfRepository.getBookshelves(userId)){
            result.add(new BookshelfDTO(bookshelf.getId(), bookshelf.getTitle(),null));
        }
        return result;
    }

    public BookshelfDTO getBookshelf(long userId, long bookshelfId) throws BookshelfNotExistException{
        Bookshelf bookshelf = bookshelfRepository.getBookshelf(bookshelfId);
        List<ShelfDTO> shelfDTOS = new LinkedList<>();
        for(Shelf shelf: bookshelf.getShelves()){
            shelfDTOS.add(new ShelfDTO(shelf.getId(), shelf.getTitle(), null));
        }
        return new BookshelfDTO(bookshelf.getId(),bookshelf.getTitle(),shelfDTOS);

    }

    public void addShelfToBookshelf(long userId, long bookshelfId, ShelfDTO shelfDTO) throws BookshelfNotExistException {
        Bookshelf currentBookshelf = bookshelfRepository.getBookshelf(bookshelfId);
        currentBookshelf.addShelf(new Shelf(shelfDTO.id(), shelfDTO.title()));
    }

    public void deleteShelfFromBookshelf(long userId, BookshelfDTO bookshelfDTO, ShelfDTO shelfDTO) throws BookshelfNotExistException{
        Bookshelf bookshelf = bookshelfRepository.getBookshelf(bookshelfDTO.id());
        bookshelf.deleteShelf(shelfDTO.id());
    }
}