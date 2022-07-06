package cs2020.experiment04.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs2020.experiment04.entity.Partybill;
import cs2020.experiment04.entity.Partyinfo;
import cs2020.experiment04.entity.User;
import cs2020.experiment04.mapper.PartybillMapper;
import cs2020.experiment04.service.IPartybillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cs2020.experiment04.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IUserService userService;

    @Override
    public List<Partybill> findBillByPartyId(Integer partyId) {
        return partybillMapper.findBillByPartyId(partyId);
    }

    @Override
    public void savePartyBillFirst(Partyinfo partyinfo) {
        //活动id
        Integer partyId = partyinfo.getId();
        //设置数据进行新增
        Partybill partybill = new Partybill();
        partybill.setBillPartyId(partyId);
        partybill.setBillName("报名费用");
        partybill.setBillPrice(partyinfo.getCharge());
        partybill.setBillUsername(partyinfo.getNickname());
        saveOrUpdate(partybill);
    }

    @Override
    public void payConfirm(Integer userId, Integer partyId) {
        partybillMapper.payConfirm(userId, partyId);
    }

    @Override
    public void payOver(Integer partyId) {
        partybillMapper.payOver(partyId);
    }

}
