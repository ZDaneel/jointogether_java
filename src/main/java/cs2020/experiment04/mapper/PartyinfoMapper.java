package cs2020.experiment04.mapper;

import cs2020.experiment04.entity.Partyinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZD
 * @since 2022-06-30
 */
public interface PartyinfoMapper extends BaseMapper<Partyinfo> {
    List<Partyinfo> findAllByPage(@Param("userId")Integer id);

    int countNowNumber(@Param("partyId")Integer id);

    int saveUserParty(@Param("userId")Integer userId, @Param("partyId")Integer partyId);

    List<Partyinfo> findCreatedByPage(@Param("userId")Integer id);

    List<Partyinfo> findCreatedGroupedByPage(@Param("userId")Integer id);

    List<Partyinfo> findCreatePayedByPage(@Param("userId")Integer id);

    List<Partyinfo> findJoinedByPage(@Param("userId")Integer id);

    List<Partyinfo> findJoinedGroupedByPage(@Param("userId")Integer id);

    int toGroup(@Param("partyId")Integer partyId);

    int endParty(@Param("partyId")Integer partyId);
}
