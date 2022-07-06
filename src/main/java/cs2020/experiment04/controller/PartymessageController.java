package cs2020.experiment04.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cs2020.experiment04.entity.Partyinfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cs2020.experiment04.common.Result;

import cs2020.experiment04.service.IPartymessageService;
import cs2020.experiment04.entity.Partymessage;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 *  前端控制器
 *
 * @author PM
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/partymessage")
public class PartymessageController {

    @Resource
    private IPartymessageService partymessageService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Partymessage partymessage) {
        partymessageService.saveOrUpdate(partymessage);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        partymessageService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        partymessageService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(partymessageService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(partymessageService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        QueryWrapper<Partymessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(partymessageService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    //消息通知
    @GetMapping("/groupedmessage")
    public Result GroupedPartyMessage(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam Integer id) {

        return Result.success(partymessageService.findMessageByPage(pageNum, pageSize, id));
    }


}

