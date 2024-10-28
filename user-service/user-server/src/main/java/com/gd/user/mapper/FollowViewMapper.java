package com.gd.user.mapper;

import com.gd.user.pojo.FollowView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-27
 */
@Mapper
public interface FollowViewMapper extends BaseMapper<FollowView> {

    @Select("select follow_id from user.follow_view where user_id = #{userId} and status = 1;")
    List<Integer> selectFollowIdByUserId(int userId);
}
