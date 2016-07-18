package ar.com.bago.persistence;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import ar.com.bago.model.developer.Stats;

@Repository
public class StatsRepository extends AbstractDbUtilsRepository<Stats> {

    private static final String GET_DEVELOPERS_GROUP_BY_SENIORITY = "SELECT SENIORITY as seniorityValue, count(id) as developers FROM DEVELOPERS GROUP BY SENIORITY";

    @Autowired
    private DataSource dataSource;

    public StatsRepository() {
        super(Stats.class);
    }

    public List<Stats> getStats() {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        String query = GET_DEVELOPERS_GROUP_BY_SENIORITY;
        return executeQueryMultipleRows(connection, query, null);
    }

}
