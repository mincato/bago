package ar.com.bago.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ar.com.bago.model.user.Credential;
import ar.com.bago.model.user.UserLogin;
import ar.com.bago.service.UserLoginFactory;

@Service
public class SpringAuthenticationService implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginFactory userLoginFactory;

    @Override
    public UserLogin authenticate(Credential credential) {
        try {
            Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(credential.getUsername(), credential
                            .getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userLoginFactory.create(userDetails.getUser());
        } catch (AuthenticationException ex) {
            throw new ar.com.bago.exception.AuthenticationException(ex);
        }
    }

}
