package ru.pablo.PersistanceImpl.DAO;

import ru.pablo.PersistanceImpl.Tables.BookshelfTable;

public class BookshelfDAO {
    private static BookshelfTable bookshelfTable;
    static{
        bookshelfTable = new BookshelfTable();
    }
    public boolean isUserOwnerOfBookshelf(long userId, long bookshelfId){
        return bookshelfTable.isUserOwnerOfBookshelf(userId, bookshelfId);
    }

}
