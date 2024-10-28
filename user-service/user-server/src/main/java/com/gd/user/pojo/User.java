package com.gd.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-26
 */
@Data
@NoArgsConstructor // 自动生成一个无参构造函数
@AllArgsConstructor // 自动生成一个包含所有字段的构造函数
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 院校代码
     */
    private Integer schoolCode;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像链接
     */
    private String avatarUrl;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 0为未删除，已删除时该值等于id，以避免唯一索引的异常，1已删除
     */
    private Integer deleted;


}
