package com.gd.user.mapper;

import com.gd.user.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface UserMapper extends BaseMapper<User> {
    @Update("update user.user_safety set mail = #{mail} where user_id = #{id}")
    int updateMailById(Integer id, String mail);
}
