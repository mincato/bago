package ar.com.bago.persistence.sql;

import ar.com.bago.common.pagination.PageRequest;
import ar.com.bago.model.developer.Seniority;

public class DeveloperSqlBuilder {

    public String buildFind(final String name, final String lastName, final Seniority seniority, PageRequest pageRequest) {
        return new OracleCustomSQL() {

            {
                SELECT("*");
                FROM("DEVELOPERS");
                if (name != null) {
                    WHERE("UPPER(NAME) LIKE #{name}");
                }
                if (lastName != null) {
                    WHERE("UPPER(LAST_NAME) LIKE #{lastName}");
                }
                if (seniority != null) {
                    WHERE("SENIORITY = #{seniority, typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler}");
                }
                ORDER_BY("ID");
                if (pageRequest != null) {
                    PAGINATION();
                }

            }
        }.toString();

    }

    public String buildCount(final String name, final String lastName, final Seniority seniority) {
    	
        return new OracleCustomSQL() {

            {
                SELECT("COUNT(*)");
                FROM("DEVELOPERS");
                if (name != null) {
                    WHERE("UPPER(NAME) LIKE #{name}");
                }
                if (lastName != null) {
                    WHERE("UPPER(LAST_NAME) LIKE #{lastName}");
                }
                if (seniority != null) {
                    WHERE("SENIORITY = #{seniority, typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler}");
                }

            }
        }.toString();

    }
}
