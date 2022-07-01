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
}
