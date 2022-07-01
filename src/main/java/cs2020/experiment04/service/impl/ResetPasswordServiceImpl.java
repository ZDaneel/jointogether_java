package cs2020.experiment04.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs2020.experiment04.entity.ResetPassword;
import cs2020.experiment04.mapper.ResetPasswordMapper;
import cs2020.experiment04.service.IResetPasswordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 *  服务实现类
 *
 * @author ZD
 * @since 2022-06-07
 */
@Service
public class ResetPasswordServiceImpl extends ServiceImpl<ResetPasswordMapper, ResetPassword> implements IResetPasswordService {
    @Override
    public ResetPassword queryByUserId(Integer userId) {
        QueryWrapper<ResetPassword> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return getOne(queryWrapper);
    }
}
