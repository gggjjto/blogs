package com.gd.user.listener;


import com.gd.comment.sdk.CommentDTO;
import com.gd.user.mapper.UserGeneralMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.gd.comment.sdk.MqConstants.BLOG_COMMENT_DECREASE_KEY;
import static com.gd.comment.sdk.MqConstants.BLOG_COMMENT_INCREASE_KEY;
import static com.gd.sdk.BlogMqConstants.BLOG_TOPIC_EXCHANGE;


/**
 * @author durance
 * @version 1.0
 * @date 2022/10/5 14:20
 */
@Slf4j
@Component
public class CommentListener {

	public static final String USER_COMMENT_QUEUE = "user.operate.comment";

	public static final String USER_COMMENT_QUEUE_CANCEL = "user.operate.comment.cancel";

	@Resource
	private UserGeneralMapper userGeneralMapper;

	/**
	 * 用户评论博客
	 *
	 * @param commentDTO 评论操作操作消息
	 */
	@RabbitListener(bindings = @QueueBinding(
			exchange = @Exchange(name = BLOG_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
			value = @Queue(name = USER_COMMENT_QUEUE),
			key = BLOG_COMMENT_INCREASE_KEY
	))
	public void commentAddUserGeneral(CommentDTO commentDTO) {
		log.debug("用户 {} 评论加1", commentDTO.getAuthorId());
		userGeneralMapper.updateCommentNumByUserId(commentDTO.getAuthorId(), 1);

	}

	/**
	 * 用户删除评论
	 *
	 * @param commentDTO 评论操作操作消息
	 */
	@RabbitListener(bindings = @QueueBinding(
			exchange = @Exchange(name = BLOG_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
			value = @Queue(name = USER_COMMENT_QUEUE_CANCEL),
			key = BLOG_COMMENT_DECREASE_KEY
	))
	public void commentreduceUserGeneral(CommentDTO commentDTO) {
		log.debug("用户 {} 评论 -1", commentDTO.getAuthorId());
		userGeneralMapper.updateCommentNumByUserId(commentDTO.getAuthorId(), -1);

	}

}
