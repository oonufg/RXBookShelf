package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.User;
import ru.pablo.PersistanceImpl.Repositories.Tables.UserTable;
import ru.pablo.PersistanceImpl.Mappers.UserMapper;

import java.util.List;

public class UserRepository {
    private UserTable userTable;
    public UserRepository(){
        userTable = new UserTable();
    }

    public void addUser(User userToAdd){
        userTable.addUser(userToAdd.getNickname());
    }

    public User getUser(long userId){
        return UserMapper.mapUser(userTable.getUser(userId));
    }

    public List<User> getUsers(){
        return UserMapper.mapUserList(userTable.getUsers());
    }

}
