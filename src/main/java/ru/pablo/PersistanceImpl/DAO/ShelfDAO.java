package ru.pablo.PersistanceImpl.DAO;

import ru.pablo.PersistanceImpl.Tables.ShelfTable;

public class ShelfDAO {
    private static ShelfTable shelfTable;
    static {
        shelfTable = new ShelfTable();
    }

    public boolean isUserOwnerOfShelf(long userId, long shelfId){
        return shelfTable.isUserOwnerOfShelf(userId, shelfId);
    }

    public boolean isShelfExists(long shelfId){
        return shelfTable.isShelfExists(shelfId);
    }


}
