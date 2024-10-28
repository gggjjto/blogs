package com.gd.server.pojo.vo;

import com.gd.server.pojo.bo.BlogCountBO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BlogListConsoleVO extends BlogListVO {

	/**
	 * 各状态博客数量
	 */
	BlogCountBO count;

}
