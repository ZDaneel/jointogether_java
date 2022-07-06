package cs2020.experiment04.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * 
 *
 * @author PM
 * @since 2022-07-05
 */
@Getter
@Setter
  @ApiModel(value = "Partymessage对象", description = "")
public class Partymessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer messageUserId;

    @ApiModelProperty("活动id")
    private Integer messagePartyId;

    @ApiModelProperty("消息内容")
    private String message;

    @TableField(exist = false)
    @ApiModelProperty("消息活动名称")
    private String messagePartyName;

}
