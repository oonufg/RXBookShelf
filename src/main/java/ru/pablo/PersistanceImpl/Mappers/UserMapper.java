package ru.pablo.PersistanceImpl.Mappers;

import ru.pablo.Spring.Security.ApplicationUser;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserMapper {
    public static ApplicationUser mapUser(Map<String, Object> unmappedUser){
        ApplicationUser user = new ApplicationUser(
                (Long)unmappedUser.get("id"),
                (String)unmappedUser.get("nickname"),
                (String)unmappedUser.get("password")
        );
        return user;
    }

    public static List<ApplicationUser> mapUserList(List<Map<String, Object>> unmappedUsers){
        List<ApplicationUser> result = new LinkedList<>();
        for(Map<String, Object> unmappedUser: unmappedUsers){
            result.add(mapUser(unmappedUser));
        }
        return result;
    }
}
