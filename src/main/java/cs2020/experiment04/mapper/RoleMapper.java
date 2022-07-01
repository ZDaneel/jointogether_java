package cs2020.experiment04.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs2020.experiment04.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 *  Mapper 接口
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id from sys_role where flag = #{flag}")
    Integer selectByFlag(@Param("flag") String flag);
}
