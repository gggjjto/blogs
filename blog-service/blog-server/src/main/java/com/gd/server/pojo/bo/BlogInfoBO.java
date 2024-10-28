package com.gd.server.pojo.bo;

import com.gd.server.pojo.domain.BlogView;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BlogInfoBO extends BlogView {

	/**
	 * 作者昵称
	 */
	String authorName;

}
