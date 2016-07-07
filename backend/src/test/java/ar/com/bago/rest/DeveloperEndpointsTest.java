package ar.com.bago.rest;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.junit.Assert;
import org.junit.Test;

import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.Seniority;
import ar.com.bago.rest.DeveloperRestService;

public class DeveloperEndpointsTest {

    @Test
    public void testFind() throws Exception {

        String url = DeveloperRestService.class.getAnnotation(Path.class).value();

        Method method = DeveloperRestService.class.getMethod("find", HttpServletRequest.class, String.class,
                String.class, Seniority.class, Integer.class, Integer.class);

        Assert.assertNull(method.getAnnotation(Path.class));

        POST postMethod = method.getAnnotation(POST.class);
        GET getMethod = method.getAnnotation(GET.class);

        Assert.assertNull(postMethod);
        Assert.assertNotNull(getMethod);
        Assert.assertEquals("/developers", url);
    }

    @Test
    public void findOne() throws Exception {

        String url = DeveloperRestService.class.getAnnotation(Path.class).value();

        Method method = DeveloperRestService.class.getMethod("find", HttpServletRequest.class, Integer.class);

        url += "/" + method.getAnnotation(Path.class).value();

        POST postMethod = method.getAnnotation(POST.class);
        GET getMethod = method.getAnnotation(GET.class);

        Assert.assertNull(postMethod);
        Assert.assertNotNull(getMethod);
        Assert.assertEquals("/developers/{id}", url);
    }

    @Test
    public void save() throws Exception {

        String url = DeveloperRestService.class.getAnnotation(Path.class).value();

        Method method = DeveloperRestService.class.getMethod("save", HttpServletRequest.class, Developer.class);

        Assert.assertNull(method.getAnnotation(Path.class));

        POST postMethod = method.getAnnotation(POST.class);
        GET getMethod = method.getAnnotation(GET.class);

        Assert.assertNotNull(postMethod);
        Assert.assertNull(getMethod);
        Assert.assertEquals("/developers", url);
    }

    @Test
    public void update() throws Exception {

        String url = DeveloperRestService.class.getAnnotation(Path.class).value();

        Method method = DeveloperRestService.class.getMethod("update", HttpServletRequest.class, Integer.class,
                Developer.class);

        url += "/" + method.getAnnotation(Path.class).value();

        POST postMethod = method.getAnnotation(POST.class);
        GET getMethod = method.getAnnotation(GET.class);
        PUT putMethod = method.getAnnotation(PUT.class);

        Assert.assertNull(postMethod);
        Assert.assertNull(getMethod);
        Assert.assertNotNull(putMethod);
        Assert.assertEquals("/developers/{id}", url);
    }

    @Test
    public void delete() throws Exception {

        String url = DeveloperRestService.class.getAnnotation(Path.class).value();

        Method method = DeveloperRestService.class.getMethod("delete", HttpServletRequest.class, Integer.class);

        url += "/" + method.getAnnotation(Path.class).value();

        POST postMethod = method.getAnnotation(POST.class);
        GET getMethod = method.getAnnotation(GET.class);
        DELETE deleteMethod = method.getAnnotation(DELETE.class);

        Assert.assertNull(postMethod);
        Assert.assertNull(getMethod);
        Assert.assertNotNull(deleteMethod);
        Assert.assertEquals("/developers/{id}", url);

    }
}
