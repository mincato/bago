package ar.com.bago.persistence.sql;

import org.apache.ibatis.jdbc.SQL;

public class OracleCustomSQL extends SQL {

    private static final String START_PAGINATION = " SELECT * FROM ( SELECT e.*, ROWNUM RN FROM ( ";

    private static final String END_PAGINATION = " ) e WHERE ROWNUM <= #{pageRequest.offset} + #{pageRequest.limit} ) WHERE RN > #{pageRequest.offset}";

    private String startPagination = null;

    private String endPagination = null;

    public SQL PAGINATION() {
        this.startPagination = START_PAGINATION;
        this.endPagination = END_PAGINATION;
        return getSelf();
    }

    @Override
    public OracleCustomSQL getSelf() {
        return this;
    }

    @Override
    public String toString() {
        if (startPagination != null) {
            return startPagination + super.toString() + endPagination;
        }
        return super.toString();
    }

}
