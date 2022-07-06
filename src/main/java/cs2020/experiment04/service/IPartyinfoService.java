package cs2020.experiment04.service;

import com.github.pagehelper.PageInfo;
import cs2020.experiment04.entity.Partyinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 *  服务类
 *
 * @author ZD
 * @since 2022-06-30
 */
public interface IPartyinfoService extends IService<Partyinfo> {

    PageInfo<Partyinfo> findAllByPage(Integer pageNum, Integer pageSize, Integer id);

    PageInfo<Partyinfo> findEndPartyByPage(Integer pageNum, Integer pageSize, Integer id, String partyname);

    void saveUserParty(Partyinfo partyinfo);

    void saveUserParty(Integer userId, Integer partyId);

    PageInfo<Partyinfo> findCreatedByPage(Integer pageNum, Integer pageSize, Integer id);

    PageInfo<Partyinfo> findCreatedGroupedByPage(Integer pageNum, Integer pageSize, Integer id);

    PageInfo<Partyinfo> findCreateUnpaidByPage(Integer pageNum, Integer pageSize, Integer id);

    PageInfo<Partyinfo> findJoinedByPage(Integer pageNum, Integer pageSize, Integer id);

    PageInfo<Partyinfo> findJoinedGroupedByPage(Integer pageNum, Integer pageSize, Integer id);

    PageInfo<Partyinfo> findJoinedUnpaidByPage(Integer pageNum, Integer pageSize, Integer id);

    void toGroup(Integer partyId);

    void endParty(Integer partyId);

    void endJoinedParty(Integer userId, Integer partyId);
}
