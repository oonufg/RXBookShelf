package ru.pablo.Services;

import org.springframework.stereotype.Service;
import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyExistsException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyInSubscribesException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotInSubscribesException;
import ru.pablo.Domain.Exceptions.Shelf.ShelfAlreadyExistException;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.PersistanceImpl.DAO.BookshelfDAO;
import ru.pablo.PersistanceImpl.Repositories.BookshelfRepository;
import ru.pablo.PersistanceImpl.Repositories.SubscribeBookshelfRepository;
import ru.pablo.Services.DTO.BookshelfDTO;
import ru.pablo.Services.DTO.ShelfDTO;

import java.util.LinkedList;
import java.util.List;

@Service
public class BookshelfService {
    private static BookshelfRepository bookshelfRepository;
    private static SubscribeBookshelfRepository subscribeBookshelfRepository;
    private static BookshelfDAO bookshelfDAO;

    static {
        bookshelfRepository = new BookshelfRepository();
        subscribeBookshelfRepository = new SubscribeBookshelfRepository();
        bookshelfDAO = new BookshelfDAO();
    }

    public void createBookshelf(long userId, BookshelfDTO bookshelfDTO) throws BookshelfAlreadyExistsException {
        bookshelfRepository.appendBookshelf(userId, new Bookshelf(bookshelfDTO.title()));
    }

    public void deleteBookshelf(long userId, long bookshelfId) throws UserNotHaveAccessException, BookshelfNotExistException {
        bookshelfRepository.deleteBookshelf(userId, bookshelfId);
    }

    public void changeBookshelf(long userId, BookshelfDTO bookshelfDTO) throws UserNotHaveAccessException, BookshelfNotExistException {
        if(bookshelfDAO.isUserOwnerOfBookshelf(userId, bookshelfDTO.id())) {
            bookshelfRepository.changeBookshelf(userId, new Bookshelf(bookshelfDTO.id(), bookshelfDTO.title()));
        }else{
            throw new UserNotHaveAccessException();
        }
    }

    public List<BookshelfDTO> getBookshelves(long userId) {
        List<BookshelfDTO> result = new LinkedList<>();
        for (Bookshelf bookshelf : bookshelfRepository.getBookshelves(userId)) {
            result.add(new BookshelfDTO(bookshelf.getId(), bookshelf.getTitle(), null));
        }
        return result;
    }

    public BookshelfDTO getBookshelf(long userId, long bookshelfId) throws BookshelfNotExistException {
        Bookshelf bookshelf = bookshelfRepository.getBookshelf(bookshelfId);
        List<ShelfDTO> shelfDTOS = new LinkedList<>();
        for (Shelf shelf : bookshelf.getShelves()) {
            shelfDTOS.add(new ShelfDTO(shelf.getId(), shelf.getTitle(), null));
        }
        return new BookshelfDTO(bookshelf.getId(), bookshelf.getTitle(), shelfDTOS);

    }

    public void addShelfToBookshelf(long userId, long bookshelfId, ShelfDTO shelfDTO) throws BookshelfNotExistException, UserNotHaveAccessException, ShelfAlreadyExistException {
        if(bookshelfDAO.isUserOwnerOfBookshelf(userId, bookshelfId)) {
            Bookshelf currentBookshelf = bookshelfRepository.getBookshelf(bookshelfId);
            currentBookshelf.addShelf(new Shelf(shelfDTO.id(), shelfDTO.title()));
        }else{
            throw new UserNotHaveAccessException();
        }
    }

    public void deleteShelfFromBookshelf(long userId, BookshelfDTO bookshelfDTO, ShelfDTO shelfDTO) throws BookshelfNotExistException, UserNotHaveAccessException, ShelfNotExistsException {
        if (bookshelfDAO.isUserOwnerOfBookshelf(userId, bookshelfDTO.id())) {
            Bookshelf bookshelf = bookshelfRepository.getBookshelf(bookshelfDTO.id());
            bookshelf.deleteShelf(shelfDTO.id());
        } else {
            throw new UserNotHaveAccessException();
        }
    }

    public List<BookshelfDTO> getSubscribeBookshelves(long userId){
        List<BookshelfDTO> result = new LinkedList<>();
        for (Bookshelf bookshelf : subscribeBookshelfRepository.getSubscribeBookshelves(userId)) {
            result.add(new BookshelfDTO(bookshelf.getId(), bookshelf.getTitle(), null));
        }
        return result;
    }


    public void subscribeToBookshelf(long userId, BookshelfDTO bookshelfDTO) throws BookshelfAlreadyInSubscribesException, BookshelfNotExistException {
        subscribeBookshelfRepository.addBookshelfToSubscribes(userId, new Bookshelf(bookshelfDTO.id(), bookshelfDTO.title()));
    }


    public void unsubscribeFromBookshelf(long userId, BookshelfDTO bookshelfDTO) throws BookshelfNotInSubscribesException, BookshelfNotExistException {
        subscribeBookshelfRepository.deleteBookshelfFromSubscribes(userId, new Bookshelf(bookshelfDTO.id(), bookshelfDTO.title()));
    }

}