package cs2020.experiment04.service;

import com.github.pagehelper.PageInfo;
import cs2020.experiment04.entity.Partymessage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 *  服务类
 *
 * @author PM
 * @since 2022-07-05
 */
public interface IPartymessageService extends IService<Partymessage> {

    PageInfo<Partymessage> findMessageByPage(Integer pageNum, Integer pageSize, Integer id);
}
