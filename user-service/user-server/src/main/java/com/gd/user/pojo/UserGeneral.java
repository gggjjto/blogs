package com.gd.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_general")
public class UserGeneral implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户获赞
     */
    private Integer likeNum;

    /**
     * 用户粉丝数
     */
    private Integer fansNum;

    /**
     * 用户评论数
     */
    private Integer commentNum;

    /**
     * 收藏博客数量
     */
    private Integer collectNum;

    /**
     * 访问数
     */
    private Integer viewNum;

    /**
     * 用户博客数量
     */
    private Integer blogNum;

    /**
     * 周排行
     */
    private Integer weekRank;

    /**
     * 总排行
     */
    private Integer totalRank;

    /**
     * 0为未删除，1为已删除
     */
    private Integer deleted;


}
