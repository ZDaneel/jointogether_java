package cs2020.experiment04.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public int saveUserParty(Partyinfo partyinfo) {
        Integer partyinfoId = partyinfo.getId();
        //用户名取用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", partyinfo.getUsername());
        User userServiceOne = userService.getOne(queryWrapper);
        Integer userId = userServiceOne.getId();
        return saveUserParty(userId, partyinfoId);
    }

    @Override
    public int saveUserParty(Integer userId, Integer partyId) {
        return partyinfoMapper.saveUserParty(userId, partyId);
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
    public List<Partyinfo> findCreatePayedByPage(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        return AddCountNum(partyinfoMapper.findCreatePayedByPage(id));
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
    public int toGroup(Integer partyId) {
        return partyinfoMapper.toGroup(partyId);
    }

    @Override
    public int endParty(Integer partyId) {
        return partyinfoMapper.endParty(partyId);
    }

    //增加当前人数
    public List<Partyinfo> AddCountNum(List<Partyinfo> partyinfoList){
        for (int i = 0; i < partyinfoList.size(); i++) {
            int nowNumber = partyinfoMapper.countNowNumber(partyinfoList.get(i).getId());
            partyinfoList.get(i).setNownumber(nowNumber);
        }
        return partyinfoList;
    }


}
