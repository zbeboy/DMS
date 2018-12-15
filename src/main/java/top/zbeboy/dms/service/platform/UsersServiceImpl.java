package top.zbeboy.dms.service.platform;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import top.zbeboy.dms.domain.dms.tables.daos.UsersDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;
import top.zbeboy.dms.security.MyUserImpl;

import javax.annotation.Resource;
import java.security.Principal;

@Service("usersService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersDao usersDao;

    @Override
    public Users findByUsername(String username) {
        return usersDao.findById(username);
    }

    @Override
    public Users getUserFromSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users users = null;
        if (!ObjectUtils.isEmpty(principal) && principal instanceof MyUserImpl) {
            users = ((MyUserImpl) principal).getUsers();
        }
        return users;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Users users) {
        usersDao.insert(users);
    }
}
