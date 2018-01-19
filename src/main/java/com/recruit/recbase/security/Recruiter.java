package com.recruit.recbase.security;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@DynamoDBTable(tableName = "recruiters")
public class Recruiter implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;

    public Recruiter() {
    }

    public Recruiter(String username, String password, String[] authorities) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
    }
    @Override
    @DynamoDBAttribute(attributeName = "grantedAuthorities")
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    @Override
    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
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

