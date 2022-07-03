package cs2020.experiment04.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import cs2020.experiment04.entity.Partyinfo;
import cs2020.experiment04.entity.User;
import cs2020.experiment04.mapper.PartyinfoMapper;
import cs2020.experiment04.service.IPartyinfoService;
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
 * @since 2022-06-30
 */
@Service
public class PartyinfoServiceImpl extends ServiceImpl<PartyinfoMapper, Partyinfo> implements IPartyinfoService {

    @Resource
    private PartyinfoMapper partyinfoMapper;

    @Autowired
    private IUserService userService;

    @Override
    public List<Partyinfo> findAllByPage(Integer pageNum, Integer pageSize,Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findAllByPage(id));
    }

    @Override
    public List<Partyinfo> findEndPartyByPage(Integer pageNum, Integer pageSize, Integer id, String partyname) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findEndPartyByPage(id, partyname));
    }

    @Override
    public void saveUserParty(Partyinfo partyinfo) {
        Integer partyinfoId = partyinfo.getId();
        //用户名取用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", partyinfo.getUsername());
        User userServiceOne = userService.getOne(queryWrapper);
        Integer userId = userServiceOne.getId();
        saveUserParty(userId, partyinfoId);
    }

    @Override
    public void saveUserParty(Integer userId, Integer partyId) {
        partyinfoMapper.saveUserParty(userId, partyId);
    }

    @Override
    public List<Partyinfo> findCreatedByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findCreatedByPage(id));
    }

    @Override
    public List<Partyinfo> findCreatedGroupedByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findCreatedGroupedByPage(id));
    }

    @Override
    public List<Partyinfo> findCreateUnpaidByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountPaidNum(AddCountNum(partyinfoMapper.findCreateUnpaidByPage(id)));
    }

    @Override
    public List<Partyinfo> findJoinedByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findJoinedByPage(id));
    }

    @Override
    public List<Partyinfo> findJoinedGroupedByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findJoinedGroupedByPage(id));
    }

    @Override
    public List<Partyinfo> findJoinedUnpaidByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findJoinedUnpaidByPage(id));
    }

    @Override
    public void toGroup(Integer partyId) {
        partyinfoMapper.toGroup(partyId);
    }

    @Override
    public void endParty(Integer partyId) {
        partyinfoMapper.endParty(partyId);
    }

    @Override
    public void endJoinedParty(Integer userId, Integer partyId) {
        partyinfoMapper.endJoinedParty(userId, partyId);
    }

    //添加当前人数字段
    public List<Partyinfo> AddCountNum(List<Partyinfo> partyinfoList){
        for (int i = 0; i < partyinfoList.size(); i++) {
            int nowNumber = partyinfoMapper.countNowNumber(partyinfoList.get(i).getId());
            partyinfoList.get(i).setNownumber(nowNumber);
        }
        return partyinfoList;
    }

    //添加已缴费人数字段
    public List<Partyinfo> AddCountPaidNum(List<Partyinfo> partyinfoList){
        for (int i = 0; i < partyinfoList.size(); i++) {
            int paidNumber = partyinfoMapper.countPaidNumber(partyinfoList.get(i).getId());
            partyinfoList.get(i).setPaidnumber(paidNumber);
        }
        return partyinfoList;
    }
}
