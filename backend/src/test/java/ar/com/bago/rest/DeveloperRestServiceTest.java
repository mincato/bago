package ar.com.bago.rest;

import java.util.Arrays;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.bago.common.exception.NotFoundException;
import ar.com.bago.common.pagination.PageResponse;
import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.DeveloperBuilder;
import ar.com.bago.model.developer.DeveloperListView;
import ar.com.bago.model.developer.Seniority;
import ar.com.bago.rest.DeveloperRestService;
import ar.com.bago.rest.response.RestResponseHandler;
import ar.com.bago.service.DeveloperService;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperRestServiceTest {

    private DeveloperRestService service;

    @Mock
    private DeveloperService developerService;

    private static final Integer VALID_DEVELOPER_ID = 1;

    @Before
    public void init() {
        service = new DeveloperRestService();
        service.setResponseHandler(new RestResponseHandler());
        service.setDeveloperService(developerService);
    }

    @Test
    public void find() {
        PageResponse<DeveloperListView> developers = new PageResponse<DeveloperListView>(Arrays.asList(
                new DeveloperListView(), new DeveloperListView(), new DeveloperListView()), 0, 0, 0l);
        Mockito.when(
                developerService.find(Mockito.anyString(), Mockito.anyString(), Mockito.any(Seniority.class),
                        Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(developers);

        Response response = service.find(null, null, null, null, null, null);

        Mockito.verify(developerService, Mockito.times(1)).find(null, null, null, null, null);
        Assert.assertEquals(developers, response.getEntity());
    }

    @Test
    public void findOne() {
        Developer developer = new Developer();
        Mockito.when(developerService.find(VALID_DEVELOPER_ID)).thenReturn(developer);

        Response response = service.find(null, VALID_DEVELOPER_ID);

        Mockito.verify(developerService, Mockito.times(1)).find(VALID_DEVELOPER_ID);
        Assert.assertEquals(developer, response.getEntity());
    }

    @SuppressWarnings("unchecked")
    @Test
    @Ignore
    public void findOneNotFound() {
        Integer developerIdNotExist = 15;
        Mockito.when(developerService.find(developerIdNotExist)).thenThrow(NotFoundException.class);

        Response response = service.find(null, developerIdNotExist);

        Assert.assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void save() {
        Developer developer = DeveloperBuilder.createDarthVader();

        Mockito.when(developerService.save(developer)).thenReturn(developer);

        Response response = service.save(null, developer);

        Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals(developer, response.getEntity());
    }
}
