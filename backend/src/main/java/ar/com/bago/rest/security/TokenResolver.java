package ar.com.bago.rest.security;

import javax.servlet.http.HttpServletRequest;

public interface TokenResolver {

    String getToken(HttpServletRequest request);

}
