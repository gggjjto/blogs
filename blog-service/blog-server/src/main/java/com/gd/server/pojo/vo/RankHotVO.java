package com.gd.server.pojo.vo;


import com.gd.user.dto.UserDTO;
import com.gd.server.pojo.domain.Blog;
import lombok.Data;


@Data
public class RankHotVO {

	/**
	 * 博客信息
	 */
	Blog blog;

	/**
	 * 用户信息
	 */
	UserDTO author;

	/**
	 * 排行榜热度
	 */
	Double hot;

}
