package cs2020.experiment04.service;

import com.github.pagehelper.PageInfo;
import cs2020.experiment04.entity.Partyinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 *  服务类
 *
 * @author ZD
 * @since 2022-06-30
 */
public interface IPartyinfoService extends IService<Partyinfo> {

    List<Partyinfo> findAllByPage(Integer pageNum, Integer pageSize, Integer id);

    int saveUserParty(Partyinfo partyinfo);

    int saveUserParty(Integer userId, Integer partyId);

    List<Partyinfo> findCreatedByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findCreatedGroupedByPage(Integer pageNum, Integer pageSize, Integer id);

    //findCreatePayedByPage
    List<Partyinfo> findCreatePayedByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findJoinedByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findJoinedGroupedByPage(Integer pageNum, Integer pageSize, Integer id);

    int toGroup(Integer partyId);

    int endParty(Integer partyId);
}
