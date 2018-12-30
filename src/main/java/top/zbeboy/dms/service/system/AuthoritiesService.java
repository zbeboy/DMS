package top.zbeboy.dms.service.system;

import top.zbeboy.dms.domain.dms.tables.pojos.Authorities;

import java.util.List;
import java.util.Map;

public interface AuthoritiesService {

    /**
     * 根据用户账号查询权限
     *
     * @param username 账号
     * @return 用户权限
     */
    List<Authorities> findByUsername(String username);

    /**
     * Check if user is login by remember me cookie, refer
     * org.springframework.security.authentication.AuthenticationTrustResolverImpl
     *
     * @return true or false
     */
    Boolean isAnonymousAuthenticated();

    /**
     * 从当前用户信息中获取权限
     *
     * @return 权限集合
     */
    StringBuilder getAuthorities();

    /**
     * 获取权限
     *
     * @return 权限名
     */
    String getAuthName(String roleId);

    /**
     * 获取系统所有角色
     *
     * @return 所有角色
     */
    List<Map<String, String>> getAuthAll();

    /**
     * 根据账号删除
     *
     * @param username 账号
     */
    void deleteByUsername(String username);

    /**
     * 保存
     *
     * @param authorities 数据
     */
    void save(Authorities authorities);
}
