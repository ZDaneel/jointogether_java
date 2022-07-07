package cs2020.experiment04.mapper;

import cs2020.experiment04.entity.Partybill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZD
 * @since 2022-07-01
 */
public interface PartybillMapper extends BaseMapper<Partybill> {
    List<Partybill> findBillByPartyId(@Param("partyId") Integer partyId);

    /*
     * 确认缴费
     */
    int payConfirm(@Param("userId")Integer userId, @Param("partyId")Integer partyId);

    /*
     * 缴费全部完成
     */
    int payOver(@Param("partyId")Integer partyId);

    List<Partybill> findBillByPartyIdAndUserId(@Param("userId")Integer userId, @Param("partyId")Integer partyId);
}
