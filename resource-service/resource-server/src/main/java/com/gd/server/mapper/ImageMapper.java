package com.gd.server.mapper;

import com.gd.server.pojo.Image;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-29
 */
public interface ImageMapper extends BaseMapper<Image> {

    @Update("update resource.image set visit = visit + 1 where url = #{url}")
    void increaseVisit(String url);
}
