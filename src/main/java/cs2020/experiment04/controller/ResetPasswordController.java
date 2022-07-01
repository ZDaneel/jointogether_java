package cs2020.experiment04.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs2020.experiment04.common.Result;

import cs2020.experiment04.service.IResetPasswordService;
import cs2020.experiment04.entity.ResetPassword;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 *  前端控制器
 *
 * @author ZD
 * @since 2022-06-07
 */
@RestController
@RequestMapping("//reset-password")
public class ResetPasswordController {

    @Resource
    private IResetPasswordService resetPasswordService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody ResetPassword resetPassword) {
        resetPasswordService.saveOrUpdate(resetPassword);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        resetPasswordService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        resetPasswordService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(resetPasswordService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(resetPasswordService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<ResetPassword> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(resetPasswordService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}

