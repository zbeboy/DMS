package top.zbeboy.dms.service.analyse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.zbeboy.dms.domain.dms.tables.daos.DiplomaDao;
import top.zbeboy.dms.domain.dms.tables.pojos.Diploma;

import javax.annotation.Resource;
import java.util.List;

@Service("diplomaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DiplomaServiceImpl implements DiplomaService {

    @Resource
    private DiplomaDao diplomaDao;

    @Override
    public List<Diploma> findByCreditId(int creditId) {
        return diplomaDao.fetchByCreditId(creditId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save(Diploma diploma) {
        diplomaDao.insert(diploma);
    }

    @Override
    public void deleteById(int id) {
        diplomaDao.deleteById(id);
    }
}
