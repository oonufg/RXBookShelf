package ru.pablo.Spring.Security.JWT;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class JWTAuthentification implements Authentication {
    private boolean isAuthentificated = false;
    private String  id;

    private List<SimpleGrantedAuthority> authorities;
    public JWTAuthentification(String id, List<SimpleGrantedAuthority> authorities){
        this.id = id;
        this.authorities = authorities;


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
        return null;
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
        return this.id;
    }
}
