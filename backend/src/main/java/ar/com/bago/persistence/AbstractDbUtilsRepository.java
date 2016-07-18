package ar.com.bago.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ar.com.bago.common.exception.BackEndException;

public abstract class AbstractDbUtilsRepository<T> {

    private Class<T> clazz;

    public AbstractDbUtilsRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected T executeQueryOneRow(Connection conn, String query, Object[] params) {
        try {
            QueryRunner runner = new QueryRunner();
            T result;
            if (params == null) {
                result = runner.query(conn, query, new BeanHandler<>(clazz));
            } else {
                result = runner.query(conn, query, new BeanHandler<>(clazz), params);
            }
            return result;
        } catch (SQLException e) {
            throw new BackEndException(e);
        }
    }

    protected List<T> executeQueryMultipleRows(Connection conn, String query, Object[] params) {
        try {
            QueryRunner runner = new QueryRunner();
            List<T> result;
            if (params == null) {
                result = runner.query(conn, query, new BeanListHandler<>(clazz));
            } else {
                result = runner.query(conn, query, new BeanListHandler<>(clazz), params);
            }
            return result;
        } catch (SQLException e) {
            throw new BackEndException(e);
        }
    }

}
