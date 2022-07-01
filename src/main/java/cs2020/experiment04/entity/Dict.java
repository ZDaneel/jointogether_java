package cs2020.experiment04.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_dict")
@Data
public class Dict {

    private String name;
    private String value;
    private String type;

}
