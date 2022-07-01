package cs2020.experiment04.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cs2020.experiment04.common.Constants;
import cs2020.experiment04.common.Result;
import cs2020.experiment04.controller.dto.UserDTO;
import cs2020.experiment04.controller.dto.UserEmailDTO;
import cs2020.experiment04.controller.dto.UserPasswordDTO;
import cs2020.experiment04.entity.User;
import cs2020.experiment04.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
//无token
/**
 *
 *  用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${files.upload.path}")
    private String filesUploadPath;

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password) || StrUtil.isBlank(email)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }

    @PostMapping("/checkEmail")
    public Result checkEmail(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        if (StrUtil.isBlank(email)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.checkEmail(userDTO));
    }

    @PostMapping("/checkCode")
    public Result checkCode(@RequestBody UserEmailDTO userEmailDTO) {
        String verCode = userEmailDTO.getVerCode();
        String email = userEmailDTO.getEmail();
        if (StrUtil.isBlank(email) ||StrUtil.isBlank(verCode)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.checkCode(userEmailDTO));
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody User user) {
        if (user.getId() == null && user.getPassword() == null) {  // 新增用户默认密码
            user.setPassword( SecureUtil.md5("123"));
        }
        return Result.success(userService.saveOrUpdate(user));
    }

    /**
     * 修改密码
     * @param userPasswordDTO
     * @return
     */
    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userPasswordDTO.setPassword(SecureUtil.md5(userPasswordDTO.getPassword()));
        userPasswordDTO.setNewPassword(SecureUtil.md5(userPasswordDTO.getNewPassword()));
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }

    /**
     * 重置密码
     * @param userDTO
     * @return
     */
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(email) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        userService.resetPassword(userDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/role/{role}")
    public Result findUsersByRole(@PathVariable String role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role);
        List<User> list = userService.list(queryWrapper);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String email,
                               @RequestParam(defaultValue = "") String address) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        return Result.success(userService.page(new Page<>(pageNum, pageSize)));
    }
}

