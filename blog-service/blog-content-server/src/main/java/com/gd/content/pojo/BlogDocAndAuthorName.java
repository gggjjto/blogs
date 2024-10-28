package com.gd.content.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BlogDocAndAuthorName extends BlogDoc {

	/**
	 * 作者昵称
	 */
	String authorName;

}
