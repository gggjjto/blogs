package com.gd.server.pojo.domain;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class CollectBlog {

	/**
	 * id
	 */
	Integer id;

	/**
	 * 博客id
	 */
	Integer blogId;

	/**
	 * 用户id
	 */
	Integer userId;

	/**
	 * 创建时间
	 */
	Timestamp createTime;

	/**
	 * 是否已经删除，0未删除，1已删除
	 */
	Integer deleted;

}
