package ar.com.bago.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ar.com.bago.model.user.Role;
import ar.com.bago.rest.response.RestResponseHandler;
import ar.com.bago.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Service
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "users", authorizations = { @Authorization(value = "bearer") })
public class UserRestService {

    @Autowired
    private RestResponseHandler responseHandler;

    @Autowired
    private UserService userService;

    @POST
    @PreAuthorize("hasAnyAuthority('CREATE_ROLE')")
    @Path("/roles")
    @ApiOperation(value = "save", notes = "Crea un nuevo rol.", response = Role.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error interno del sistema => Ver code y message lanzados"),
            @ApiResponse(code = 401, message = "Usuario no autenticado"),
            @ApiResponse(code = 403, message = "Usuario no autorizado") })
    public Response save(@Context HttpServletRequest request, @Valid Role role) {
        Role roleSaved = userService.saveRole(role);
        return responseHandler.buildSuccessResponse(roleSaved, Status.OK);
    }

    public void setResponseHandler(RestResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
