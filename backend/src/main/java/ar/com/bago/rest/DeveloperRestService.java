package ar.com.bago.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ar.com.bago.common.pagination.PageResponse;
import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.DeveloperListView;
import ar.com.bago.model.developer.Seniority;
import ar.com.bago.rest.response.RestResponseHandler;
import ar.com.bago.service.DeveloperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Service
@Path("/developers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "developers", authorizations = { @Authorization(value = "bearer") })
public class DeveloperRestService {

    @Autowired
    private RestResponseHandler responseHandler;

    @Autowired
    private DeveloperService developerService;

    @GET
    @PreAuthorize("hasAnyAuthority('READ_DEVELOPER')")
    @ApiOperation(value = "findAll", notes = "Busca todos developers.", response = Developer.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error interno del sistema => Ver code y message lanzados"),
            @ApiResponse(code = 401, message = "Usuario no autenticado"),
            @ApiResponse(code = 403, message = "Usuario no autorizado") })
    public Response find(@Context HttpServletRequest request, @QueryParam("name") String name,
            @QueryParam("lastName") String lastName, @QueryParam("seniority") Seniority seniority,
            @QueryParam("pageNumber") Integer pageNumber, @QueryParam("pageSize") Integer pageSize) {
        PageResponse<DeveloperListView> page = developerService.find(name, lastName, seniority, pageNumber, pageSize);
        return responseHandler.buildSuccessResponse(page, Status.OK);
    }

    @GET
    @Path("{id}")
    @PreAuthorize("hasAnyAuthority('READ_DEVELOPER')")
    @ApiOperation(value = "find", notes = "Busca un developer por ID.", response = Developer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error interno del sistema => Ver code y message lanzados"),
            @ApiResponse(code = 401, message = "Usuario no autenticado"),
            @ApiResponse(code = 403, message = "Usuario no autorizado"),
            @ApiResponse(code = 404, message = "Developer no encontrado") })
    public Response find(@Context HttpServletRequest request, @PathParam("id") Integer id) {
        Developer developer = developerService.find(id);
        return responseHandler.buildSuccessResponse(developer, Status.OK);
    }

    @GET
    @Path("seniorities")
    public Response getSeniorities(@Context HttpServletRequest request) {
        Seniority[] seniorities = developerService.getSeniorities();
        return responseHandler.buildSuccessResponse(seniorities, Status.OK);
    }

    @POST
    @PreAuthorize("hasAnyAuthority('CREATE_DEVELOPER')")
    @ApiOperation(value = "save", notes = "Crea un nuevo developer.", response = Developer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error interno del sistema => Ver code y message lanzados"),
            @ApiResponse(code = 401, message = "Usuario no autenticado"),
            @ApiResponse(code = 403, message = "Usuario no autorizado") })
    public Response save(@Context HttpServletRequest request, @Valid Developer newDeveloper) {
        Developer developerSaved = developerService.save(newDeveloper);
        return responseHandler.buildSuccessResponse(developerSaved, Status.OK);
    }

    @PUT
    @Path("{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_DEVELOPER')")
    @ApiOperation(value = "update", notes = "Actualiza un developer existente.", response = Developer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error interno del sistema => Ver code y message lanzados"),
            @ApiResponse(code = 401, message = "Usuario no autenticado"),
            @ApiResponse(code = 403, message = "Usuario no autorizado"),
            @ApiResponse(code = 404, message = "Developer no encontrado") })
    public Response update(@Context HttpServletRequest request, Developer developer) {
        Developer developerUpdated = developerService.update(developer);
        return responseHandler.buildSuccessResponse(developerUpdated, Status.OK);
    }

    @DELETE
    @Path("{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_DEVELOPER')")
    @ApiOperation(value = "delete", notes = "Elimina un developer existente.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error interno del sistema => Ver code y message lanzados"),
            @ApiResponse(code = 401, message = "Usuario no autenticado"),
            @ApiResponse(code = 403, message = "Usuario no autorizado"),
            @ApiResponse(code = 404, message = "Developer no encontrado") })
    public Response delete(@Context HttpServletRequest request, @PathParam("id") Integer id) {
        developerService.delete(id);
        return responseHandler.buildSuccessResponse(Status.OK);
    }
    
    @GET
    @Path("throw/{param}")
    @PreAuthorize("hasAnyAuthority('READ_DEVELOPER')")
    @ApiOperation(value = "throwServiceException", notes = "Lanza una excepcion de servicio parametrizada.")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Error interno del sistema => Ver code y message lanzados"),
            @ApiResponse(code = 401, message = "Usuario no autenticado"),
            @ApiResponse(code = 403, message = "Usuario no autorizado") })
    public Response throwServiceException(@Context HttpServletRequest request, @PathParam("param") String param) {
        developerService.throwServiceException(param);
        return responseHandler.buildSuccessResponse(Status.OK);
    }    

    public void setResponseHandler(RestResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public void setDeveloperService(DeveloperService developerService) {
        this.developerService = developerService;
    }
}
