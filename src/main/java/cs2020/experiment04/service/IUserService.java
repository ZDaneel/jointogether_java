package cs2020.experiment04.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cs2020.experiment04.controller.dto.UserDTO;
import cs2020.experiment04.controller.dto.UserEmailDTO;
import cs2020.experiment04.controller.dto.UserPasswordDTO;
import cs2020.experiment04.entity.User;

/**
 *
 *  服务类
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    UserEmailDTO checkEmail(UserDTO userDTO);

    void resetPassword(UserDTO userDTO);

    String checkCode(UserEmailDTO userEmailDTO);

    User getAllStaff();
}
