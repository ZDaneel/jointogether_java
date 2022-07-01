package cs2020.experiment04.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("//partybill")
public class PartybillController {

    @Resource
    private IPartybillService partybillService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Partybill partybill) {
        partybillService.saveOrUpdate(partybill);
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
    public Result findAll() {
        return Result.success(partybillService.list());
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

}

