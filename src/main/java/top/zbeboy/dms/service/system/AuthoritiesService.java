package top.zbeboy.dms.service.system;

import top.zbeboy.dms.domain.dms.tables.pojos.Authorities;

import java.util.List;

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
}
