package cs2020.experiment04.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cs2020.experiment04.controller.dto.UserDTO;
import cs2020.experiment04.controller.dto.UserPasswordDTO;
import cs2020.experiment04.entity.User;
import org.apache.ibatis.annotations.Update;

/**
 *
 *  Mapper 接口
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    @Update("update sys_user set password = #{password} where email = #{email}")
    int resetPassword(UserDTO userDTO);

    User getAllStaff();
}
