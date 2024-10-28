package com.gd.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangpeng
 * @since 2024-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("university")
public class University implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学校名称
     */
    private String name;

    /**
     * 学校标识码
     */
    private Integer identifier;

    /**
     * 院校代码
     */
    private Integer code;

    /**
     * 所属城市
     */
    private Integer cityId;

    /**
     * 办学层次（1本科，2专科）
     */
    private Integer rank;

    /**
     * 备注
     */
    private String remark;


}
