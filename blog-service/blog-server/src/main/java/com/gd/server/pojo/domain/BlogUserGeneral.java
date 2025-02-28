package com.gd.server.pojo.domain;

import lombok.Data;


@Data
public class BlogUserGeneral {

	/**
	 * 用户id
	 */
	Integer userId;

	/**
	 * 浏览量
	 */
	Integer viewNum;

	/**
	 * 点赞量
	 */
	Integer likeNum;

	/**
	 * 评论量
	 */
	Integer commentNum;

	/**
	 * 收藏量
	 */
	Integer collectionNum;

}
