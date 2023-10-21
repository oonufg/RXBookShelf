package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.Domain.Persistance.IShelfRepository;
import ru.pablo.PersistanceImpl.Mappers.ShelfMapper;
import ru.pablo.PersistanceImpl.Tables.ShelfTable;

import java.util.List;

public class ShelfRepository implements IShelfRepository {
    private static ShelfTable shelfTable;
    static {
        shelfTable = new ShelfTable();
    }

    @Override
    public List<Shelf> getShelves(long bookshelfId) {
        return ShelfMapper.mapShelfList(shelfTable.getShelves(bookshelfId));
    }

    @Override
    public Shelf getShelf(long shelfId) {
        return ShelfMapper.mapShelf(shelfTable.getShelf(shelfId));
    }

    @Override
    public void appendShelf(long bookshelfId, Shelf shelfToAdd) {
        shelfTable.addShelf(bookshelfId, shelfToAdd.getTitle());
    }

    @Override
    public void deleteShelf(long shelfId){
        shelfTable.deleteShelf(shelfId);
    }

    @Override
    public void changeShelf(long userId, Shelf shelfToChange)  throws UserNotHaveAccessException, ShelfNotExistsException{
        if(shelfTable.isShelfExists(shelfToChange.getId())) {
            if (shelfTable.isUserOwnerOfShelf(userId, shelfToChange.getId())) {
                shelfTable.changeShelf(shelfToChange.getId(), shelfToChange.getTitle());
            } else {
                throw new UserNotHaveAccessException();
            }
        }else{
            throw new ShelfNotExistsException();
        }
    }


}
