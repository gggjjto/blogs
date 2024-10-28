package com.gd.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.server.pojo.domain.BlogUserGeneral;
import com.gd.server.pojo.domain.BlogView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogViewMapper extends BaseMapper<BlogView> {

	/**
	 * 查询用户发送的博客各项数据的统计
	 *
	 * @param userIds 用户id集合
	 * @return 统计数据
	 */
	List<BlogUserGeneral> selectBlogViewsByUserIds(Integer[] userIds);

}
