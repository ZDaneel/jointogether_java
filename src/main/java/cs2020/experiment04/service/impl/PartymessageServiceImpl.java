package cs2020.experiment04.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cs2020.experiment04.entity.Partymessage;
import cs2020.experiment04.mapper.PartymessageMapper;
import cs2020.experiment04.service.IPartymessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 *  服务实现类
 *
 * @author PM
 * @since 2022-07-05
 */
@Service
public class PartymessageServiceImpl extends ServiceImpl<PartymessageMapper, Partymessage> implements IPartymessageService {

    @Resource
    private PartymessageMapper partymessageMapper;

    @Override
    public PageInfo<Partymessage> findMessageByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize,"pm.id desc");
        return new PageInfo<>(partymessageMapper.findMessageByPage(id));
    }



}
