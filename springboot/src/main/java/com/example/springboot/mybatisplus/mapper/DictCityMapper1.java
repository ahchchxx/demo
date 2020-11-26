package com.example.springboot.mybatisplus.mapper;

import com.example.springboot.common.mapper.BaseDao;
import com.example.springboot.mybatisplus.DictCity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictCityMapper1 extends BaseDao<DictCity> {

    // 模糊查询
//    List<Staff> fuzzyQuery(Page<Staff> page, @Param("staff") Staff staff);
//    <select id="fuzzyQuery" resultType="你的实体类Reference" parameterType="你的实体类Reference">
//    SELECT * FROM sz_staff
//        <trim prefix="WHERE" prefixOverrides="AND|OR">
//            <if test='staff.userName!=null and staff.userName!=" "'>
//              AND USER_NAME like CONCAT('%',#{staff.userName},'%')
//            </if>
//            <if test='staff.phone!=null and staff.phone!=" "'>
//              AND PHONE like CONCAT('%',#{staff.phone},'%')
//            </if>
//            <if test='staff.belongFirm!=null and staff.belongFirm!=" "'>
//              AND BELONG_FIRM like CONCAT('%',#{staff.belongFirm},'%')
//            </if>
//            <if test='staff.positionId!=null and staff.positionId!=" "'>
//              AND POSITION_ID=#{staff.positionId}
//            </if>
//            <if test='staff.superLeader!=null and staff.superLeader!=" "'>
//              AND SUPER_LEADER like CONCAT('%',#{staff.superLeader},'%')
//            </if>
//            <if test='staff.status!=null and staff.status!=" "'>
//              AND STATUS=#{staff.status}
//            </if>
//            <if test='staff.isSupportPeople!=null and staff.isSupportPeople!=" "'>
//              AND IS_SUPPORT_PEOPLE=#{staff.isSupportPeople}
//            </if>
//        </trim>
//    </select>

}
