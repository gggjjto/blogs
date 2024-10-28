package com.gd.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.server.pojo.domain.CollectBlog;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface CollectBlogMapper extends BaseMapper<CollectBlog> {

	/**
	 * 获取用户对某列表博客的收藏状态
	 *
	 * @param userId     用户id
	 * @param blogIdList 博客列表
	 * @return map key和value都是用户收藏的博客id
	 */
	@MapKey("value")
	Map<Integer, Integer> selectMapByUserIdAndBlogIdList(Integer userId, Integer[] blogIdList);

}
