package ar.com.bago.persistence;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import ar.com.bago.model.user.Permission;
import ar.com.bago.model.user.Role;
import ar.com.bago.model.user.User;

public interface UserRepository {

    @Select("SELECT * FROM USERS WHERE USERNAME = #{username}")
    @Results(id = "userResult", value = { @Result(property = "id", column = "ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "firstName", column = "FIRST_NAME"),
            @Result(property = "middleName", column = "MIDDLE_NAME"),
            @Result(property = "lastName", column = "LAST_NAME"), @Result(property = "email", column = "EMAIL"),
            @Result(property = "password", column = "PASSWORD") })
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM USERS WHERE ID = #{userId}")
    @ResultMap("userResult")
    User findOne(@Param("userId") Integer userId);

    @Insert("INSERT INTO USERS (USERNAME, FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL) "
            + "VALUES (#{username}, #{firstName}, #{middleName}, #{lastName}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    User save(User user);

    @Select("SELECT DISTINCT P.NAME AS NAME " + "FROM PERMISSIONS P "
            + "INNER JOIN ROLES_PERMISSIONS RP ON P.ID = RP.PERMISSION_ID "
            + "INNER JOIN USER_ROLES UR ON UR.ROLE_ID = RP.ROLE_ID " + "WHERE UR.USER_ID = #{userId}")
    Set<String> findPermissionsByUser(@Param("userId") Integer userId);

    @Insert("INSERT INTO ROLES (NAME) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveRole(Role newRole);

    @Insert("INSERT INTO ROLES_PERMISSIONS (ROLE_ID, PERMISSION_ID) VALUES (#{role.id}, #{permission.id})")
    void saveRolePermissionRelationship(@Param("role") Role role, @Param("permission") Permission permission);

}