package com.gd.comment.client;

import com.gd.core.result.RestResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/10/8
 */
@Slf4j
public class CommentClientResolver implements CommentClient{
    /**
     * 获取评论列表
     *
     * @param blogId   博客id
     * @param page     第几页
     * @param pageSize 页大小
     * @return 评论列表
     */
    @Override
    public RestResult<Object> getCommentList(Integer blogId, Integer page, Integer pageSize) {
        log.error("Comment 服务异常：获取评论列表 getCommentList 请求失败");
        return RestResult.fail("request fail");
    }
}
