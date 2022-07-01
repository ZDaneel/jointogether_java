package cs2020.experiment04.service.impl;

import cs2020.experiment04.entity.Partybill;
import cs2020.experiment04.mapper.PartybillMapper;
import cs2020.experiment04.service.IPartybillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *  服务实现类
 *
 * @author ZD
 * @since 2022-07-01
 */
@Service
public class PartybillServiceImpl extends ServiceImpl<PartybillMapper, Partybill> implements IPartybillService {

    @Resource
    private PartybillMapper partybillMapper;

    @Override
    public List<Partybill> findBillByPartyId(Integer partyId) {
        return partybillMapper.findBillByPartyId(partyId);
    }
}
