package ar.com.bago.rest.security;

import java.util.Set;

import ar.com.bago.model.user.User;

public class UserDetailsFactory {

    public static UserDetails create(User user, Set<Authority> authorities) {
        UserDetails userDetails = new UserDetails(user, authorities);
        return userDetails;
    }

}
