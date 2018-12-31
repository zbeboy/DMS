package top.zbeboy.dms.service.platform;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.zbeboy.dms.domain.dms.tables.daos.UsersTypeDao;
import top.zbeboy.dms.domain.dms.tables.pojos.UsersType;

import javax.annotation.Resource;

@Service("usersTypeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UsersTypeServiceImpl implements UsersTypeService {

    @Resource
    private UsersTypeDao usersTypeDao;

    @Override
    public UsersType findByUsersTypeId(int usersTypeId) {
        return usersTypeDao.findById(usersTypeId);
    }

    @Override
    public UsersType findByUsersTypeName(String usersTypeName) {
        return usersTypeDao.fetchOneByUsersTypeName(usersTypeName);
    }
}
