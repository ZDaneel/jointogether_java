package cs2020.experiment04.service;

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

    List<Partyinfo> findEndPartyByPage(Integer pageNum, Integer pageSize, Integer id, String partyname);

    void saveUserParty(Partyinfo partyinfo);

    void saveUserParty(Integer userId, Integer partyId);

    List<Partyinfo> findCreatedByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findCreatedGroupedByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findCreateUnpaidByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findJoinedByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findJoinedGroupedByPage(Integer pageNum, Integer pageSize, Integer id);

    List<Partyinfo> findJoinedUnpaidByPage(Integer pageNum, Integer pageSize, Integer id);

    void toGroup(Integer partyId);

    void endParty(Integer partyId);

    void endJoinedParty(Integer userId, Integer partyId);
}
