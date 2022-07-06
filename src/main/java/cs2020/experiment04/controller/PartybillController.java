package cs2020.experiment04.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cs2020.experiment04.entity.Partyinfo;
import cs2020.experiment04.service.IPartyinfoService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs2020.experiment04.common.Result;

import cs2020.experiment04.service.IPartybillService;
import cs2020.experiment04.entity.Partybill;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 *  前端控制器
 *
 * @author ZD
 * @since 2022-07-01
 */
@RestController
@RequestMapping("/partybill")
public class PartybillController {

    @Resource
    private IPartybillService partybillService;

    @Resource
    private IPartyinfoService partyinfoService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Partybill partybill) {
        String billUsername = partybill.getBillUsername();

        partybillService.saveOrUpdate(partybill);
        //修改partyinfo里的费用
        Integer partyId = partybill.getBillPartyId();
        Double charge = partybill.getBillPrice();
        Partyinfo partyinfo = partyinfoService.getById(partyId);
        partyinfo.setCharge(partyinfo.getCharge()+charge);
        partyinfoService.saveOrUpdate(partyinfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        partybillService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        partybillService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll(@RequestParam Integer partyId) {
        return Result.success(partybillService.findBillByPartyId(partyId));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(partybillService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Partybill> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(partybillService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    //确认缴费
    @GetMapping("/payconfirm")
    public Result payConfirm(@RequestParam Integer userId, @RequestParam Integer partyId) {
        partybillService.payConfirm(userId, partyId);
        return Result.success();
    }

    //确认缴费
    @GetMapping("/payover")
    public Result payOver(@RequestParam Integer partyId) {
        partybillService.payOver(partyId);
        return Result.success();
    }
}

