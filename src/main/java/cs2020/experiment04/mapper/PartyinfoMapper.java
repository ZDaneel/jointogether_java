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
    /*
    * 正在进行的活动
    */
    List<Partyinfo> findAllByPage(@Param("userId")Integer id);

    /*
     * 参加的、已经结束的活动
     */
    List<Partyinfo> findEndPartyByPage(@Param("userId")Integer id,@Param("partyname")String partyname);

    /*
     * 计算活动当前的人数
     */
    int countNowNumber(@Param("partyId")Integer id);

    /*
     * 计算活动已支付的人数
     */
    int countPaidNumber(@Param("partyId")Integer id);

    /*
     * 新增一个参加活动的记录
     */
    int saveUserParty(@Param("userId")Integer userId, @Param("partyId")Integer partyId);

    /*
     * 自己创建的、正在进行的、没有成团的、没有缴费的
     */
    List<Partyinfo> findCreatedByPage(@Param("userId")Integer id);

    /*
     * 自己创建的、正在进行的、已经成团的、没有缴费的
     */
    List<Partyinfo> findCreatedGroupedByPage(@Param("userId")Integer id);

    /*
     * 自己创建的、已经结束的、已经成团的、没有缴费的
     */
    List<Partyinfo> findCreateUnpaidByPage(@Param("userId")Integer id);

    /*
     * 不是自己创建的、正在进行的、没有成团的、没有缴费的
     */
    List<Partyinfo> findJoinedByPage(@Param("userId")Integer id);

    /*
    * 不是自己创建的、正在进行的、已经成团的、没有缴费的
    */
    List<Partyinfo> findJoinedGroupedByPage(@Param("userId")Integer id);

    /*
     * 不是自己创建的、已经结束的、已经成团的、没有缴费的
     */
    List<Partyinfo> findJoinedUnpaidByPage(@Param("userId")Integer id);

    /*
     * 修改是否成团
     */
    int toGroup(@Param("partyId")Integer partyId);

    /*
     * 结束活动
     */
    int endParty(@Param("partyId")Integer partyId);

    /*
     * 退出活动
     */
    int endJoinedParty(@Param("userId")Integer userId, @Param("partyId")Integer partyId);
}
