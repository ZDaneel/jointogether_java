package cs2020.experiment04.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cs2020.experiment04.entity.Menu;

import java.util.List;

/**
 *
 *  服务类
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
