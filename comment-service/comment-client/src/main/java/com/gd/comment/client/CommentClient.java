package com.gd.comment.client;

import com.gd.core.result.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/10/8
 */
@Import(CommentClientResolver.class)
@FeignClient(value = "comment-server", fallback = CommentClientResolver.class)
public interface CommentClient {
    /**
     * 获取评论列表<br>
     * todo 这里的类是用的object
     *
     * @param blogId   博客id
     * @param page     第几页
     * @param pageSize 页大小
     * @return 评论列表
     */
    @GetMapping("/comment/list")
    RestResult<Object> getCommentList(@RequestParam Integer blogId, @RequestParam Integer page, @RequestParam(defaultValue = "3") Integer pageSize);

}
