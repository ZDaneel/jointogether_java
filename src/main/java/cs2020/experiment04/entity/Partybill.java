package cs2020.experiment04.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ZD
 * @since 2022-07-01
 */
@Getter
@Setter
@ToString
@ApiModel(value = "Partybill对象", description = "")
public class Partybill implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("账单名称")
    private String billName;

    @ApiModelProperty("账单费用")
    private Double billPrice;

    @ApiModelProperty("发起用户")
    private String billUsername;

    @ApiModelProperty("关联活动")
    private Integer billPartyId;

    @ApiModelProperty("是否AA")
    private Integer isAa;

}
