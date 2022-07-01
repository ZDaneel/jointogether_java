package cs2020.experiment04.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cs2020.experiment04.common.Constants;
import cs2020.experiment04.common.Result;
import cs2020.experiment04.common.RoleEnum;
import cs2020.experiment04.controller.dto.UserDTO;
import cs2020.experiment04.controller.dto.UserEmailDTO;
import cs2020.experiment04.controller.dto.UserPasswordDTO;
import cs2020.experiment04.entity.Menu;
import cs2020.experiment04.entity.ResetPassword;
import cs2020.experiment04.entity.User;
import cs2020.experiment04.exception.ServiceException;
import cs2020.experiment04.mapper.RoleMapper;
import cs2020.experiment04.mapper.RoleMenuMapper;
import cs2020.experiment04.mapper.UserMapper;
import cs2020.experiment04.service.IMenuService;
import cs2020.experiment04.service.IResetPasswordService;
import cs2020.experiment04.service.IUserService;
import cs2020.experiment04.utils.TokenUtils;
import cs2020.experiment04.utils.VerCodeGenerateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 *  服务实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Resource
    private IResetPasswordService resetPasswordService;

    //	引入邮件接口
    @Resource
    private JavaMailSender mailSender;

    //	获得发件人信息
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public UserDTO login(UserDTO userDTO) {
        UserDTO result;
        // 用户密码 md5加密
        userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            result = userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
        return result;
    }

    @Override
    public User register(UserDTO userDTO) {
        // 用户密码 md5加密
        userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            // 默认一个普通用户的角色
            one.setRole(RoleEnum.ROLE_STAFF.toString());
            if (one.getNickname() == null) {
                one.setNickname(one.getUsername());
            }
            save(one);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    @Override
    public void resetPassword(UserDTO userDTO) {
        userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
        int update = userMapper.resetPassword(userDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "重置失败");
        }
    }

    @Override
    public String checkCode(UserEmailDTO userEmailDTO) {
        //email找id
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userEmailDTO.getEmail());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        //id找code
        ResetPassword resetPassword = resetPasswordService.queryByUserId(one.getId());
        String str = "false";
        if (resetPassword != null) {
            if (Objects.equals(userEmailDTO.getVerCode(), resetPassword.getVercode())){
                str = "true";
            }
        }
        return str;
    }

    @Override
    public User getAllStaff() {
        return userMapper.getAllStaff();
    }

    @Override
    public UserEmailDTO checkEmail(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userDTO.getEmail());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }

        /*邮箱验证*/
        UserEmailDTO userEmailDTO = null;
        if (one != null) {
            System.out.println(one);
            //String verCode = VerCodeGenerateUtil.generateVerCode();
            String verCode = sendEmail(one.getEmail());
            //把验证码写入数据库
            writeCode(one.getId(),verCode);
            userEmailDTO = new UserEmailDTO();
            userEmailDTO.setEmail(one.getEmail());
        }

        return userEmailDTO;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();

            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

    /**
     * 发送邮件
     * @param toEmail
     * @return verCode
     */
    private String sendEmail(String toEmail){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);

        message.setTo(toEmail);

        message.setSubject("您本次的验证码是");

        String verCode = VerCodeGenerateUtil.generateVerCode();

        message.setText("尊敬的客户,您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + "（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");

        mailSender.send(message);

        return verCode;
    }

    /**
     * 验证码写入数据库
     * @param userId, verCode
     */
    private void writeCode(Integer userId, String verCode){
        ResetPassword resetPassword = resetPasswordService.queryByUserId(userId);
        if (resetPassword != null) { //如果以及存在数据
            //更新验证码
            resetPassword.setVercode(verCode);
            resetPasswordService.updateById(resetPassword);
        } else {
            ResetPassword resetPassword1 = new ResetPassword();
            resetPassword1.setUserId(userId);
            resetPassword1.setVercode(verCode);
            resetPasswordService.save(resetPassword1);
        }
    }
}
