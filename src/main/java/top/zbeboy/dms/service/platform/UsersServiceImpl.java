package top.zbeboy.dms.service.platform;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.zbeboy.dms.domain.dms.tables.daos.UsersDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Users;

import javax.annotation.Resource;

@Service("usersService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersDao usersDao;

    @Override
    public Users findByUsername(String username) {
        return usersDao.findById(username);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Users users) {
        usersDao.insert(users);
    }
}
