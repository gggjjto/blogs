package com.gd.comment.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.comment.server.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
