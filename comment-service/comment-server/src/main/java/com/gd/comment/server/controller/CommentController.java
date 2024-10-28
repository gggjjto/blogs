package com.gd.comment.server.controller;


import com.gd.comment.server.pojo.Comment;
import com.gd.comment.server.pojo.CommentListVO;
import com.gd.comment.server.service.CommentService;
import com.gd.core.exception.BusinessException;
import com.gd.web.auth.AuthHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 评论相关接口
 *
 */
@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private CommentService commentService;

	/**
	 * 新增评论
	 *
	 * @param comment 必须传入被评论的博客id和评论内容
	 */
	@PostMapping
	public void createComment(@RequestBody Comment comment) {
		Integer id = AuthHelper.getCurrentUserIdOrExit();
		if (comment.getBlogId() == null || comment.getContent() == null) {
			throw new BusinessException();
		}
		comment.setUserId(id);
		commentService.create(comment);
	}

	/**
	 * 删除评论
	 *
	 * @param id 评论id
	 */
	@DeleteMapping
	public void deleteComment(@RequestParam Integer id) {
		Integer userId = AuthHelper.getCurrentUserIdOrExit();
		commentService.checkAndDelete(userId, id);
	}

	/**
	 * 获取评论列表
	 *
	 * @param blogId 评论的博客id
	 */
	@GetMapping("/list")
	public CommentListVO getCommentList(@RequestParam Integer blogId, @RequestParam Integer page, @RequestParam(defaultValue = "3") Integer pageSize) {
		return commentService.getList(blogId, page, pageSize);
	}

}
