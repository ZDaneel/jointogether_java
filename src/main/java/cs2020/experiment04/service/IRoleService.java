package cs2020.experiment04.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cs2020.experiment04.entity.Role;

import java.util.List;

/**
 *
 *  服务类
 */
public interface IRoleService extends IService<Role> {

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
