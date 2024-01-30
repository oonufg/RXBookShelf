package ru.pablo.Spring.Security.JWT;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.pablo.Spring.Security.ApplicationUser;

import java.util.Collection;
import java.util.List;

public class JWTAuthentification implements Authentication {
    private boolean isAuthentificated = false;
    private ApplicationUser user;

    private List<SimpleGrantedAuthority> authorities;
    public JWTAuthentification(ApplicationUser user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthentificated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthentificated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
