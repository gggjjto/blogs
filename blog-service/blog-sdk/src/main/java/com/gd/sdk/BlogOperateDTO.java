package com.gd.sdk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogOperateDTO {

	/**
	 * 被操作的博客id
	 */
	Integer blogId;

	/**
	 * 执行的用户id，可能为空
	 */
	Integer userId;

	/**
	 * 博客作者id
	 */
	Integer authorId;

}
