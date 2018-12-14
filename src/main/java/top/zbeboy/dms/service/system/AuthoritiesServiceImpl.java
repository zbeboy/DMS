package top.zbeboy.dms.service.system;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import top.zbeboy.dms.domain.dms.tables.daos.AuthoritiesDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Authorities;
import javax.annotation.Resource;
import java.util.List;

@Service("authoritiesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private final DSLContext create;

    @Resource
    private AuthoritiesDao authoritiesDao;

    @Autowired
    AuthoritiesServiceImpl(DSLContext dslContext) {
        create = dslContext;
    }

    @Override
    public List<Authorities> findByUsername(String username) {
        return authoritiesDao.fetchByUsername(username);
    }

    @Override
    public Boolean isAnonymousAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !ObjectUtils.isEmpty(authentication) && new AuthenticationTrustResolverImpl().isAnonymous(authentication);
    }
}
