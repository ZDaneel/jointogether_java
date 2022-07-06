package cs2020.experiment04.mapper;

import cs2020.experiment04.entity.Partymessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author PM
 * @since 2022-07-05
 */
public interface PartymessageMapper extends BaseMapper<Partymessage> {

    //已成团的
    List<Partymessage> findMessageByPage(@Param("userId")Integer id);

    //找到所有的用户id
    List<Integer> findAllUserId(Integer partyId);

    //发送成团信息
    void sendGroupMessage(@Param("userId")Integer userId, @Param("partyId")Integer partyId);

    //发送缴费信息
    void sendPayMessage(@Param("userId")Integer userId, @Param("partyId")Integer partyId);
}
