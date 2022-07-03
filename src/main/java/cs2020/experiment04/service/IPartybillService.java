package cs2020.experiment04.service;

import cs2020.experiment04.entity.Partybill;
import com.baomidou.mybatisplus.extension.service.IService;
import cs2020.experiment04.entity.Partyinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *  服务类
 *
 * @author ZD
 * @since 2022-07-01
 */
public interface IPartybillService extends IService<Partybill> {
    List<Partybill> findBillByPartyId(Integer partyId);

    void savePartyBillFirst(Partyinfo partyinfo);

    void payConfirm(Integer userId, Integer partyId);

    void payOver(Integer partyId);
}
