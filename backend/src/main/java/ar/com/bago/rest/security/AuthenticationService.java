package ar.com.bago.rest.security;

import ar.com.bago.model.user.Credential;
import ar.com.bago.model.user.UserLogin;

public interface AuthenticationService {

    UserLogin authenticate(Credential credential);

}
