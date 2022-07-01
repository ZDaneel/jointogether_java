package cs2020.experiment04.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * 
 *
 * @author ZD
 * @since 2022-06-07
 */
@Getter
@Setter
@ApiModel(value = "ResetPassword对象", description = "")
public class ResetPassword implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String vercode;


}
