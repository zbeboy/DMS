package top.zbeboy.dms.service.platform;

import top.zbeboy.dms.domain.dms.tables.pojos.Users;

public interface UsersService {

    /**
     * 根据账号查询用户信息(全部)
     *
     * @param username 账号
     * @return 用户全部信息
     */
    Users findByUsername(String username);

    /**
     * 从session中获取用户完整信息
     *
     * @return session中的用户信息
     */
    Users getUserFromSession();

    /**
     * 保存
     *
     * @param users 数据
     */
    void save(Users users);
}
