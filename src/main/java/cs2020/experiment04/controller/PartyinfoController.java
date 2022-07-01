package cs2020.experiment04.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Partyinfo partyinfo) {
        //插入partyinfo表
        partyinfoService.saveOrUpdate(partyinfo);
        //查询插入的id
        QueryWrapper<Partyinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("partyname", partyinfo.getPartyname());
        Partyinfo one = partyinfoService.getOne(queryWrapper);
        //插入user_party表
        partyinfoService.saveUserParty(one);
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


/*        QueryWrapper<Partyinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        Page<Partyinfo> page = partyinfoService.page(new Page<>(pageNum, pageSize), queryWrapper);*/
        List<Partyinfo> allByPage = partyinfoService.findAllByPage(pageNum, pageSize, id);
        return Result.success(allByPage);
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

        List<Partyinfo> createdByPage = partyinfoService.findCreatedByPage(pageNum, pageSize, id);
        return Result.success(createdByPage);
    }

    @GetMapping("/mycreategrouped")
    public Result myCreateGrouped(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Integer id) {

        List<Partyinfo> createdByPage = partyinfoService.findCreatedGroupedByPage(pageNum, pageSize, id);
        return Result.success(createdByPage);
    }

    @GetMapping("/myjoin")
    public Result myJoin(@RequestParam Integer pageNum,
                         @RequestParam Integer pageSize,
                         @RequestParam Integer id) {

        List<Partyinfo> joinedByPage = partyinfoService.findJoinedByPage(pageNum, pageSize, id);
        return Result.success(joinedByPage);
    }

    @GetMapping("/myjoingrouped")
    public Result myJoinGrouped(@RequestParam Integer pageNum,
                         @RequestParam Integer pageSize,
                         @RequestParam Integer id) {

        List<Partyinfo> joinedGroupedByPage = partyinfoService.findJoinedGroupedByPage(pageNum, pageSize, id);
        return Result.success(joinedGroupedByPage);
    }

    @GetMapping("/toGroup")
    public Result toGroup(@RequestParam Integer partyId) {
        partyinfoService.toGroup(partyId);
        return Result.success();
    }

}

