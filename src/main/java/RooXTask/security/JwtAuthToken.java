package RooXTask.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class JwtAuthToken implements Authentication {

    @JsonProperty("sub")
    private UUID id;
    private String token;

    @JsonProperty("username")
    private String name;
    private boolean authenticated;
    private List<String> roles;

    private Object principal;
    private Collection<GrantedAuthority> authorities;

    public JwtAuthToken() {
    }

    public JwtAuthToken(String token) {
        this.token = token;
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
        return this.principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        if (this.authorities == null) {
            ArrayList<GrantedAuthority> temp = new ArrayList<>(roles.size());
            for (String role : roles) {
                temp.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            }
            this.authorities = Collections.unmodifiableList(temp);
        }
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}