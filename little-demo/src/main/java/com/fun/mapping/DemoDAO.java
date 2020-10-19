package com.fun.mapping;

import com.fun.mapping.jdop.annotation.DataAccesor;
import com.fun.mapping.jdop.annotation.Param;
import com.fun.mapping.jdop.annotation.Query;

/**
 * @author huanye
 * Date: 2017/9/22 下午5:45
 */
@DataAccesor
public interface DemoDAO {

    @Query(sql="select * from area where id= :id")
    Area selectById(@Param("id") Long id);

    @Query(
        sql = "SELECT a.id AS id, a.name AS roleName, b.id AS permissionId, b.name AS permissionName" +
            " FROM role a LEFT JOIN permission b ON a.permission_id=b.id",
        lvl = 1,
        keys = {"id"})
    Area selectAllRolePermission();

    @Query(
        sql = "select c.code as code, c.name as name," +
            "p.code as 'province.code' ,p.name as 'province.name' " +
            "a.code as 'areas.code', a.name as 'areas.name'" +
            "from t_city c left join t_province p on c.province_code=p.code " +
            "left join t_area a on c.code=a.city_code " +
            "where c.id in ( :codes )",
        lvl=2
    )
    City selectCityesByIds(@Param("codes")String[] codes);
}
