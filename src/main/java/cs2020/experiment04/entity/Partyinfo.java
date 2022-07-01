package cs2020.experiment04.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * 
 *
 * @author ZD
 * @since 2022-06-30
 */
@Getter
@Setter
  @ApiModel(value = "Partyinfo对象", description = "")
@ToString
public class Partyinfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("活动主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("活动名称")
      private String partyname;

      @ApiModelProperty("活动地点")
      private String place;

      @ApiModelProperty("活动日期")
      private LocalDateTime date;

      @ApiModelProperty("活动花费")
      private Double charge;

      @ApiModelProperty("活动总人数")
      private Integer number;

      @TableField(exist = false)
      @ApiModelProperty("活动现有人数")
      private Integer nownumber=0;

      @ApiModelProperty("活动发起人")
      private String username;

      @ApiModelProperty("活动介绍")
      private String partyintro;


}
