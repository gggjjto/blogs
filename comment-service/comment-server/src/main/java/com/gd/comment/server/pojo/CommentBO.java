package com.gd.comment.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class CommentBO extends Comment {

	/**
	 * 发表评论的用户的昵称
	 */
	String nickname;

	/**
	 * 头像链接
	 */
	String avatarUrl;

	/**
	 * 回复的用户昵称
	 */
	String parentNickname;

}
