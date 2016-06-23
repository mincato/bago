package ar.com.bago.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitils.reflectionassert.ReflectionAssert;

import ar.com.bago.common.exception.NotFoundException;
import ar.com.bago.common.pagination.PageRequest;
import ar.com.bago.common.pagination.PageResponse;
import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.DeveloperBuilder;
import ar.com.bago.model.developer.DeveloperListView;
import ar.com.bago.model.developer.Seniority;
import ar.com.bago.persistence.DeveloperRepository;
import ar.com.bago.service.DeveloperService;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    private DeveloperService developerService;

    private Developer developerKyloRen;

    private DeveloperListView developerKyloRenListView;
    private DeveloperListView developerDarthVaderListView;
    private DeveloperListView developerDarthMaulListView;

    @Before
    public void init() {
        developerService = new DeveloperService();
        developerService.setRepository(developerRepository);
        developerKyloRen = DeveloperBuilder.createKyloRen();
        developerKyloRenListView = DeveloperBuilder.createKyloRenListView();
        developerDarthVaderListView = DeveloperBuilder.createDarthVaderListView();
        developerDarthMaulListView = DeveloperBuilder.createDarthMaulListView();
    }

    @Test
    public void save() {
        developerService.save(developerKyloRen);
        verify(developerRepository, times(1)).save(developerKyloRen);
    }

    @Test
    public void findById() {
        Integer developerId = 1;
        when(developerRepository.findById(developerId)).thenReturn(developerKyloRen);

        Developer result = developerService.find(developerId);

        ReflectionAssert.assertReflectionEquals(developerKyloRen, result);
    }

    @Test(expected = NotFoundException.class)
    public void givenNonExistentDeveloperWhenCallFindIdThenThrowsNotFound() {
        Integer developerId = 25;
        when(developerRepository.findById(developerId)).thenReturn(null);
        developerService.find(developerId);
    }

    @Test
    public void delete() {
        Integer developerId = 1;
        developerService.delete(developerId);
        verify(developerRepository, times(1)).delete(developerId);
    }

    @Test
    public void update() {
        developerService.update(developerKyloRen);
        verify(developerRepository, times(1)).update(developerKyloRen);
    }

    @Test
    public void find() {
        List<DeveloperListView> expectedDevelopers = Arrays.asList(developerKyloRenListView,
                developerDarthMaulListView, developerDarthVaderListView);

        PageResponse<DeveloperListView> expectedPageDevelopers = new PageResponse<DeveloperListView>(
                expectedDevelopers, 0, 0, 0l);
        when(
                developerRepository.find(Mockito.anyString(), Mockito.anyString(), Mockito.any(Seniority.class),
                        Mockito.any(PageRequest.class))).thenReturn(expectedDevelopers);

        PageResponse<DeveloperListView> results = developerService.find(null, null, null, 0, 0);

        ReflectionAssert.assertReflectionEquals(expectedPageDevelopers, results);
    }
}
