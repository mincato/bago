package ar.com.bago.rest.security;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import ar.com.bago.common.exception.ForbiddenException;
import ar.com.bago.rest.response.RestResponseHandler;

@Service
public class SecurityExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    @Autowired
    private RestResponseHandler responseHandler;

    @Override
    public Response toResponse(AccessDeniedException exception) {
        return responseHandler.buildErrorResponse(new ForbiddenException(exception));
    }

}
