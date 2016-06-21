package ar.com.bago.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.DateOnlyTypeHandler;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.DeveloperListView;
import ar.com.bago.model.developer.Seniority;
import ar.com.bago.pagination.PageRequest;
import ar.com.bago.persistence.sql.DeveloperSqlBuilder;

public interface DeveloperRepository {

    @SelectProvider(type = DeveloperSqlBuilder.class, method = "buildFind")
    @Results(id = "developerListViewResult", value = { @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"), @Result(property = "lastName", column = "LAST_NAME") })
    List<DeveloperListView> find(@Param("name") String name, @Param("lastName") String lastName,
            @Param("seniority") Seniority seniority, @Param("pageRequest") PageRequest pageRequest);

    @SelectProvider(type = DeveloperSqlBuilder.class, method = "buildCount")
    Long count(@Param("name") String name, @Param("lastName") String lastName, @Param("seniority") Seniority seniority);

    @Select("SELECT * FROM DEVELOPERS WHERE ID = #{developerId}")
    @Results(id = "developerResult", value = { @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"), @Result(property = "lastName", column = "LAST_NAME"),
            @Result(property = "seniority", column = "SENIORITY", typeHandler = EnumOrdinalTypeHandler.class),
            @Result(property = "dateEntry", column = "DATE_ENTRY", typeHandler = DateOnlyTypeHandler.class) })
    Developer findById(@Param("developerId") Integer id);

    @Insert("INSERT INTO DEVELOPERS (NAME, LAST_NAME, SENIORITY, DATE_ENTRY) VALUES (#{name}, #{lastName},"
            + "#{seniority, typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler}, #{dateEntry})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Developer newDeveloper);

    @Delete("DELETE FROM DEVELOPERS WHERE ID = #{developerId}")
    void delete(@Param("developerId") Integer id);

    @Update("UPDATE DEVELOPERS SET NAME = #{name}, LAST_NAME = #{lastName}, "
            + "SENIORITY = #{seniority, typeHandler = org.apache.ibatis.type.EnumOrdinalTypeHandler}, "
            + "DATE_ENTRY = #{dateEntry} " + "WHERE ID = #{id}")
    void update(Developer developer);

}
