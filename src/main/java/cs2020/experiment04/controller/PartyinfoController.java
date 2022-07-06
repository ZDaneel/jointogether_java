package cs2020.experiment04.controller;

import com.github.pagehelper.PageInfo;
import cs2020.experiment04.service.IPartybillService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs2020.experiment04.common.Result;

import cs2020.experiment04.service.IPartyinfoService;
import cs2020.experiment04.entity.Partyinfo;

import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author ZD
 * @since 2022-06-30
 */
@Log4j2
@RestController
@RequestMapping("/partyinfo")
public class PartyinfoController {

    @Resource
    private IPartyinfoService partyinfoService;

    @Resource
    private IPartybillService partybillService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Partyinfo partyinfo) {
        //插入partyinfo表
        partyinfoService.saveOrUpdate(partyinfo);
        //插入user_party表
        partyinfoService.saveUserParty(partyinfo);
        //插入partbill表
        partybillService.savePartyBillFirst(partyinfo);
        return Result.success();
    }

    @PostMapping("/changeparty")
    public Result saveParty(@RequestBody Partyinfo partyinfo) {
        //插入partyinfo表
        partyinfoService.saveOrUpdate(partyinfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        partyinfoService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        partyinfoService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(partyinfoService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(partyinfoService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam Integer id) {

        return Result.success(partyinfoService.findAllByPage(pageNum, pageSize, id));
    }

    //已经结束的活动
    @GetMapping("/allendparty")
    public Result allEndParty(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam Integer id,
                              @RequestParam String partyname) {
        return Result.success(partyinfoService.findEndPartyByPage(pageNum, pageSize, id, partyname));
    }

    @GetMapping("/joinparty")
    public Result joinParty(@RequestParam Integer userId,
                            @RequestParam Integer partyId) {
        partyinfoService.saveUserParty(userId, partyId);
        return Result.success();
    }

    @GetMapping("/mycreate")
    public Result myCreate(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam Integer id) {

        return Result.success(partyinfoService.findCreatedByPage(pageNum, pageSize, id));
    }

    @GetMapping("/mycreategrouped")
    public Result myCreateGrouped(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Integer id) {

        return Result.success(partyinfoService.findCreatedGroupedByPage(pageNum, pageSize, id));
    }

    @GetMapping("/mycreateunpaid")
    public Result myCreateUnpaid(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam Integer id) {

        return Result.success(partyinfoService.findCreateUnpaidByPage(pageNum, pageSize, id));
    }

    @GetMapping("/myjoin")
    public Result myJoin(@RequestParam Integer pageNum,
                         @RequestParam Integer pageSize,
                         @RequestParam Integer id) {

        return Result.success(partyinfoService.findJoinedByPage(pageNum, pageSize, id));
    }

    @GetMapping("/myjoingrouped")
    public Result myJoinGrouped(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam Integer id) {

        return Result.success(partyinfoService.findJoinedGroupedByPage(pageNum, pageSize, id));
    }

    //已结束的、未缴费的活动
    @GetMapping("/myjoinunpaid")
    public Result joinUnpaid(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam Integer id) {

        return Result.success(partyinfoService.findJoinedUnpaidByPage(pageNum, pageSize, id));
    }

    //成团is_group 0->1
    @GetMapping("/toGroup")
    public Result toGroup(@RequestParam Integer partyId) {
        partyinfoService.toGroup(partyId);
        return Result.success();
    }

    //结束活动is_active 1->0
    @GetMapping("/endparty")
    public Result endParty(@RequestParam Integer partyId) {
        partyinfoService.endParty(partyId);
        return Result.success();
    }

    //退出已参加的活动
    @GetMapping("/endjoinedparty")
    public Result endJoinedParty(@RequestParam Integer userId, @RequestParam Integer partyId) {
        partyinfoService.endJoinedParty(userId, partyId);
        return Result.success();
    }
}

