package ru.pablo.PersistanceImpl.Mappers;

import ru.pablo.Domain.Entities.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserMapper {
    public static User mapUser(Map<String, Object> unmappedUser){
        User user = new User(
                (String)unmappedUser.get("nickname"),
                (String)unmappedUser.get("password")
        );
        return user;
    }

    public static List<User> mapUserList(List<Map<String, Object>> unmappedUsers){
        List<User> result = new LinkedList<>();
        for(Map<String, Object> unmappedUser: unmappedUsers){
            result.add(mapUser(unmappedUser));
        }
        return result;
    }
}
