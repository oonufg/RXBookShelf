package ru.pablo.Spring.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.pablo.PersistanceImpl.Tables.UserTable;

import java.util.Map;

public class CUserDetailsService implements UserDetailsService {
    static UserTable users = new UserTable();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(users.isUserExists(username)){
            Map<String, Object> unmappedUser = users.getUserByNickname(username);
            return new ApplicationUser(
                    (Long)unmappedUser.get("id"),
                    (String)unmappedUser.get("nickname"),
                    (String)unmappedUser.get("password")
            );
        }else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void save(ApplicationUser user){
        if(!users.isUserExists(user.getUsername())){
            users.addUser(user.getUsername(), passwordEncoder.encode(user.getPassword()));
        }
    }
}