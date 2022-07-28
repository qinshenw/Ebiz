package edu.support.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author wqs
 */
@Setter
@Getter
public class BaseDomain implements Serializable {

    @TableId(type = AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    @TableField(update = "now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateTime;
}
