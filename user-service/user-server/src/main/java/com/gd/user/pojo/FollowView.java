package com.gd.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("follow_view")
public class FollowView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 粉丝id
     */
    private Integer userId;

    /**
     * 关注id
     */
    private Integer followId;

    /**
     * 备注
     */
    private String note;

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 院校代码
     */
    private Integer schoolCode;

    private String avatarUrl;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;


}
