package com.dr3amers.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//TODO add Roles and figure out where to get booleans
public class AuthenticatedUser extends User implements UserDetails {

    public AuthenticatedUser(User user) {
        super.setId(user.getId());
        super.setEmail(user.getEmail());
        super.setName(user.getName());
        super.setSurname(user.getSurname());
        super.setNickname(user.getNickname());
        super.setPassword(user.getPassword());
        super.setProjects(user.getProjects());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
