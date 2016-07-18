package ar.com.bago.persistence;

import java.util.ArrayList;
import java.util.List;

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

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import ar.com.bago.model.developer.Stats;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/embedded-db.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class StatsRepositoryTest {

    @Autowired
    private StatsRepository statsRepository;

    @Before
    public void setUp() {
    }

    @Test
    @DatabaseSetup(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.INSERT)
    @DatabaseTearDown(value = "/dbtest/developer/developers.xml", type = DatabaseOperation.DELETE_ALL)
    public void findAndFilterByNameParameter() {

        List<Stats> expectedStats = buildExpectedStats();
        List<Stats> stats = statsRepository.getStats();
        Assert.assertEquals(2, stats.size());
        for (Stats expectedStat : expectedStats) {
            Assert.assertTrue(stats.contains(expectedStat));
        }
    }

    private List<Stats> buildExpectedStats() {
        List<Stats> statsList = new ArrayList<>();
        Stats stats = new Stats();
        stats.setSeniorityValue(0);
        stats.setDevelopers(1);
        statsList.add(stats);
        stats = new Stats();
        stats.setSeniorityValue(1);
        stats.setDevelopers(1);
        statsList.add(stats);
        return statsList;
    }

}
