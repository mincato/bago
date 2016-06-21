package ar.com.bago.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.unitils.reflectionassert.ReflectionAssert;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.DeveloperBuilder;
import ar.com.bago.model.developer.DeveloperListView;
import ar.com.bago.model.developer.Seniority;
import ar.com.bago.persistence.DeveloperRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/embedded-db.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class DeveloperRepositoryTest {

    @Autowired
    private DeveloperRepository developerRepository;

    private Developer developerDarthVader;

    private DeveloperListView developerKyloRenListView;
    private DeveloperListView developerDarthMaulListView;

    @Before
    public void setUp() {
        developerDarthVader = DeveloperBuilder.createDarthVader();
        developerKyloRenListView = DeveloperBuilder.createKyloRenListView();
        developerDarthMaulListView = DeveloperBuilder.createDarthMaulListView();
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @ExpectedDatabase(value = "/dbtest/developer/save-developer-expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void save() {
        developerRepository.save(developerDarthVader);
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void findNullParameters() {

        List<DeveloperListView> expectedDevelopers = buildExpectedDevelopers();

        List<DeveloperListView> developers = developerRepository.find(null, null, null, null);

        Assert.assertNotNull(developers);
        ReflectionAssert.assertReflectionEquals(expectedDevelopers, developers);
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void findAndFilterByNameParameter() {

        List<DeveloperListView> expectedDevelopers = buildExpectedDevelopers(developerKyloRenListView);
        List<DeveloperListView> developers = developerRepository.find("KYLO", null, null, null);

        Assert.assertNotNull(developers);
        ReflectionAssert.assertReflectionEquals(expectedDevelopers, developers);
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void findAndFilterBySeniority() {

        List<DeveloperListView> expectedDevelopers = buildExpectedDevelopers(developerKyloRenListView);
        List<DeveloperListView> developers = developerRepository.find(null, null, Seniority.JUNIOR, null);

        Assert.assertNotNull(developers);
        ReflectionAssert.assertReflectionEquals(expectedDevelopers, developers);
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void findAndFilterByLastNameParameter() {

        List<DeveloperListView> expectedDevelopers = buildExpectedDevelopers(developerKyloRenListView);
        List<DeveloperListView> developers = developerRepository.find(null, "REN", null, null);

        Assert.assertNotNull(developers);
        ReflectionAssert.assertReflectionEquals(expectedDevelopers, developers);
    }

    private List<DeveloperListView> buildExpectedDevelopers() {
        List<DeveloperListView> expectedDevelopers = Arrays
                .asList(developerKyloRenListView, developerDarthMaulListView);
        IntStream.range(0, expectedDevelopers.size()).forEach(i -> expectedDevelopers.get(i).setId(i + 1));
        return expectedDevelopers;
    }

    private List<DeveloperListView> buildExpectedDevelopers(DeveloperListView developer) {
        List<DeveloperListView> expectedDevelopers = Arrays.asList(developer);
        IntStream.range(0, expectedDevelopers.size()).forEach(i -> expectedDevelopers.get(i).setId(i + 1));
        return expectedDevelopers;
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @ExpectedDatabase(value = "/dbtest/developer/update-developer-expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void update() {
        Developer kylo = developerRepository.findById(1);
        Assert.assertEquals(Seniority.JUNIOR, kylo.getSeniority());

        kylo.setSeniority(Seniority.SEMI_SENIOR);
        developerRepository.update(kylo);
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @ExpectedDatabase(value = "/dbtest/developer/delete-developer-expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void delete() {
        developerRepository.delete(1);
    }
}
