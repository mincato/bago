package ar.com.bago.persistence.sql;

import org.apache.ibatis.jdbc.SQL;

public class CustomSQL extends SQL {

    private static final String PAGINATION = " LIMIT #{pageRequest.limit} OFFSET #{pageRequest.offset}";

    private String pagination = null;

    public SQL PAGINATION() {
        this.pagination = PAGINATION;
        return getSelf();
    }

    @Override
    public CustomSQL getSelf() {
        return this;
    }

    @Override
    public String toString() {
        if (pagination != null) {
            return super.toString() + pagination;
        }
        return super.toString();
    }

}
