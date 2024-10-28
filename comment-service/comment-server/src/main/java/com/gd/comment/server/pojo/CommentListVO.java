package com.gd.comment.server.pojo;

import com.gd.core.result.ListVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class CommentListVO extends ListVO<CommentVO> {

	/**
	 * 总评论数量（包括二级评论）
	 */
	@SuppressWarnings("AlibabaPojoNoDefaultValue")
	Long allCount = 0L;

}
