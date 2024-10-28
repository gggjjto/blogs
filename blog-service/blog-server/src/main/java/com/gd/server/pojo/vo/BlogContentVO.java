package com.gd.server.pojo.vo;

import com.gd.user.dto.UserDTO;
import com.gd.server.pojo.bo.BlogStatusBO;
import com.gd.server.pojo.domain.BlogContentHtml;
import lombok.Data;


@Data
public class BlogContentVO {

	/**
	 * 博客基本信息,带用户状态
	 */
	BlogStatusBO info;

	/**
	 * 博客内容信息
	 */
	BlogContentHtml content;

	/**
	 * 作者信息
	 */
	UserDTO author;

}
