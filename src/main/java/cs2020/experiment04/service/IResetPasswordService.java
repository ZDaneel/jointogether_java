package cs2020.experiment04.service;

import cs2020.experiment04.entity.ResetPassword;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 *  服务类
 *
 * @author ZD
 * @since 2022-06-07
 */
public interface IResetPasswordService extends IService<ResetPassword> {
    //user_id查询
    ResetPassword queryByUserId(Integer userId);

}
