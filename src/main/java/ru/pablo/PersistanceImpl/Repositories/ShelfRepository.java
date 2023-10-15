package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Persistance.IShelfRepository;
import ru.pablo.PersistanceImpl.Mappers.ShelfMapper;
import ru.pablo.PersistanceImpl.Repositories.Tables.ShelfTable;

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
    public void deleteShelf(long shelfId) {
        shelfTable.deleteShelf(shelfId);
    }


}
