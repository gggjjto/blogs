package com.gd.user.mapper;

import com.gd.user.pojo.UserSafety;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-26
 */
@Mapper
public interface UserSafetyMapper extends BaseMapper<UserSafety> {

    @Update("update user.user_safety set password = #{password} where user_id = #{id};")
    int updatePasswordById(Integer id, String password);

    @Select("select * from user.user_safety where mail = #{mail}")
     UserSafety selectByMail(String mail);
}
