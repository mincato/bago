package ar.com.bago.rest.security;

import javax.servlet.http.HttpServletRequest;

public class HeaderTokenResolver implements TokenResolver {

    @Override
    public String getToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

}
